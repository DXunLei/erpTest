package cn.erp.dto;

public class OrderDetailExample {
	
	private Long orderUuid;
	
	private Long goodsUuid;
	
	private Integer num;
	
	private Double price;
	
	private Double surplus;

	public Long getOrderUuid() {
		return orderUuid;
	}

	public void setOrderUuid(Long orderUuid) {
		this.orderUuid = orderUuid;
	}

	public Long getGoodsUuid() {
		return goodsUuid;
	}

	public void setGoodsUuid(Long goodsUuid) {
		this.goodsUuid = goodsUuid;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getSurplus() {
		return surplus;
	}

	public void setSurplus(Double surplus) {
		this.surplus = surplus;
	}


	
	
}
