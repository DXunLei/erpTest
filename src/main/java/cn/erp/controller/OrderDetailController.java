package cn.erp.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.erp.dto.OrderDetailExample;
import cn.erp.pojo.TblOrder;
import cn.erp.pojo.TblOrderdetail;
import cn.erp.service.OrderDetailService;
import cn.erp.service.OrderService;
import cn.erp.util.Common;

@Controller
public class OrderDetailController {
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/order/in/inDetailList.do")
	public String SelectDetail(HttpServletRequest request,HttpServletResponse response) {
		
		String uuid=request.getParameter("uuid");
		System.out.println(uuid);
		OrderDetailExample orderDetailExample=new OrderDetailExample();
		List<TblOrderdetail> list=orderDetailService.selectById(Common.convertStringToLongIfNotNull(uuid));
		List<TblOrder> orderList=orderService.selectById(Common.convertStringToLongIfNotNull(uuid));
		//TblOrder tblOrder=list.get(0).getTblOrder();
		System.out.println("******************");
		System.out.println(list.size());
		
		System.out.println("******************");
		request.setAttribute("list", list);	
		//request.setAttribute("tblOrder", tblOrder);	
		request.setAttribute("orderList", orderList);
		return "order/in/inDetailList";
	}
	
}
