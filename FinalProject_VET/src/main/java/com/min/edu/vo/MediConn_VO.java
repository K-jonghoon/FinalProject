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
public class MediConn_VO {

	private String medi_code;
	private String hosp_id;

	private List<MediCode_VO> medicode_vo;
}
