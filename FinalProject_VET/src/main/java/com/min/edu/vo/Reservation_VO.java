package com.min.edu.vo;

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
public class Reservation_VO {

	private String resrv_num;
	private String resrv_hops;
	private String resrv_visit;
	private String resrv_time;
	private String resrv_name;
	private String resrv_tel;
	private String resrv_memo;
	private String resrv_status;
	private String resrv_regdate;
	private String resrv_userid;

}
