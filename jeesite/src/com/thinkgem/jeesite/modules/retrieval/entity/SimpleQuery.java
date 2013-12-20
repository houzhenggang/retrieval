package com.thinkgem.jeesite.modules.retrieval.entity;

import java.util.ArrayList;
import java.util.List;

public class SimpleQuery {
	/**
	 * 默认关键字
	 */
	private String keyword;
	
	private List<SimpleItem> simpleItems = new ArrayList<SimpleItem>();
	
	private String titleField;
	
	private String resumeField;
	
	private int titleLength=50;
	
	private int resumeLength=300;
	
	private List<String> queryFields;
	
	private int pageSize=10;
	
	private int nowStartPage=1;
	
	/**
	 * 关键字
	 * @return
	 */
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public List<SimpleItem> getSimpleItems() {
		return simpleItems;
	}
	public void setSimpleItems(List<SimpleItem> simpleItems) {
		this.simpleItems = simpleItems;
	}
	
	public String getTitleField() {
		return titleField;
	}
	public void setTitleField(String titleField) {
		this.titleField = titleField;
	}
	
	public int getTitleLength() {
		return titleLength;
	}
	public void setTitleLength(int titleLength) {
		this.titleLength = titleLength;
	}
	
	public List<String> getQueryFields() {
		return queryFields;
	}
	public void setQueryFields(List<String> queryFields) {
		this.queryFields = queryFields;
	}
	
	public String getResumeField() {
		return resumeField;
	}
	public void setResumeField(String resumeField) {
		this.resumeField = resumeField;
	}
	
	public int getResumeLength() {
		return resumeLength;
	}
	public void setResumeLength(int resumeLength) {
		this.resumeLength = resumeLength;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getNowStartPage() {
		return nowStartPage;
	}
	public void setNowStartPage(int nowStartPage) {
		this.nowStartPage = nowStartPage;
	}
	
}
