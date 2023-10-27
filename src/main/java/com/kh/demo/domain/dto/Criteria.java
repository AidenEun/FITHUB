package com.kh.demo.domain.dto;

import lombok.Data;
import org.springframework.web.util.UriComponentsBuilder;

@Data
public class Criteria {
	private int pagenum;
	private int amount;
	private int startrow;

	public Criteria() {
		this(1,10);
	}

	public Criteria(int pagenum, int amount) {
		this.pagenum = pagenum;
		this.amount = amount;
		this.startrow = (this.pagenum - 1) * this.amount;
	}
	
	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
		this.startrow = (this.pagenum - 1) * this.amount;
	}
	
}







