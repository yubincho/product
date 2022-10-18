package com.example.store.model;

import java.util.Date;

import lombok.Data;

@Data
public class CategoryDTO {

	private Integer idx;
	private String code;
	private String codename;
	private int sort_num;
	private String comment;
	private Date reg_date;
	
}
