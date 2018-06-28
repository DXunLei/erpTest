<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String base=request.getContextPath() ;%>
<link href="<%=base%>/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=base%>/js/jquery-1.8.3.js"></script>
<!--引入ng-app所需的js文件-->
<script type="text/javascript" src="<%=base%>/js/angular.js"></script>
<script type="text/javascript" src="<%=base%>/js/angular-route.min.js"></script>


<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">进货审核</span>
		</div>
	</div>
	<div class="content-text">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<c:forEach var="o" items="${orderList}">
						<tr>
						<td height="30">企业名称:</td>
						<td class="order_show_msg">${o.tblSupplier.name}</td>
						<td>下单时间:</td>
						<td class="order_show_msg">${o.createtime}</td>
						<td>下 单 人:</td>
						<td class="order_show_msg">${o.tblEmp.name}</td>
						<td>订 单 号:</td>
						<td class="order_show_msg" colspan="2">${o.ordernum}</td>
					</tr>
					</c:forEach>
					<c:forEach var="o" items="${orderList}">
					<tr>
						<td height="30">订单类别:</td>
						<td class="order_show_msg"><c:if test="${o.ordertype==1}">采购</c:if></td>
						<td>订单状态:</td>
						<td class="order_show_msg"><c:if test="${o.type==0}">未审核</c:if>
						<c:if test="${o.type==1}">已审核</c:if>
						</td>
						<td>商品总量:</td>
						<td class="order_show_msg">${o.totalnum}</td>
						<td>订单总额:</td>
						<td class="order_show_msg">${o.totalprice} 元</td>
					</tr>
					</c:forEach>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<center style="text-decoration:underline;font-size:16px; font-weight:bold; font-family:"黑体";">&nbsp;&nbsp;&nbsp;&nbsp;订&nbsp;&nbsp;单&nbsp;&nbsp;明&nbsp;&nbsp;细&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<br/>
				<table width="100%" border="1" cellpadding="0" cellspacing="0" id="table">
					<tr align="center"
						style="background:url(<%=base%>/images/table_bg.gif) repeat-x;">
						<td width="20%" height="30">商品类别</td>
						<td width="20%">商品名称</td>
						<td width="20%">购买数量</td>
						<td width="20%">进货单价</td>
						<td width="20%">合计</td>
					<c:forEach var="s" items="${list}">
					<tr align="center" bgcolor="#FFFFFF">
						<td height="30">${s.tblGoods.tblGoodstype.name}</td>
						<td>${s.tblGoods.name}</td>
						<td>${s.num}</td>
						<td align="right">${s.price} 元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td align="right">${s.surplus} 元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
					</c:forEach>
					<tr align="right">
						<td height="30" width="20%"></td><td height="30" width="20%"></td><td height="30" width="20%"></td>
						<td height="30" width="20%">总计&nbsp;&nbsp;</td>
						<td width="20%">  <span>元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
					</tr>
				</table>
				<script type="text/javascript">
    					var calcTotal=function(table,column){//合计，表格对象，对哪一列进行合计，第一列从0开始
       					var trs=table.getElementsByTagName('tr');
        				var start=1,//忽略第一行的表头
            			end=trs.length-1;//忽略最后合计的一行
        				var total=0;
				        for(var i=start;i<end;i++){
				            var td=trs[i].getElementsByTagName('td')[column];
				            var t=parseFloat(td.innerHTML);
				            if(t)total+=t;
				        }
				        total += '<span>&nbsp;元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>';
				        trs[end].getElementsByTagName('td')[column].innerHTML=total;
				    };
				    calcTotal(document.getElementById('table'),4);
				</script>
				<br/>
				<table width="100%">
				<c:forEach var="o" items="${orderList}">
					<tr align="center">
						<td width="50%">
							<a href="<%=base %>/order/in/passAndReject.do?uuid=${o.uuid}" style="color:#0f0;font-size:20px;padding-top:2px;font-weight:bold;text-decoration:none;width:82px;height:28px;display:block;background:url(<%=base%>/images/btn_bg.jpg)">
								通&nbsp;&nbsp;过
							</a>
						</td>
						<td width="50%">
							<a href="<%=base %>/order/in/passAndReject.do?uuid=${o.uuid}" style="color:#f00;font-size:20px;padding-top:2px;font-weight:bold;text-decoration:none;width:82px;height:28px;display:block;background:url(<%=base%>/images/btn_bg.jpg)">
								驳&nbsp;&nbsp;回
							</a>
						</td>
					</tr>
				</c:forEach>	
				</table>
			</div>
	</div>
	<div class="content-bbg"></div>
</div>
