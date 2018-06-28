<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String base=request.getContextPath() ;%>
	
<link href="<%=base%>/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=base%>/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="<%=base%>/js/Calendar.js"></script>
<!--引入ng-app所需的js文件-->
<script type="text/javascript" src="<%=base%>/js/angular.js"></script>
<script type="text/javascript" src="<%=base%>/js/angular-route.min.js"></script>

<script type="text/javascript">
var app = angular.module("myApp",[]);
app.config(['$httpProvider', function ( $httpProvider ) {  
    $httpProvider.defaults.transformRequest = function ( data ) {  
        var str = '';  
        for( var i in data ) {  
            str += i + '=' + data[i] + '&';  
        }  
        return str.substring(0,str.length-1);  
    }  
}]); 

 app.controller("siteCtrl",function($scope,$http,$timeout){
      var timer = null ;
      init();
      function init(){
    	  findList(1);
      }
      function findList(pagenum){
    	$scope.pageInfo = [] ;
  	    var base= $("#PageContext").val();
  		var url=base+"/employee/listJson.do";
  		$scope.data= {
                  username: $("#username").val(),
                  pageNum:pagenum
  		 };
  		$timeout.cancel(timer);
          timer = $timeout(function(){
          	 $http({
          		
          		 url:url,           //请求的url路径  
                   method:'post',    //GET/DELETE/HEAD/JSONP/POST/PUT  
                   data:$scope.data,  
                   headers: {  
                       'Content-Type': 'application/x-www-form-urlencoded'  
                   }
  
                 }).then(function successCallback(response) {
                      $scope.pageInfo  = response.data;
                      console.log($scope.pageInfo);
                })
          },100)
      }
      
      
      $scope.query= function(pagenum){
    	    findList(pagenum);               
      }
})
</script>



<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">员工管理</span>
		</div>
	</div>
	<div class="content-text">
		<form  ng-app="myApp" ng-controller="siteCtrl"   method="post"  id="forms">
		   <input id="PageContext" type="hidden" value="${pageContext.request.contextPath}" />
		
		    <input type="hidden"  id="pageNum" name="pageNum" >
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td height="30">用&nbsp;户&nbsp;名</td>
						<td><input type="text"       name=username  id="username"  size="14" /></td>
						<td>真实姓名</td>
						<td><input type="text" size="14" /></td>
						<td>电&nbsp;&nbsp;&nbsp;&nbsp;话</td>
						<td><input type="text" size="14" /></td>
						<td>性&nbsp;&nbsp;&nbsp;&nbsp;别</td>
						<td>
							<select class="kuan">
								<option value="-1">----请-选-择----</option>
								<option value="1">男</option>
								<option value="0">女</option>
							</select>
						</td>
							
						<td width="70"><a href="<%=base%>/employee/add.do"> <img src="<%=base%>/images/can_b_02.gif" border="0" /> </a></td>
					</tr>
					<tr>
						<td  height="30">电子邮件</td>
						<td><input type="text" size="14" /></td>
						<td>登录时间</td>
						<td>
							<input type="text"  size="14" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"/>
						</td>
						<td>登录时间</td>
						<td>
							<input type="text" size="14" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"/>
						</td>
						<td>部门名称</td>
						<td>
							<select class="kuan">
								<option value="-1">----请-选-择----</option>
								<c:forEach     var="dep"  items="${depList}">
								<option value="${dep.uuid}">${dep.name}</option>
								</c:forEach>
							</select>
						</td>
						<td><a id="query"   ng-click="query(1)"> <img src="<%=base%>/images/can_b_01.gif" border="0" /> </a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(<%=base%>/images/table_bg.gif) repeat-x;">
						<td width="10%" height="30">用户名</td>
						<td width="10%">真实姓名</td>
						<td width="5%">性别</td>
						<td width="12%">出生日期</td>
						<td width="10%">电话</td>
						<td width="12%">电子邮件</td>
						<td width="9%">所属部门</td>
						<td width="16%">最后登录时间</td>
						<td width="16%">操作</td>
					</tr>
				  <tr ng-repeat="c in pageInfo.list">
					 <td>{{c.username}}</td>
                   <td>{{c.name}}</td>
                   <td>{{c.gender}}</td>
                   	
                   	<td>	{{c.birthday*1000 | date:'yyyy-MM-dd'}} </td>
                   	<td>{{c.tele}}</td>
                   	<td>{{c.email}}</td>
                   	<td>{{c.tblDep.name}}</td>
                   	<td>{{c.logintimes}}</td>
                   	<td><img src="<%=base%>/images/icon_3.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="./input.jsp" class="xiu">修改</a>
							</span> 
							<img src="<%=base%>/images/icon_04.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="javascript:void(0)" class="xiu" onclick="showMsg('是否删除该项数据？',318)">删除</a>
							</span></td>
                 </tr>
					<tr>  <td colspan="9" align="center">           
                           <a href="#" ng-click="query(1)">首页</a>|  
                          <a  href="#" ng-click="query(pageInfo.pageNum-1)">上一页</a>|  
                           <a  href="#" ng-click="query(pageInfo.pageNum+1)">下一页</a>|  
                           <a href="#" ng-click="query(pageInfo.pages)">末页</a>|  
		                     第{{pageInfo.pageNum}}页/  
		                     共{{pageInfo.pages}}页  
		                  ({{pageInfo.total}}条数据）</td></tr>
				</table>
			</div>
			
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
