package com.min.edu.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Hospital_VO {

	private String hosp_id;
	private String hosp_name;
	private String hosp_time;
	private String hosp_off;
	private String hosp_park;
	private String hosp_etc;

	private List<AnimalConn_VO> animalconn_vo;
	private List<MediConn_VO> mediconn_vo;
	private List<Reservation_VO> reservation_vo;
	
}
