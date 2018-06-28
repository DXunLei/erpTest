<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<jsp:useBean id="timestamp" class="java.util.Date"/> <!-- 通过jsp:userBean标签引入java.util.Date日期类 -->
	<jsp:setProperty name="timestamp" property="time" value="${b.createtime}"/> <!-- 使用jsp:setProperty标签将时间戳设置到Date的time属性中 --> 
 	<%String base=request.getContextPath() ;%>
<link href="<%=base%>/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=base%>/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="<%=base%>/js/Calendar.js"></script>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
	});
	
	/* function timestampToTime(timestamp) {
        var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        Y = date.getFullYear() + '-';
        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        D = date.getDate() + ' ';
       
        return Y+M+D;
    }
	
	/* h = date.getHours() + ':';
    m = date.getMinutes() + ':';
    s = date.getSeconds();
    return Y+M+D+h+m+s; */ 
	function query(pageNum){
		
		$("#gender").val($("#sex"));
		$("#pageNum").val(pageNum);
		$("#depUuid").val($("#dep"));
		$("#forms").submit();
	}
    
    function exportExcel(){
    	var base= $("#PageContext").val();
        var url=base+"/order/in/export.do";
    	window.open(url);  
    }
</script>

<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">进货订单管理</span>
		</div>
	</div>
	<div class="content-text">
	
		<form action="<%=base%>/order/in/inList.do" method="post" id="forms"> 
		
		<input id="PageContext" type="hidden" value="${pageContext.request.contextPath}" /> 
		
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td height="30">订单状态</td> 
						<td> 
							<select class="kuan" style="width:113px" name="type">
								<option value="-1">----请-选-择----</option>
								<option value="0">未审核</option>
								<option value="1">已审核</option>
								<option value="2">正在采购</option>
								<option value="3">入库完成</option>
							</select> 
						</td>
						<td>下单人:</td>
						<td><input type="text" size="14" name="creater"/></td>
						<td>总量:</td>
						<td><input type="text" size="14" name="begintotalnum"/></td>
						<td>到 </td>
						<td>&nbsp;&nbsp;<input type="text" size="14" name="endtotalnum"/></td>
						<td>
							<a href="<%=base%>/order/in/input.do">
								<img src="<%=base%>/images/can_b_02.gif" border="0" /> 
							</a>
						</td>
					</tr>
					<tr>
						<td>下单时间:</td>
						<td>
							<input type="text" size="14" onfocus="c.showMoreDay=false;c.show(this);" readonly="true" name="createtime"/>
						</td>
						<td>到</td>
						<td>
							<input type="text" size="14" onfocus="c.showMoreDay=false;c.show(this);" readonly="true" name="endtime"/>
						</td>
						<td>总额:</td>
						<td><input type="text" size="14" name="begintotalprice"/></td>
						<td>到</td>
						<td>&nbsp;&nbsp;<input type="text" size="14" name="endtotalprice"/></td>					
						<td><a id="query" onclick="query(1)"> 
							<img src="<%=base%>/images/can_b_01.gif" border="0"/> </a>
						</td>		
					</tr>
				</table>
			</div>
			
			
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(<%=base%>/images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">订单号</td>
						<td width="9%">供应商</td>
						<td width="10%">制单人</td>
						<td width="20%">制单时间</td>
						<td width="10%">订单商品总量</td>
						<td width="12%">订单总金额</td>
						<td width="5%">详情</td>
						<td width="9%">订单状态</td>
					</tr>
					<c:forEach var="b" items="${list}">
					<tr align="center" bgcolor="#FFFFFF">
						<td width="13%" height="30">${b.ordernum}</td>
						<td>${b.tblSupplier.name}</td>
						<td>${b.tblEmp.name}</td>
						<td><fmt:formatDate value="${timestamp}" pattern="yyyy-MM-dd"/></td>
						<td>${b.totalnum}</td>
						<td align="right">${b.totalprice}元</td>
						<td>
							<a href="<%=base%>/order/in/inDetailList.do?uuid=${b.uuid}" class="xiu">详情</a>
						</td>
						<td class="order_show_msg"><c:if test="${b.type==0}">未审核</c:if>
						<c:if test="${b.type==1}">已审核</c:if>
						</td>
					</tr>
					</c:forEach>
					<tr align="center"><td colspan="8">           
                           <a href="#" onclick="query(1)">首页</a>|  
                           <a  href="#" onclick="query(${pageInfo.pageNum-1})">上一页</a>|  
                           <a  href="#" onclick="query(${pageInfo.pageNum+1})">下一页</a>|  
                           <a href="#" onclick="query(${pageInfo.pages})">末页</a>|  
		                   	 	 第${pageInfo.pageNum}页/  
		                     	共${pageInfo.pages}页  
		                  		(${pageInfo.total}条数据）</td>
                  </tr>  
				</table>
				<a onclick="exportExcel()">
				<img src="<%=base%>/images/can_b_03.gif" border="0" />
				</a>
			</div>
			
			<input type="hidden"  id="pageNum" name="pageNum" >
		</form>
	</div>
	<div class="content-bbg"></div>
</div>


	
	
