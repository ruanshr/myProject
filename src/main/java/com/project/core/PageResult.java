package com.project.core;

import java.io.Serializable;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.project.core.utils.StringUtil;

public class PageResult<T> implements  Serializable {
	public static final int NUMBERS_PER_PAGE = 10;
	private int numPerPage;
	private int totalRows;
	private int totalPages;
	private int currentPage;
	private int startIndex;
	private int lastIndex;
	private List<T> resultList;
	private JdbcTemplate jdbcTemplate;
	public PageResult(String sql){
		if(jdbcTemplate == null){
			throw new IllegalArgumentException("com.project.core.PageResult.jdbcTemplate is null,please install it first");
		}
		if(StringUtil.isEmpty(sql)){
			throw new IllegalArgumentException("com.project.core.PageResult.sql is empty");
		}
		new PageResult<T>(sql,currentPage,NUMBERS_PER_PAGE,jdbcTemplate);
	}
	public PageResult(String sql, int currentPage, int numbersPerPage, JdbcTemplate jdbcTemplate) {
		if(jdbcTemplate == null){
			throw new IllegalArgumentException("com.project.core.PageResult.jdbcTemplate is null,please install it first");
		}
		if(StringUtil.isEmpty(sql)){
			throw new IllegalArgumentException("com.project.core.PageResult.sql is empty");
		}
		setNumPerPage(numbersPerPage);
		setCurrentPage(currentPage);
		StringBuffer totalSQL = new StringBuffer(" SELECT count(*) FROM (");
		totalSQL.append(sql);
		totalSQL.append(" ) totalTable");
		setJdbcTemplate(jdbcTemplate);
		setTotalRows(getJdbcTemplate().queryForObject(totalSQL.toString(), Integer.class));
		setTotalPages();
		setStartIndex();
		setLastIndex();
		
		setResultList(getJdbcTemplate().queryForList(getPageSQL(sql, startIndex, numbersPerPage)));
	} 
	
	public String getPageSQL(String queryString,Integer startIndex,Integer pageSize){
		String pageSql = "";
		if(null != startIndex && null != pageSize){
			pageSql = queryString + " limit "+startIndex + ","+pageSize;
		}else if(null != startIndex && null == pageSize){
			pageSql = queryString + " limit " + startIndex;
		}else{
			pageSql = queryString;
		}
		return pageSql;
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages() {
		if(totalRows % numPerPage == 0){
			this.totalPages = totalRows /numPerPage;
		}else{
			this.totalPages = (totalRows / numPerPage) + 1;
		} 
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex() {
		this.startIndex = (currentPage - 1) *numPerPage;
	}
	public int getLastIndex() {
		return lastIndex;
	}
	public void setLastIndex() {
		if(totalRows <numPerPage){
			this.lastIndex = totalRows;
		}else if((totalRows % numPerPage == 0) || (totalRows % numPerPage != 0 && currentPage < totalPages)){
			this.lastIndex = currentPage * numPerPage;
		}else if(totalRows % numPerPage !=0 && currentPage == totalPages){
			this.lastIndex = totalRows;
		}
	}
	public List getResultList() {
		return resultList;
	}
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	public String toString() {
		return "totalRows:"+totalRows+" totalPages:"+totalPages+" currentPage:"+currentPage+resultList;
	}

}
