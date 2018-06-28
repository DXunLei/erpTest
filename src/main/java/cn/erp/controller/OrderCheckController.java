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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.erp.dto.OrderDetailExample;
import cn.erp.dto.OrderExample;
import cn.erp.pojo.TblEmp;
import cn.erp.pojo.TblOrder;
import cn.erp.pojo.TblOrderdetail;
import cn.erp.service.OrderDetailService;
import cn.erp.service.OrderService;
import cn.erp.util.Common;
import cn.erp.util.JsonUtils;
import cn.erp.util.session.Constants;
@Controller
public class OrderCheckController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailService orderDetailService;
	
	//进入采购审批页面
	@RequestMapping(value="/order/in/inApproveList.do",method=RequestMethod.GET)
	public ModelAndView checkin(HttpServletRequest request,HttpServletResponse response) {	
		ModelAndView modelAndView=new ModelAndView("order/in/inApproveList");//view   return 
		modelAndView.addObject("zzname", "yuyu");                      //model   request.setAttribute()
		return modelAndView;
	}
	
	//采购审批页面查询所有订单记录
	@ResponseBody
	@RequestMapping(value="/order/in/inApproveListJson.do",method=RequestMethod.POST)
	public void inApproveList(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//传参
		String empName=request.getParameter("empName");
		if(empName.isEmpty()){
			empName=null;
		}
		String createtime=request.getParameter("createtime");
		String endtime=request.getParameter("endtime");
		
		String begintotalnum=request.getParameter("begintotalnum");
		String endtotalnum=request.getParameter("endtotalnum");
		
		String begintotalprice=request.getParameter("begintotalprice");
		String endtotalprice=request.getParameter("endtotalprice");
		
		System.out.println(request.getParameter("pageNum"));
		System.out.println(request.getParameter("empName"));
	
		int pageNum=Common.convertToOneIfNull(request.getParameter("pageNum"));
		int pageSize=Constants.PAGE_SIZE;
		//建立对象
		TblEmp tblEmp=new TblEmp();
		tblEmp.setName(empName);
		
		OrderExample orderExample=new OrderExample();
		orderExample.setTblEmp(tblEmp);
		
		orderExample.setCreatetime(Common.convertStringToTimestamps(createtime));
		orderExample.setEndtime(Common.convertStringToTimestamps(endtime));
		
		orderExample.setBegintotalnum(Common.convertStringToIntIfNotNull(begintotalnum));
		orderExample.setEndtotalnum(Common.convertStringToIntIfNotNull(endtotalnum));
		
		orderExample.setBegintotalprice(Common.convertStringToDoubleIfNotNull(begintotalprice));
		orderExample.setEndtotalprice(Common.convertStringToDoubleIfNotNull(endtotalprice));
		
		//只队紧跟其后的方法有效
		PageHelper.startPage(pageNum, pageSize);
		List<TblOrder> list=orderService.selectByName(orderExample);
		PageInfo pageInfo=new PageInfo(list);
		
		String json=JsonUtils.toJson(pageInfo);
		System.out.println(list);
		System.out.println(json);
		OutputStream out;
        response.setContentType("text/javascript;charset=UTF-8");
		out = response.getOutputStream();
		out.write(json.getBytes("UTF-8"));
		out.flush();
		out.close();
		
	}
	
	
	//进入审核页面
	@RequestMapping(value="/order/in/inApprove.do",method=RequestMethod.GET)
	public ModelAndView inApprove(HttpServletRequest request,HttpServletResponse response) {	
		ModelAndView modelAndView=new ModelAndView("order/in/inApprove");//view   return 
		String uuid=request.getParameter("uuid");
		System.out.println("************************");
		System.out.println(Common.convertStringToLongIfNotNull(uuid));
		System.out.println("************************");
		//建立对象
		
	    List<TblOrderdetail> list=orderDetailService.selectById(Common.convertStringToLongIfNotNull(uuid));
			
		List<TblOrder> orderList=orderService.selectById(Common.convertStringToLongIfNotNull(uuid));
		
		for (TblOrder tblOrder : orderList) {
			System.out.println(tblOrder.getUuid());
		}
		
		//TblOrder tblOrder=list.get(0).getTblOrder();
		System.out.println("************************");
		
		System.out.println("************************");
		modelAndView.addObject("list", list);
		modelAndView.addObject("orderList", orderList);
		return modelAndView;
	}
	
	//通过&驳回
	@RequestMapping(value="/order/in/passAndReject.do",method=RequestMethod.GET)
	public ModelAndView passAndReject(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView=new ModelAndView("order/in/inApproveList");
		Long uuid=Common.convertStringToLongIfNotNull(request.getParameter("uuid"));		
		orderService.updateType(uuid);		
		
		return modelAndView;
	}
	
	

/*	
	@ResponseBody
	@RequestMapping(value="/order/in/inApprove.do",method=RequestMethod.GET)
	public void inApproveJson(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		String uuid=request.getParameter("uuid");
		System.out.println(uuid);
		System.out.println("111111111111111111111111111111111111111111");
		System.out.println(request.getParameter("pageNum"));	
		int pageNum=Common.convertToOneIfNull(request.getParameter("pageNum"));
		int pageSize=Constants.PAGE_SIZE;
		//建立对象
		OrderDetailExample orderDetailExample=new OrderDetailExample();
		orderDetailExample.setUuid(Common.convertStringToLongIfNotNull(uuid));
		System.out.println("22222222222222222222222222222222222222222222");
		//只队紧跟其后的方法有效
		PageHelper.startPage(pageNum, pageSize);
		
		List<TblOrderdetail> list=orderDetailService.selectById(orderDetailExample);
		
		TblOrder tblOrder=list.get(0).getTblOrder();
		
		System.out.println(list.size());
		PageInfo pageInfo=new PageInfo(list);
		
		String json=JsonUtils.toJson(pageInfo);
		String json2=JsonUtils.toJson(tblOrder);
		
			   json=json.substring(0, json.length()-1)+",\"tblOrder\":"+json2+"}";
		System.out.println(list);
		System.out.println(json);
		OutputStream out;
        response.setContentType("text/javascript;charset=UTF-8");
		out = response.getOutputStream();
		out.write(json.getBytes("UTF-8"));
		out.flush();
		out.close();
		
	}
	*/
	
}
