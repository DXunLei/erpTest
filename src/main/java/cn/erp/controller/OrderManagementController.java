package cn.erp.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.erp.dto.OrderDetailExample;
import cn.erp.dto.OrderExample;
import cn.erp.pojo.TblGoods;
import cn.erp.pojo.TblGoodstype;
import cn.erp.pojo.TblSupplier;
import cn.erp.service.GoodsService;
import cn.erp.service.GoodsTypeService;
import cn.erp.service.OrderDetailService;
import cn.erp.service.OrderService;
import cn.erp.service.SupplierService;
import cn.erp.util.Common;
import cn.erp.util.JsonUtils;

@Controller
public class OrderManagementController {
	
		@Autowired
		private SupplierService supplierService;
		@Autowired
		private GoodsTypeService goodsTypeService;
		@Autowired
		private GoodsService goodsService;
		@Autowired
		private OrderService orderService;
		
		//进入订单管理页面
		@RequestMapping(value="/order/in/input.do",method=RequestMethod.GET)
		public ModelAndView checkin(HttpServletRequest request,HttpServletResponse response) {	
			//把供应商列表获取出来
			List<TblSupplier> suppliersList=supplierService.selectList();
			//在页面通过el表达式获取供应商信息
			ModelAndView modelAndView=new ModelAndView("order/in/input");//view   return 
			//放到model里
			modelAndView.addObject("suppliersList",suppliersList);
			return modelAndView;
		}
		
		@ResponseBody
		@RequestMapping(value="/order/in/inputListJson.do",method=RequestMethod.POST)
		public void inApproveList(HttpServletRequest request,HttpServletResponse response) throws IOException {
			//传参
			String supplierUuid=request.getParameter("supplier");
			System.out.println("*******************************************");
			System.out.println(supplierUuid);
			System.out.println("*******************************************");
			List<TblGoodstype> list=goodsTypeService.selectList(Common.convertStringToLongIfNotNull(supplierUuid));
			System.out.println("*******************************************");
			for (TblGoodstype tblGoodstype : list) {
				System.out.println(tblGoodstype.getName());
			}
			System.out.println("*******************************************");
			String json=JsonUtils.toJson(list);
			System.out.println(json);
			OutputStream out;
	        response.setContentType("text/javascript;charset=UTF-8");
			out = response.getOutputStream();
			out.write(json.getBytes("UTF-8"));
			out.flush();
			out.close();
			
		}
		
		@ResponseBody
		@RequestMapping(value="/order/in/selectGoodsJson.do",method=RequestMethod.POST)
		public void selectGoods(HttpServletRequest request,HttpServletResponse response) throws IOException {
			//传参
			String goodsTypeUuid=request.getParameter("goodsType");
			System.out.println("*******************************************");
			System.out.println(goodsTypeUuid);
			System.out.println("*******************************************");
			List<TblGoods> list=goodsService.selectList(Common.convertStringToLongIfNotNull(goodsTypeUuid));
			System.out.println("*******************************************");
			for (TblGoods tblGoods : list) {
				System.out.println(tblGoods.getName());
			}
			System.out.println("*******************************************");
			String json=JsonUtils.toJson(list);
			System.out.println(json);
			OutputStream out;
	        response.setContentType("text/javascript;charset=UTF-8");
			out = response.getOutputStream();
			out.write(json.getBytes("UTF-8"));
			out.flush();
			out.close();	
		}
		
		@ResponseBody
		@RequestMapping(value="/order/in/selectGoodsPriceJson.do",method=RequestMethod.POST)
		public void selectGoodsprice(HttpServletRequest request,HttpServletResponse response) throws IOException {
			//传参
			String goodsUuid=request.getParameter("goods");
			System.out.println("*******************************************");
			System.out.println(goodsUuid);
			System.out.println("*******************************************");
			TblGoods list=goodsService.selectByPrimaryKey(Common.convertStringToLongIfNotNull(goodsUuid));
			
			String json=JsonUtils.toJson(list);
			System.out.println(json);
			OutputStream out;
	        response.setContentType("text/javascript;charset=UTF-8");
			out = response.getOutputStream();
			out.write(json.getBytes("UTF-8"));
			out.flush();
			out.close();	
		}
		
		//保存一条数据
		@RequestMapping(value="/order/in/saveList.do")
		public void saveList(HttpServletRequest request,HttpServletResponse response) {
			String supplierUuid=request.getParameter("supplier");
			System.out.println(supplierUuid);
			System.out.println("*************************************");
			String goodsUuid=request.getParameter("goods");
			System.out.println(goodsUuid);
			String num=request.getParameter("num");
			System.out.println(num);
			String price=request.getParameter("goodsInPrices");
			System.out.println(price);
			String surplus=request.getParameter("surplus");
			System.out.println(surplus);
			
			OrderExample orderExample=new OrderExample();
			orderExample.setSupplierUuid(Common.convertStringToLongIfNotNull(supplierUuid));
			
			OrderDetailExample orderDetailExample=new OrderDetailExample();
			orderDetailExample.setGoodsUuid(Common.convertStringToLongIfNotNull(goodsUuid));
			orderDetailExample.setNum(Common.convertStringToIntIfNotNull(num));
			orderDetailExample.setPrice(Common.convertStringToDoubleIfNotNull(price));
			orderDetailExample.setSurplus(Common.convertStringToDoubleIfNotNull(surplus));
			
			orderService.insertOrderAndOrderDetail(orderExample,orderDetailExample);
			
			
		}
		
		
}
