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
public class FullCalendar_VO {

	private String title;
	private String start;
	private String end;
	private String hosp_time;
	private String hosp_off;
	private String resrv_num;
	private String resrv_time;
	private String resrv_staus;
	
}
