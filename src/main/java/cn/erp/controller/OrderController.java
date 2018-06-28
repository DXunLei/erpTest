package cn.erp.controller;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.erp.dto.OrderExample;
import cn.erp.pojo.TblEmp;
import cn.erp.pojo.TblOrder;
import cn.erp.service.OrderService;
import cn.erp.util.Common;
import cn.erp.util.ExcelUtil;
import cn.erp.util.session.Constants;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	//导出表格
	@RequestMapping(value="/order/in/export.do")
	public  void export(HttpServletRequest   request, HttpServletResponse response){
		OrderExample  orderExample=new OrderExample();
		List<TblOrder> list=orderService.selectList(orderExample);
		//导出
				try {
					//HttpServletResponse response = ServletActionContext.getResponse();
					response.setContentType("application/x-execl");
					response.setHeader("Content-Disposition", "attachment;filename=" + new String("aa.xls"));
					ServletOutputStream outputStream = response.getOutputStream();
					
					ExcelUtil.exportUserExcel(list,outputStream);
					System.out.println("导出成功");
					if(outputStream != null){
						outputStream.close();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
		
	}
	
	@RequestMapping(value="/order/in/inList.do")
	public String listByPage(HttpServletRequest request,HttpServletResponse response) {
		
		String name=request.getParameter("creater");
		String createtime=request.getParameter("createtime");
		String endtime=request.getParameter("endtime");
		
		String begintotalnum=request.getParameter("begintotalnum");
		String endtotalnum=request.getParameter("endtotalnum");
		 
		String begintotalprice=request.getParameter("begintotalprice");
		String endtotalprice=request.getParameter("endtotalprice");
	
		int pageNum=Common.convertToOneIfNull(request.getParameter("pageNum"));
		int pageSize=Constants.PAGE_SIZE;
		TblEmp tblEmp=new TblEmp();
		tblEmp.setName(name);
		OrderExample orderExample=new OrderExample();
		orderExample.setTblEmp(tblEmp);
		
		orderExample.setCreatetime(Common.convertStringToTimestamps(createtime));
		orderExample.setEndtime(Common.convertStringToTimestamps(endtime));
		
		orderExample.setBegintotalnum(Common.convertStringToIntIfNotNull(begintotalnum));
		orderExample.setEndtotalnum(Common.convertStringToIntIfNotNull(endtotalnum));
		
		orderExample.setBegintotalprice(Common.convertStringToDoubleIfNotNull(begintotalprice));
		orderExample.setEndtotalprice(Common.convertStringToDoubleIfNotNull(endtotalprice));
		System.out.println("********************************************************************");
		//仅对紧跟其后的方法生效
		PageHelper.startPage(pageNum, pageSize);
		List<TblOrder> list=orderService.selectByName(orderExample);
		
		System.out.println(list.size()+"******************************************************");
		PageInfo pageInfo=new PageInfo(list);
	
		request.setAttribute("pageInfo", pageInfo);
		
		request.setAttribute("list", list);
		return "order/in/inList";
	}
	
	
	
	
}


