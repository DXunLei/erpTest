package cn.erp.dto;

import cn.erp.pojo.TblEmp;

public class OrderExample {

	private Long uuid;
	
	private TblEmp tblEmp;

	private Long createtime;

	private Long endtime;
	
	private Integer begintotalnum;
	
	private Integer endtotalnum;
	
	private Double begintotalprice;
	
	private Double endtotalprice;
	
	private Long supplierUuid;
	
	
	
	
	public Long getSupplierUuid() {
		return supplierUuid;
	}

	public void setSupplierUuid(Long supplierUuid) {
		this.supplierUuid = supplierUuid;
	}

	public TblEmp getTblEmp() {
		return tblEmp;
	}

	public void setTblEmp(TblEmp tblEmp) {
		this.tblEmp = tblEmp;
	}

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public Long getEndtime() {
		return endtime;
	}

	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}

	public Integer getBegintotalnum() {
		return begintotalnum;
	}

	public void setBegintotalnum(Integer begintotalnum) {
		this.begintotalnum = begintotalnum;
	}

	public Integer getEndtotalnum() {
		return endtotalnum;
	}

	public void setEndtotalnum(Integer endtotalnum) {
		this.endtotalnum = endtotalnum;
	}

	public Double getBegintotalprice() {
		return begintotalprice;
	}

	public void setBegintotalprice(Double begintotalprice) {
		this.begintotalprice = begintotalprice;
	}

	public Double getEndtotalprice() {
		return endtotalprice;
	}

	public void setEndtotalprice(Double endtotalprice) {
		this.endtotalprice = endtotalprice;
	}

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	
}
