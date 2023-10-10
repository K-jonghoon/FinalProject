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
public class NationwideHospital_VO {

	private String 번호;
	private String 개방서비스명;
	private String 상세영업상태;
	private String 소재지전화;
	private String 소재지전체주소;
	private String 도로명전체주소;
	private String 도로명우편번호;
	private String 사업장명;
	
}
