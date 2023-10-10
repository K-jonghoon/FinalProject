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
public class FileBoard_VO {

	private int f_seq;
	private String f_ref;
	private String f_originname;
	private String f_storedname;
	private int f_size;
	private String f_delflag;

}
