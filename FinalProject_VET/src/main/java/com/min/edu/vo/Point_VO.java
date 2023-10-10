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
public class Point_VO {

	private String pnt_id;
	private int pnt_seq;
	private int pnt_sum;
	private String pnt_date;
	private int pnt_point;

}
