package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class CriteriaWithNoKeyword {
	private int pagenum;
	private int amount;
	private int startrow;

	public CriteriaWithNoKeyword() {
		this(1,10);
	}

	public CriteriaWithNoKeyword(int pagenum, int amount) {
		this.pagenum = pagenum;
		this.amount = amount;
		this.startrow = (this.pagenum - 1) * this.amount;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
		this.startrow = (this.pagenum - 1) * this.amount;
	}

}