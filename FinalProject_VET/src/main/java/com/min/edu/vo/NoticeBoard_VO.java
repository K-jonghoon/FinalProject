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
public class NoticeBoard_VO {

	private String noti_seq;
	private String noti_auth;
	private String noti_id;
	private String noti_title;
	private String noti_content;
	private String noti_regdate;
	private String noti_delflag;
	
	private List<FileBoard_VO> fileboard_vo;
	
}
