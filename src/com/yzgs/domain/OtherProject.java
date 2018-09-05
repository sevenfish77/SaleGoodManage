package com.yzgs.domain;

import java.util.Date;

/**
 * 其他支出项目实体
 * @author lenovo
 * 下午1:46:26
 */
public class OtherProject {
	private String id;//支出项目主键
	
	private String projectName;//支出项目名称
	
	private Double projectPrice;//支出项目金额
	
	private Date  projectDate;//支出项目时间
	
	private String projectRemark;//支出项目备注

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Double getProjectPrice() {
		return projectPrice;
	}

	public void setProjectPrice(Double projectPrice) {
		this.projectPrice = projectPrice;
	}

	public Date getProjectDate() {
		return projectDate;
	}

	public void setProjectDate(Date projectDate) {
		this.projectDate = projectDate;
	}

	public String getProjectRemark() {
		return projectRemark;
	}

	public void setProjectRemark(String projectRemark) {
		this.projectRemark = projectRemark;
	}
	
	
	
	
	

}
