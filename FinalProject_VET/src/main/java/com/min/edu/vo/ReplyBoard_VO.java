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
public class ReplyBoard_VO {

	private String rpy_seq;
	private String rpy_id;
	private String rpy_ref;
	private String rpy_content;
	private String rpy_regdate;
	private String rpy_status;
	private String rpy_chosen;	

	private List<QuestBoard_VO> questboard_vo;
	private List<FileBoard_VO> fileboard_vo;
	private List<Hospital_VO> hospital_vo;
	
}
