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
public class Payment_VO {

	private String pay_num;
	private String pay_id;
	private int pay_amount;
	private String pay_time;
	private String pay_status;
	private String pay_method;
	private String pay_code;
	private String merchant_uid;
	private String imp_uid;

}
