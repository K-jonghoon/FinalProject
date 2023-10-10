package com.min.edu.vo;

public class Paging_VO {

	private int page;
	private int countList;
	
	private int totalCount;
	
	private int countPage;
	private int totalPage;
	private int stagePage;
	private int endPage;
	
	
	
	public Paging_VO(int page, int countList, int totalCount, int countPage, int totalPage, int stagePage,
			int endPage) {
		super();
		this.page = page;
		this.countList = countList;
		this.totalCount = totalCount;
		this.countPage = countPage;
		this.totalPage = totalPage;
		this.stagePage = stagePage;
		this.endPage = endPage;
	}
	
	public Paging_VO() {
		super();
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		if(totalPage < page) {
			page = totalPage;
		}
		this.page = page;
	}
	public int getCountList() {
		return countList;
	}
	public void setCountList(int countList) {
		this.countList = countList;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCountPage() {
		return countPage;
	}
	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		int totalPageResult = totalCount/countList;
		if(totalCount%countList >0) {
			totalPageResult++;
		}
		this.totalPage = totalPageResult;
	}
	public int getStagePage() {
		return stagePage;
	}
	public void setStagePage(int stagePage) {
		int stagePageResult = ((page-1)/countPage)*countPage+1;
		this.stagePage = stagePageResult;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		
		int endPageResult = stagePage+countPage-1;
		if(endPageResult > totalPage) {
			endPageResult = totalPage;
		}
		
		this.endPage = endPageResult;
	}
	
	@Override
	public String toString() {
		return "Paging_Vo [page=" + page + ", countList=" + countList + ", totalCount=" + totalCount + ", countPage="
				+ countPage + ", totalPage=" + totalPage + ", stagePage=" + stagePage + ", endPage=" + endPage + "]";
	}
	
}
