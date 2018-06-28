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

import cn.erp.dto.DepExample;
import cn.erp.dto.EmpExample;
import cn.erp.pojo.TblDep;
import cn.erp.pojo.TblEmp;
import cn.erp.service.DepService;
import cn.erp.service.EmpService;
import cn.erp.util.Common;
import cn.erp.util.JsonUtils;
import cn.erp.util.session.Constants;

@Controller
public class EmpController {
	
	@Autowired
	private EmpService empService;
	@Autowired
	private DepService depservice;
	//请求登录页面
	@RequestMapping(value="/employee/loginIn.do")
	public String login() {
		return "login";
	}
	//处理登录请求
	@RequestMapping(value="/employee/login.do")
	public String goLogin(HttpServletRequest request,HttpServletResponse response) {
		//接收参数
		String username=request.getParameter("username");
		String pwd=request.getParameter("pwd");
		//调用service层
		EmpExample empExample=new EmpExample();
		empExample.setUsername(username);
		empExample.setPwd(pwd);
		TblEmp tblEmp=empService.login(empExample);
		
		if(tblEmp==null) {
			request.setAttribute("error", "用户名或者密码错误");
			return "login";
		}
		//session
		return "main";
		
	}
	
	
//	@RequestMapping(value="/employee/list.do")
//	public String listByPage(HttpServletRequest request,HttpServletResponse response) {
//		//获取参数
//		/*request.getParameter("username");
//		String username2=Common.convertEmptyToNull("username");
//		String name2=Common.convertEmptyToNull("name");
//		String tele2=Common.convertEmptyToNull("tele");
//		String gender2=Common.convertEmptyToNull("gender");
//		String logintimes2=Common.convertEmptyToNull("logintimes");
//		String depUuid2=Common.convertEmptyToNull("depUuid");*/
//		
//		String username=request.getParameter("username");
//		String name=request.getParameter("name");
//		
//		int pageNum=Common.convertToOneIfNull(request.getParameter("pageNum"));
//		int pageSize=Constants.PAGE_SIZE;
//		//把参数封装到EmpExample对象中
//		EmpExample empExample=new EmpExample();
//		empExample.setUsername(username);
//		empExample.setName(name);
//		//仅对紧跟其后的方法生效
//		PageHelper.startPage(pageNum, pageSize);
//		List<TblEmp> list=empService.selectByName(empExample);
//		
//		PageInfo pageInfo=new PageInfo(list);
//		
//		DepExample depExample=new DepExample();
//		List<TblDep> depList=depservice.selectList(depExample);
//
//		request.setAttribute("pageInfo", pageInfo);
//		request.setAttribute("list", list);
//		request.setAttribute("depList", depList);
//		return "employee/list";
//		
//	}
	
	@RequestMapping(value="/employee/list.do",method=RequestMethod.GET)
	public ModelAndView check(HttpServletRequest request,HttpServletResponse response) {	
		ModelAndView modelAndView=new ModelAndView("employee/list");//view   return 
		modelAndView.addObject("aaname", "yuyu");                      //model   request.setAttribute()
		return modelAndView;
	}
	
	//获取列表数据
    @ResponseBody
	@RequestMapping(value = "/employee/listJson.do",method=RequestMethod.POST)
    public  void listJson(HttpServletRequest   request, HttpServletResponse response) throws IOException{
	
    	//获取参数
    	String username=request.getParameter("username");
		if(username.isEmpty()){
			username=null;
		}
		System.out.println(request.getParameter("pageNum"));
	    int pageNum=Common.convertToOneIfNull(request.getParameter("pageNum"));
		
		
		System.out.println(request.getParameter("username"));
	  
		int pageSize=Constants.PAGE_SIZE;
	  //把参数封装在EmpExample;
		
		EmpExample  empExample=new EmpExample();
		empExample.setUsername(username);
		//只队紧跟其后的方法有效
		PageHelper.startPage(pageNum, pageSize);
		List<TblEmp> list=empService.selectList( empExample);
		
		PageInfo pageInfo=new PageInfo(list);
		DepExample  depExample=new DepExample();
	
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

	
	@RequestMapping(value="/employee/add.do")
	public String add() {
	
		return "employee/input";
	}
	
}
