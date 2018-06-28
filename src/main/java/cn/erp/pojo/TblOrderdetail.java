package cn.erp.pojo;

public class TblOrderdetail {
    private Long uuid;

    private Long goodsuuid;

    private Double price;

    private Long orderuuid;

    private Integer num;

    private Double surplus;
    
    private TblGoods tblGoods;
    
    private TblOrder tblOrder;

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public Long getGoodsuuid() {
        return goodsuuid;
    }

    public void setGoodsuuid(Long goodsuuid) {
        this.goodsuuid = goodsuuid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getOrderuuid() {
        return orderuuid;
    }

    public void setOrderuuid(Long orderuuid) {
        this.orderuuid = orderuuid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getSurplus() {
        return surplus;
    }

    public void setSurplus(Double surplus) {
        this.surplus = surplus;
    }

	public TblGoods getTblGoods() {
		return tblGoods;
	}

	public void setTblGoods(TblGoods tblGoods) {
		this.tblGoods = tblGoods;
	}

	public TblOrder getTblOrder() {
		return tblOrder;
	}

	public void setTblOrder(TblOrder tblOrder) {
		this.tblOrder = tblOrder;
	}

	@Override
	public String toString() {
		return "TblOrderdetail [uuid=" + uuid + ", goodsuuid=" + goodsuuid + ", price=" + price + ", orderuuid="
				+ orderuuid + ", num=" + num + ", surplus=" + surplus + ", tblGoods=" + tblGoods + ", tblOrder="
				+ tblOrder + "]";
	}


}