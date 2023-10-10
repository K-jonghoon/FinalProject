package com.min.edu.vo;

import java.util.List;

import org.apache.commons.text.StringEscapeUtils;

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
public class QuestBoard_VO {

	private String qst_seq;
	private String qst_id;
	private String qst_title;
	private String qst_content;
	private String qst_species;
	private String qst_part;
	private String qst_regdate;
	private String qst_fast;
	private String qst_status;
	private String users_name;
	private String anm_species;
	private String part_name;
	
	private List<ReplyBoard_VO> replyboard_vo;
	private List<FileBoard_VO> fileboard_vo;
	private List<AnimalPart_VO> animalpart_vo;
	private List<AnimalCode_VO> animalcode_vo;
	private List<Users_VO> users_vo;
	private List<Hospital_VO> hospital_vo;
	
	public String getUnescapeContent() {
		return StringEscapeUtils.unescapeHtml4(qst_content);
	}
	
}
