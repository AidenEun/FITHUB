package com.kh.demo.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageDTO {
	private int startPage;
	private int endPage;
	private int realEnd;
	private Long total;
	private boolean prev,next;
	private Criteria cri;

	public PageDTO(Long total, Criteria cri) {
		int amount = cri.getAmount();
		int pagenum = cri.getPagenum();
		this.cri = cri;
		this.total = total;


		this.endPage = (int)Math.ceil(pagenum*1.0/10)*10;
		this.startPage = this.endPage - 9;
		this.realEnd = (int)Math.ceil(total*1.0/amount);
		this.realEnd = this.realEnd == 0?1:this.realEnd;
		
		this.endPage = Math.min(endPage, realEnd);
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < this.realEnd;
	}

}