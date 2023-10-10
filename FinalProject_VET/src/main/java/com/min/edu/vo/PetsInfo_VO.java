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
public class PetsInfo_VO {

	private int pet_seq;
	private String pet_owner;
	private String pet_name;
	private String pet_bday;
	private String pet_species;
	private String pet_gender;
	private String pet_neut;
	private String pet_report;
	
	private List<MediChart_VO> medichart_vo;
	
}
