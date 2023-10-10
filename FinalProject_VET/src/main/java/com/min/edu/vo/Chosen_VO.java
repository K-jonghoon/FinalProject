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
public class Chosen_VO {

	private String rpy_id;
	private int rpy_cnt;
	private int rpy_chosen;

	private Users_VO users_vo;
}
