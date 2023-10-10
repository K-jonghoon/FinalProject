package com.min.edu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.tools.DocumentationTool.Location;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.min.edu.model.service.IMediChart_Service;
import com.min.edu.vo.FileBoard_VO;
import com.min.edu.vo.MediChart_VO;
import com.min.edu.vo.MediCode_VO;
import com.min.edu.vo.Paging_VO;
import com.min.edu.vo.PetsInfo_VO;
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class MediChart_Controller {

	@Autowired
	private IMediChart_Service service;
	
	@GetMapping(value = "/selectAllChart.do")
	public String selectAllChart(HttpSession session, Model model) {
		log.info("&&&&& MediChartController 메인화면 -> 전체진료기록페이지 &&&&&");
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		String pet_owner = loginVo.getUsers_id();
		
		List<PetsInfo_VO> allPets = service.searchPet(pet_owner);
		model.addAttribute("allPets",allPets);
		
//		List<PetsInfo_VO> allCharts = service.selectAllChart(pet_owner);
//		model.addAttribute("allCharts",allCharts);
		
		List<MediCode_VO> lists = new ArrayList<MediCode_VO>();
		
		List<MediCode_VO> mlists =  service.selectAllMediCode();
		
		for(int i=0; i< mlists.size();i++) {
			if (mlists.get(i).getMedi_code().length() == 2) {
				lists.add(mlists.get(i));
			}
		}
		model.addAttribute("codelists",lists);
		
		return "chart_allChart";
	}
	

	@PostMapping(value = "/listByCodeS.do")
	@ResponseBody
	public Map<String, Object> listByCodeS(String medicodeL){
		log.info("&&&&& MediChartController listByCodeS 전달받은 parameter 값 : {} &&&&&",medicodeL);
		
		// 서비스 실행 쿼리 결과
		List<MediCode_VO> mlists = service.selectAllMediCode();
		List<MediCode_VO> list = new ArrayList<MediCode_VO>();
		
		for (int i=0; i < mlists.size(); i++) {
			if(mlists.get(i).getMedi_top().equals(medicodeL)) {
					list.add(mlists.get(i));
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		
		return map;
	}
	
	
	@PostMapping(value = "/selectPetChart.do")
	@ResponseBody
	public Map<String, Object> selectPetChart(HttpSession session, String pet_seq) {
		log.info("&&&&& MediChartController 전체진료기록 -> 반려동물별 진료기록페이지 &&&&&");
		log.info("&&&&& MediChartController selectPetChart 전달받은 parameter값 : {}&&&&&",pet_seq);
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		String pet_owner = loginVo.getUsers_id();
		
		Map<String, Object> map = new HashMap<String, Object>();
			map.put("pet_owner", pet_owner);
			map.put("pet_seq", pet_seq);
		
		List<PetsInfo_VO> selectPetChart = service.selectPetChart(map);
		Map<String, Object> map2 = new HashMap<String, Object>();
		
		map2.put("detail", selectPetChart);
		return map2;
	}
	
	
	@GetMapping(value = "/insertNewChartForm.do")
	public String insertNewChartForm(HttpSession session, Model model, String sche_content, String sche_date) {
		log.info("&&&&& MediChartController 반려동물별 진료기록 -> 새 진료기록 작성페이지 &&&&&");
		log.info("&&&&& MediChartController insertNewChartForm 전달받은 parameter값: {} {}",sche_content, sche_date);
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		String pet_owner = loginVo.getUsers_id();
		
		List<PetsInfo_VO> pvo = service.searchPet(pet_owner);
		List<PetsInfo_VO> petList = new ArrayList<PetsInfo_VO>();
		if(!pvo.isEmpty()) {
			for(int i=0; i<pvo.size();i++) {
				petList.add(pvo.get(i));
			}
		}
		model.addAttribute("petList", petList);
		model.addAttribute("medi_title",sche_content);
		model.addAttribute("medi_visit",sche_date);
		
		return "chart_insertNewChartForm";
	}
	
	@PostMapping(value = "/insertNewChart.do")
	public String insertNewChart(@RequestParam Map<String, Object> map, HttpSession session , Model model) {
		log.info("&&&&& MediChartController 새 진료기록 작성페이지 -> 상세페이지 &&&&&");
		log.info("&&&&& MediChartController selectSChart 전달받은 parameter값 : {}",map);
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		String medi_id = loginVo.getUsers_id();
		
		String medi_content = (String) map.get("medi_content");
		String escapedContent = StringEscapeUtils.escapeHtml4(medi_content);
		String medi_visit = (String) map.get("medi_visit");
		String pet_seq = (String)map.get("petName");
		int mpet_seq = Integer.parseInt(pet_seq);
		String medi_title = (String)map.get("medi_title");
		String codeL = (String) map.get("codeL");
		String codeS = (String) map.get("codeS");
		
		MediCode_VO mvo1 = service.searchMediName(codeL);
		String medi_l = mvo1.getMedi_name();
		MediCode_VO mvo2 = service.searchMediName(codeS);
		String medi_s = mvo2.getMedi_name();
		
		MediChart_VO detailVo = new MediChart_VO();
		detailVo.setMedi_content(escapedContent);
		detailVo.setMedi_id(medi_id);
		detailVo.setMedi_l(codeL);
		detailVo.setMedi_lname(medi_l);
		detailVo.setMedi_s(codeS);
		detailVo.setMedi_sname(medi_s);
		detailVo.setMedi_title(medi_title);
		detailVo.setMedi_visit(medi_visit);
		detailVo.setMpet_seq(mpet_seq);
		
		String m = service.insertNewChart(detailVo);
		
		return "redirect:/selectOneChart.do?medi_num="+m;
	}
	
	
	@PostMapping(value = "/selectSChart.do")
	@ResponseBody
	public Map<String, Object> selectSChart(HttpSession session, String medi_l, String medi_s){
		log.info("&&&&& MediChartController selectSChart 전달받은 parameter값 : {} {}&&&&&",medi_l,medi_s);
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		String pet_owner = loginVo.getUsers_id();
		
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("medi_l", medi_l);
			put("medi_s", medi_s);
			put("pet_owner", pet_owner);
		}};
		List<PetsInfo_VO> slists = service.selectSChart(map);
		List<PetsInfo_VO> lists = new ArrayList<PetsInfo_VO>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		
		for(int i=0; i<slists.size();i++ ) {
			lists.add(slists.get(i));
		}
		map2.put("lists", lists);
	
		return map2;
	}
	
	@GetMapping(value = "/selectOneChart.do")
	public String selectOneChart(String medi_num, Model model){
		log.info("&&&&& MediChartController 진료기록 작성페이지 -> 상세페이지 &&&&&");
		log.info("&&&&& MediChartController selectOneChart 전달받은 parameter값 : {}&&&&&",medi_num);
		
		PetsInfo_VO pvo =  service.selectOneChart(medi_num);
		
		String unescapedContent = StringEscapeUtils.unescapeHtml4(pvo.getMedichart_vo().get(0).getMedi_content());  
		
		model.addAttribute("pvo",pvo);
		model.addAttribute("medi_content", unescapedContent);
		
		return "chart_detail";
	}
	
	@GetMapping(value = "/modifyChartForm.do")
	public String modifyChartForm(String medi_num, Model model) {
		log.info("&&&&& MediChartController 상세페이지 -> 진료기록 수정페이지 &&&&&");
		log.info("&&&&& MediChartController modifyChartForm 전달받은 parameter값 : {}&&&&&",medi_num);
		PetsInfo_VO pvo = service.selectOneChart(medi_num);
		String unescapedContent = StringEscapeUtils.unescapeHtml4(pvo.getMedichart_vo().get(0).getMedi_content());
		
		model.addAttribute("pvo",pvo);
		model.addAttribute("medi_content", unescapedContent);
		return "chart_updateForm";
	}
	
	@PostMapping(value = "/updateChart.do")
	public String updateChart(String medi_num, String medi_content) {
		log.info("&&&&& MediChartController 진료기록 수정페이지(수정) -> 상세페이지 &&&&&");
		log.info("&&&&& MediChartController modifyChartForm 전달받은 parameter값 : {} {}&&&&&",medi_num, medi_content);
		String escapedContent = StringEscapeUtils.escapeHtml4(medi_content);
		
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("medi_content", escapedContent);
			put("medi_num",medi_num);
		}};
		
		int n = service.modifyChart(map);
		
		return n>0?"redirect:/selectOneChart.do?medi_num="+medi_num : "redirect:/modifyChartForm.do?medi_num="+medi_num;
	}

	@GetMapping(value = "/deleteChart.do")
	public String deleteChart(String medi_num) {
		log.info("&&&&& MediChartController 진료기록 수정페이지(삭제) -> 상세페이지 &&&&&");
		log.info("&&&&& MediChartController modifyChartForm 전달받은 parameter값 : {}&&&&&", medi_num);
		
		int n = service.deleteChart(medi_num);
		
		return n>0?"redirect:/selectAllChartPaging.do":"redirect:/selectOneChart.do?medi_num="+medi_num;
	}
	
	@RequestMapping(value="/getContent.do", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String getContent(String medi_num) {
		log.info("&&&&& MediChartController getContent 전달받은 parameter값 : {}&&&&&", medi_num);
		String content = service.getDetail(medi_num);
		String unescapedContent = StringEscapeUtils.unescapeHtml4(content);
		
		return unescapedContent;
	}
	
	@RequestMapping(value="/uploadImage.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> uploadImage(MultipartFile upload, HttpServletRequest req) {
		log.info("&&&&& MediChartController modifyChartForm 전달받은 parameter값 : {}&&&&&",upload);
		
		String ext = upload.getOriginalFilename().substring(upload.getOriginalFilename().lastIndexOf("."));
		String saveName = UUID.randomUUID().toString().replace("-", "")+ext;

		InputStream inputStream = null;
		OutputStream outputStream = null;
		String path="";
		
		try {
			inputStream = upload.getInputStream();
			
			path = WebUtils.getRealPath(req.getSession().getServletContext(),"/ckupload");
			System.out.println("########"+path);
			
			File storage = new File(path);
			if(!storage.exists()) {
				storage.mkdir();
			}
			File newFile = new File(path+"/"+saveName);
			if(!newFile.exists()) {
				newFile.createNewFile();
			}
			outputStream = new FileOutputStream(newFile);
			
			int read = 0;
			byte[] b = new byte[(int)upload.getSize()];
			while((read=inputStream.read(b))!=-1) {
				outputStream.write(b,0,read);
			}
			
		} catch (IOException e) {
			log.error("uploadImage read Error : \n"+e.getMessage());
		} finally {
				try {
					inputStream.close();
					outputStream.close();
				} catch (IOException e) {
					log.error("uploadImage close Error : \n"+e.getMessage());
					e.printStackTrace();
				}
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "./ckupload/"+saveName);
		
		return map;
	}
	
	@RequestMapping(value="/removeImage.do", method = RequestMethod.POST)
	@ResponseBody
	public void removeImage(String saveName, HttpServletRequest req) {
		log.info("&&&&& MediChartController removeImage 전달받은 parameter값 : {}&&&&&",saveName);
		
		String path = "";
		
		try {
			path = WebUtils.getRealPath(req.getSession().getServletContext(),"/ckupload");
			File oldFile = new File(path+"/"+saveName);
			if(oldFile.exists()) {
				oldFile.delete();
			}
		} catch (FileNotFoundException e) {
			log.error("removeImage Error : \n"+e.getMessage());
		}
	}
	
	@GetMapping(value = "/pdfDownload.do")
	public String pdfDownload(String medi_num,Model model, HttpServletRequest req) {
		log.info("&&&&& MediChartController pdfDownload 전달받은 parameter값 : {}&&&&&",medi_num);
		
		String result = "";
		
		PetsInfo_VO pvo = service.selectOneChart(medi_num);
		
		try {
            Document document = new Document(); // pdf 문서를 처리하는 객체
            
            Date date = new Date(); 
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
            String format_date = formatter.format(date);
            
            // System 환경변수 중 사용자 디렉토리 경로 가져오기
            String env= System.getenv("USERPROFILE");
            
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(env+"/Downloads/진료기록_"+format_date+".pdf"));
            
            document.open();
            
            BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/H2GTRM.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            
            Font font = new Font(baseFont, 12);
 
            PdfPTable table = new PdfPTable(5);
            Chunk chunk = new Chunk("진료기록", font);
            Paragraph ph = new Paragraph(chunk);
            ph.setAlignment(Element.ALIGN_CENTER);
            document.add(ph);
            
            float[] columnWidths = new float[]{10f, 10f, 10f, 10f, 40f};
            table.setWidths(columnWidths);
            table.setPaddingTop(10);
 
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE); 
            
            PdfPCell cell1 = new PdfPCell(new Phrase("반려동물", font));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
 
            PdfPCell cell2 = new PdfPCell(new Phrase("진료일자", font));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
 
            PdfPCell cell3 = new PdfPCell(new Phrase("진료과목", font));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell cell4 = new PdfPCell(new Phrase("세부과목",font));
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
 
            PdfPCell cell5 = new PdfPCell(new Phrase("내용",font));
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            table.addCell(cell1); 
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
 
            String unescapedContent = StringEscapeUtils.unescapeHtml4(pvo.getMedichart_vo().get(0).getMedi_content());
            System.out.println("진료내용 : "+ unescapedContent);
            
            //진료내용 중 글만 추출하기(not img)
            String pattern1 = "<p>(.*?)</p>";
            Pattern p = Pattern.compile(pattern1);
            Matcher m = p.matcher(unescapedContent);
            
            String text = "";
            while(m.find()) {
            	for(int i=0; i<m.groupCount(); i++) {
            		text += m.group(i);
            	}
            }
            Matcher m2 = p.matcher(text);
            
            //추출된 <p>태그 개수만큼 잘라 붙여서 내용 출력형태 만들기
            String final_text = "";
            while(m2.find()) {
            	String cuttedText = m2.group(1);
            	final_text += cuttedText;
            }
            
            PdfPCell pet_name = new PdfPCell(new Phrase(pvo.getPet_name(), font));
            PdfPCell medi_visit = new PdfPCell(new Phrase(pvo.getMedichart_vo().get(0).getMedi_visit(), font));
            PdfPCell medi_lname  = new PdfPCell(new Phrase(pvo.getMedichart_vo().get(0).getMedi_lname(), font));
            PdfPCell medi_sname = new PdfPCell(new Phrase(pvo.getMedichart_vo().get(0).getMedi_sname(),font));
            PdfPCell medi_content = new PdfPCell(new Phrase(final_text,font));    
            
            table.addCell(pet_name); // 테이블에 생성한 셀 데이터 추가
            table.addCell(medi_visit);
            table.addCell(medi_lname);
            table.addCell(medi_sname);
            table.addCell(medi_content);
            
            document.add(table);
            
            //진료내용에 img태그가 존재하면 pdf에 이미지 추가
            if (unescapedContent.contains("img")) {
            	
            	int count = 0;
                int index = 0;
                String target = "img";

                while ((index = unescapedContent.indexOf("img", index)) != -1) {
                    count++;
                    index += target.length();
                }
                // 진료내용에 포함된 img 개수 구하기
                System.out.println("포함된 이미지 개수 : "+ count);
            	
                // 진료내용 중 이미지태그만 추출하기
            	 String pattern2 = "<img\\s+src=\"([^\"]+)\"";
                 Pattern imgPattern = Pattern.compile(pattern2);
                 Matcher matcher = imgPattern.matcher(unescapedContent);
                 
                 String fileName = "";
                 while (matcher.find()) {
                     String imgsrc = matcher.group(1);

                     // 파일명 추출
                     String[] parts = imgsrc.split("/");
                     fileName = parts[parts.length - 1];
                 }
                 
                 ServletContext context = req.getServletContext();
                 String savedirectory = context.getRealPath("ckupload");
                 
                 System.out.println("상대경로!!!!!!! : "+savedirectory);
                 
                 Image img = Image.getInstance(savedirectory +"/"+ fileName);
                 
                 //이미지 사이즈 원본의 60퍼센트로 축소
                 img.scalePercent(60);
                 
                 // 이미지의 가로, 세로 크기 가져오기
                 float imgWidth = img.getScaledWidth();
                 float imgHeight = img.getScaledHeight();

                 // 페이지 크기 가져오기
                 float pageWidth = document.getPageSize().getWidth();
                 float pageHeight = document.getPageSize().getHeight();

                 // 이미지를 페이지 중앙에 배치하기 위한 좌표 계산
                 float x = (pageWidth - imgWidth) / 2;
                 float y = (pageHeight - imgHeight) / 2;

                 // 이미지 위치 설정
                 img.setAbsolutePosition(x, y);
                 
                 document.add(img);
            } 
            document.close();
            
            result = "success";
 
        } catch (Exception e) {
            e.printStackTrace();
            result = "fail";
        }
		
		if(result.equals("success")) {
			model.addAttribute("msg","다운로드 폴더에 PDF 파일이 저장되었습니다");
			model.addAttribute("url","selectAllChartPaging.do");
			return "alert";
		}else {
			model.addAttribute("msg","PDF 다운로드에 실패했습니다.");
			model.addAttribute("url","selectAllChartPaging.do");
			return "alert";
		}
	}
	
	//전체 진료기록 조회 (페이징 처리)
	@GetMapping(value = "/selectAllChartPaging.do")
	public String selectAllChartPaging(HttpSession session, Model model,String pet_seq,
										@RequestParam(required = false, defaultValue = "1")String page) {
		log.info("&&&&& MediChartController selectAllChartPaging 전달받은 parameter값  페이지 :{}&&&&&",page);
		Users_VO loginVo = (Users_VO)session.getAttribute("loginVo");
		String pet_owner = loginVo.getUsers_id();
		
		Paging_VO pVo = null;
		if(session.getAttribute("row")==null) {
			pVo = new Paging_VO();
			session.setAttribute("row", pVo);
		} 
		else {
			pVo = (Paging_VO)session.getAttribute("row");
		}
		int selectPage = Integer.parseInt(page);
		int totalCount = service.chartAllCount(pet_owner);
		
		pVo.setTotalCount(totalCount);
		pVo.setCountList(4);
		pVo.setCountPage(1);
		pVo.setTotalPage(pVo.getTotalCount());
		pVo.setPage(selectPage);
		pVo.setStagePage(selectPage);
		pVo.setEndPage(pVo.getCountPage());
		
		int first = pVo.getPage()*pVo.getCountList()-(pVo.getCountList()-1);
		int last = pVo.getPage()*pVo.getCountList();
		
		if(pet_seq != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pet_owner", pet_owner);
			map.put("first",first);
			map.put("last", last);
			map.put("pet_seq", pet_seq);
			List<PetsInfo_VO> chart_lists = service.selectAllChartPaging(map);
			model.addAttribute("chart_lists",chart_lists);
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pet_owner", pet_owner);
			map.put("first",first);
			map.put("last", last);
			List<PetsInfo_VO> chart_lists = service.selectAllChartPaging(map);
			model.addAttribute("chart_lists",chart_lists);
		}
		
		List<PetsInfo_VO> allPets = service.searchPet(pet_owner);
		model.addAttribute("allPets",allPets);
		
		List<MediCode_VO> code_lists = new ArrayList<MediCode_VO>();
		List<MediCode_VO> mlists =  service.selectAllMediCode();
		
		for(int i=0; i< mlists.size();i++) {
			if (mlists.get(i).getMedi_code().length() == 2) {
				code_lists.add(mlists.get(i));
			}
		}
		model.addAttribute("codelists",code_lists);
		model.addAttribute("page",pVo);
		
		return "chart_allChart";
	}
	
}

