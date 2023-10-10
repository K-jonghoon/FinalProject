package com.min.edu.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MediChart_VO {

	private String medi_num;
	private int mpet_seq;
	private String medi_regdate;
	private String medi_visit;
	private String medi_l;
	private String medi_s;
	private String medi_title;
	private String medi_content;
	private String medi_id;
	private String medi_delflag;
	private String medi_lname;
	private String medi_sname;

	private List<FileBoard_VO> fileboard_vo;
	private List<MediCode_VO> medicode_vo;
	
}
