package cn.erp.pojo;

public class TblOrder {
    private Long uuid;

    private String ordernum;

    private Long creater;

    private Long createtime;

    private Long checker;

    private Long checktime;

    private Long completer;

    private Long endtime;

    private Integer ordertype;

    private Integer type;

    private Double totalprice;

    private Long supplieruuid;

	private Integer totalnum;
	
	
	private TblEmp tblEmp;
	
	private TblSupplier tblSupplier;
	
	

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public Long getCreater() {
		return creater;
	}

	public void setCreater(Long creater) {
		this.creater = creater;
	}

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public Long getChecker() {
		return checker;
	}

	public void setChecker(Long checker) {
		this.checker = checker;
	}

	public Long getChecktime() {
		return checktime;
	}

	public void setChecktime(Long checktime) {
		this.checktime = checktime;
	}

	public Long getCompleter() {
		return completer;
	}

	public void setCompleter(Long completer) {
		this.completer = completer;
	}

	public Long getEndtime() {
		return endtime;
	}

	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}

	public Integer getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(Integer ordertype) {
		this.ordertype = ordertype;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}

	public Long getSupplieruuid() {
		return supplieruuid;
	}

	public void setSupplieruuid(Long supplieruuid) {
		this.supplieruuid = supplieruuid;
	}

	public Integer getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(Integer totalnum) {
		this.totalnum = totalnum;
	}

	public TblEmp getTblEmp() {
		return tblEmp;
	}

	public void setTblEmp(TblEmp tblEmp) {
		this.tblEmp = tblEmp;
	}

	public TblSupplier getTblSupplier() {
		return tblSupplier;
	}

	public void setTblSupplier(TblSupplier tblSupplier) {
		this.tblSupplier = tblSupplier;
	}



	@Override
	public String toString() {
		return "TblOrder [uuid=" + uuid + ", ordernum=" + ordernum + ", creater=" + creater + ", createtime="
				+ createtime + ", checker=" + checker + ", checktime=" + checktime + ", completer=" + completer
				+ ", endtime=" + endtime + ", ordertype=" + ordertype + ", type=" + type + ", totalprice=" + totalprice
				+ ", supplieruuid=" + supplieruuid + ", totalnum=" + totalnum + ", tblEmp=" + tblEmp + ", tblSupplier="
				+ tblSupplier + " ]";
	}

	
	
	
	
	

 
}