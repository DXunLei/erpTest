<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%String base=request.getContextPath() ;%>
<link href="<%=base%>/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=base%>/js/jquery-1.8.3.js"></script>

<script type="text/javascript">
	function intToFloat(val){
		return new Number(val).toFixed(2);
	}
	//联动显示商品类型
	$(document).ready(function() {
		$("#supplier").change(function(){
			var base= $("#PageContext").val();
			console.log(base);
			var supplier=$("#supplier").val();
			var url=base+"/order/in/inputListJson.do";
			   //ajax		
			    $.ajax({
                  url:url,
                  type:"post",
                  dataType:"json",
                  data:{
                    'supplier':supplier
                },
            success : function(data) {
            	console.log(data)
       
				$(".goodsType").empty();
				$(".goodsType").append("<option value='-1'>请选择</option>");
                for(var i=0;i<data.length;i++){
                    $(".goodsType").append("<option value = "+data[i].uuid+">"+data[i].name+"</option>");
                }
            }    
         }); 		   
	 });
		
		//联动显示商品名称
		$("#goodsType").live("change",function(){	
			var base= $("#PageContext").val();
			console.log(base);
			var goodsType=$("#goodsType").val();
			var url=base+"/order/in/selectGoodsJson.do";
			   //ajax		
			    $.ajax({
                  url:url,
                  type:"post",
                  dataType:"json",
                  data:{
                    'goodsType':goodsType
                },
            success : function(data) {
            	console.log(data);
				$(".goods").empty();
				$(".goods").append("<option value='-1'>请选择</option>");
                for(var i=0;i<data.length;i++){
                    $(".goods").append("<option value = "+data[i].uuid+">"+data[i].name+"</option>");
                   
                }           
            }
	    });
	 });
		 //联动显示商品进货单价	
		$("#goods").live("change",function(){	
			var base= $("#PageContext").val();
			console.log(base);
			var goods=$("#goods").val();
			var url=base+"/order/in/selectGoodsPriceJson.do";
			   	//ajax		
			    $.ajax({
                  url:url,
                  type:"post",
                  dataType:"json",
                  data:{
                    'goods':goods
                },
            success : function(data) {
            	console.log(data);
				document.getElementById('goodsInPrices').value=data.inprice;
				
				var num = $(".num").val();
				console.log(num);
 				//获取当前价格
 				var price=data.inprice;
 				console.log(price);
 				var total=intToFloat(num*price);
 				console.log(total);
 				document.getElementById('total').value=total;
 				total();
            }
               
	    });
	 }); 
		//点击保存超链接向后台传入表单数据
		$(".save").click(function() {
			var base= $("#PageContext").val();
			console.log(base);
			var supplier=$("#supplier").val();//供应商uuid
			var goodsType=$("#goodsType").val();//商品类型uuid
			var goods=$("#goods").val();//商品uuid
			var num=$("#num").val();//商品进货数量
			var goodsInPrices=$("#goodsInPrices").val();//商品进价
			var surplus=$("#total").val();//商品合计
			var url=base+"/order/in/saveList.do";
			   	//ajax		
			    $.ajax({
                  url:url,
                  type:"post",
                  dataType:"json",
                  data:{              		
                	'supplier':supplier,
                	'goodsType':goodsType,
                   	'goods':goods,
                   	'num':num,
                   	'goodsInPrices':goodsInPrices,
                   	'surplus':surplus,
                }            
	    	});
		});
		 
		//修改商品数量，事件绑定为点击任意键盘数字按钮
		$(".num").live("keyup",function(event){
			//事件激活方式为任意键盘数字按钮，避免用户输入非法数字
			if(event.keyCode>=48 && event.keyCode<=57 || event.keyCode>=96 && event.keyCode<=105 || event.keyCode == 8){
				//获取当前输入数量值
				var num = $(this).val();
				//获取当前价格
				var price = $($(this).parent().next().children()[0]).val();
				$(this).parent().next().next().text(intToFloat(num*price)+" 元");
	
				return true;
			}
			return false;
		});
		 
		/* //添加新商品
		$("#add").click(function(){
			//设置不能修改供应商
			$("#supplier").attr("disabled","disabled");
			//设置已存在的所有select全部不可点击
			$("#goodsType").attr("disabled","disabled");
			$("#goods").attr("disabled","disabled");
			
			//发送ajax提交，将供应商id与当前已经使用的类别对应商品2个id传递到后台，并将其过滤，过滤完毕的数据传递回页面
			var goodsTypeObjs = $(".goodsType");
			var goodsObjs = $(".goods");
			var jsonParam = {"gm.goodsType.supplier.uuid":$("#supplier").val()};
			var hasUuids = "";
			for(i = 0;i<goodsTypeObjs.length;i++){
				hasUuids = hasUuids + $(goodsTypeObjs[i]).val();
				hasUuids = hasUuids + ":";
				hasUuids = hasUuids + $(goodsObjs[i]).val();
				if(i != goodsTypeObjs.length -1){
					hasUuids = hasUuids + ",";
				}
			}
			jsonParam["hasUuids"]= hasUuids;
				
				//动态添加一个tr行
				//创建tr组件
				var $tr = $("<tr></tr>");
				
				//创建td组件，生成商品类别select
				var typeSelectStr = "<select class='goodsType' style='width:200px'>";
				for(i = 0;i<4;i++){
					typeSelectStr+="<option value='";
					typeSelectStr+=111;
					typeSelectStr+="'>";
					typeSelectStr+="类别名称"+i;
					typeSelectStr+="</option>";
				}
				typeSelectStr += "</select>";
				var $td1 = $("<td height='30'>"+typeSelectStr+"</td>");
				$tr.append($td1);
				
				//创建td组件，生成商品select
				var goodsSelectStr = "<select name='gmUuids' class='goods' style='width:200px'>";
				for(i = 0;i<4;i++){
					goodsSelectStr+="<option value='";
					goodsSelectStr+=123;
					goodsSelectStr+="'>";
					goodsSelectStr+="新商品名"+i;
					goodsSelectStr+="</option>";
				}
				goodsSelectStr += "</select>";
				var $td2 = $("<td>"+goodsSelectStr+"</td>");
				$tr.append($td2);
				
				//创建td组件，生成商品数量input，默认值1
				var $td3 = $("<td>&nbsp;<input name='nums' type='text' value='1' class='num order_num' style='width:67px;border:1px solid black;text-align:right;padding:2px' /></td>");
				$tr.append($td3);
				
				var $td4 = $("<td style='padding-left:4px'><input name='prices' type='text' value='"+222+"' class='prices order_num' style='width:93px;border:1px solid black;text-align:right;padding:2px' /> 元</td>");
				$tr.append($td4);
				
				var $td5 = $("<td class='total' align='right'>"+222+" 元</td>");
				$tr.append($td5);
				
				var $td6 = $("<td>&nbsp;&nbsp;<a class='deleteBtn delete xiu' value='"+112+"'>删除</a>|<a class='deleteBtn save xiu' value='"+112+"'>保存</a></td>");
				$tr.append($td6);
				
				//在最后一个tr对象前添加该tr组件
				$("#finalTr").before($tr);
				//注意：新添加的元素是否具有动态事件激活能力
				
				//获取后台数据是否还有下一个可用的商品，判断添加按钮是否显示
				if("Y" == "N"){
					//将添加按钮设置为不可显示
					$("#add").css("display","none");
				}
				total();
		}); 
		  */
		 
		//求总和的方法
		function total(){
			  var calcTotal=function(table,column){//合计，表格对象，对哪一列进行合计，第一列从0开始
 					var trs=table.getElementsByTagName('tr');
  				var start=1,//忽略第一行的表头
      			end=trs.length-1;//忽略最后合计的一行
  				var total='';
  				console.log(total);
			        for(var i=start;i<end;i++){
			            var td=trs[i].getElementsByTagName('td')[column];
			            var t=parseFloat(td.innerHTML);
			            if(t)total+=t;
			        }
			        console.log(total);
			        total += '<span>&nbsp;元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>';
			        console.log(total);
			        trs[end].getElementsByTagName('td')[column].innerHTML=total;
			        console.log(total);
			    };
			    calcTotal(document.getElementById('order'),4);
		}
    					
					
	
	
});
</script>

<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">订单管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="" method="post">
		
		<input id="PageContext" type="hidden" value="${pageContext.request.contextPath}" /> 
		
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td height="24">&nbsp;</td>
					</tr>
					<tr>
						<td width="68px" height="30">供应商：</td>
						<td width="648px">
						
							 <select class="kuan" style="width:190px" id="supplier" name="supplier">
							    <option value="-1">请选择</option>
							 	<c:forEach var="s" items="${suppliersList}">
								<option value="${s.uuid}">${s.name}</option>
								</c:forEach>
							</select>
							
						</td>
						<td height="30">
							<a id="add"><img src="<%=base%>/images/can_b_02.gif" border="0" /> </a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table id="order" width="100%" border="1" cellpadding="0" cellspacing="0" >
					<tr align="center"
						style="background:url(<%=base%>/images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">商品类别</td>
						<td width="25%">商品名称</td>
						<td width="10%">采购数量</td>
						<td width="15%">单价</td>
						<td width="15%">合计</td>
						<td width="10%">操作</td>
					</tr>
					
					<tr align="center" bgcolor="#FFFFFF">
						<td height="30">
							<select class="goodsType" id="goodsType"  name="goodsType"  style="width:200px">
								<option value="-1">请选择</option>
							</select>
						</td>
						<td>
							<select class="goods" id="goods" name="goods" style="width:200px">
								<option value="-1">请选择</option>
							</select>
						</td>
						<td><input id="num" name="nums" class="num order_num" style="width:67px;border:1px solid black;text-align:right;padding:2px" type="text" value="1"/></td>
						<td><input id="goodsInPrices" name="prices" class="prices goodsInPrices" style="width:93px;border:1px solid black;text-align:right;padding:2px" type="text" value="" readonly="readonly"/> 元</td>
						<td><input id="total" name="surplus" class="total" style="width:67px;border:0px;text-align:right;padding:2px" type="text" value=""/> 元</td>
						<td>
							<a class="deleteBtn delete xiu" value="4">删除</a>|<a href="#" class="saveBtn save xiu">保存</a>
						</td>
					</tr>
					
					<tr id="finalTr" align="center"
						style="background:url(<%=base%>/images/table_bg.gif) repeat-x;">
						<td height="30"></td>
						<td height="30"></td>
						<td height="30"></td>
						<td height="30" align="right">总&nbsp;计:&nbsp;</td>
						<td id="all" width="16%" align="right"></td>
						<td>&nbsp;</td>
					</tr>
				</table>
					
				<div class="order-botton">
				
				<div style="margin:1px auto auto 1px;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td>
					    	<a href="javascript:void(0)" id="submitOrder"><img src="<%=base%>/images/order_tuo.gif" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="<%=base%>/images/order_tuo.gif" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="<%=base%>/images/order_tuo.gif" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</div>
		</form>
	</div>
	
	<div class="content-bbg"></div>
</div>
