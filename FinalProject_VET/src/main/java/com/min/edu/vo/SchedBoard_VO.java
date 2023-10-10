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
public class SchedBoard_VO {

	private int sche_num;
	private String sche_id;
	private String sche_date;
	private String sche_title;
	private String sche_content;
	private String sche_hour;
	private String sche_minute;

}
