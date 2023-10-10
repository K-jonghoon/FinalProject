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
public class Users_VO {

	private String users_id;
	private String users_pw;
	private String users_name;
	private String users_tel;
	private String users_status;
	private String users_joindate;
	private String users_addr;
	private String users_subtel;
	private String users_auth;
	private long users_crn;
	
	private List<PetsInfo_VO> petsinfo_vo;
	private List<SnsInfo_VO> snsinfo_vo;
	private List<SchedBoard_VO> shedboard_vo;
	private List<BookMark_VO> bookmark_vo;
	private List<Reservation_VO> reservation_vo;
	private List<NoticeBoard_VO> noticeboard_vo;
	private List<Point_VO> point_vo;
	private List<Payment_VO> payment_vo;
	private List<QuestBoard_VO> questboard_vo;
	private List<Hospital_VO> hospital_vo;
	

}
