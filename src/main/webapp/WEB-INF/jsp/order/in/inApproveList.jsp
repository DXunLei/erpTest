<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
  		var url=base+"/order/in/inApproveListJson.do";
  		$scope.data= {
  				empName: $("#empName").val(),
  				
  				createtime: $("#createtime").val(),
  				endtime: $("#endtime").val(),
  				
  				begintotalnum: $("#begintotalnum").val(),
  				endtotalnum: $("#endtotalnum").val(),
  				
  				begintotalprice: $("#begintotalprice").val(),
  				endtotalprice: $("#endtotalprice").val(),
  				
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
			<span class="page_title">进货审核</span>
		</div>
	</div>
	<div class="content-text">
		<form ng-app="myApp" ng-controller="siteCtrl"   method="post" id="forms">
		   <input id="PageContext" type="hidden" value="${pageContext.request.contextPath}" /> 
		    <input type="hidden"  id="pageNum" name="pageNum" >
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td  height="30">下单时间:</td>
						<td>
							<input id="createtime" name="createtime" type="text" size="14" onfocus="c.showMoreDay=false;c.show(this); " readonly="true"/>
						</td>
						<td>到</td>
						<td>
							&nbsp;&nbsp;<input id="endtime" name="endtime" type="text" size="14" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"/>
						</td>
						<td>总量:</td>
						<td><input id="begintotalnum" name="begintotalnum" type="text" size="14" /></td>
						<td>到 </td>
						<td>&nbsp;&nbsp;<input id="endtotalnum" name="endtotalnum" type="text" size="14" /></td>
					</tr>
					<tr>
						<td>总额:</td>
						<td><input id="begintotalprice" name="begintotalprice" type="text" size="14" /></td>
						<td>到</td>
						<td>&nbsp;&nbsp;<input id="endtotalprice" name="endtotalprice" type="text" size="14" /></td>
						<td>下单人:</td>
						<td><input type="text" size="14" id="empName" name="empName"/></td>
						<td>&nbsp;</td>
						<td>&nbsp;&nbsp;<a id="query" ng-click="query(1)"> 
							<img src="<%=base%>/images/can_b_01.gif" border="0" /> </a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(<%=base%>/images/table_bg.gif) repeat-x;">
						<td width="30%" height="30">订单号</td>
						<td width="12%">供应商</td>
						<td width="10%">制单人</td>
						<td width="20%">制单时间</td>
						<td width="10%">订单商品总量</td>
						<td width="12%">订单总金额</td>
						<td width="6%">审核</td>
					</tr>
				
					<tr align="center" bgcolor="#FFFFFF" ng-repeat="c in pageInfo.list">
						<td width="13%" height="30">{{c.ordernum}}</td>
						<td>{{c.tblSupplier.name}}</td>
						<td>{{c.tblEmp.name}}</td>
						<td>{{c.createtime*1000 | date:'yyyy-MM-dd'}}</td>
						<td>{{c.totalnum}}</td>
						<td align="right">{{c.totalprice}} 元</td>
						<td>						
							<p ng-if="c.type==0"><a href="<%=base %>/order/in/inApprove.do?uuid={{c.uuid}}">审核</a></p>
							<p ng-if="c.type==1"><a href ="javascript:volid(0);" style="text-decoration-line: none">已审核</a></p>							
						</td>
					</tr>
					
					
					<tr>  <td colspan="7" align="center">           
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
