package cn.erp.util;

import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import cn.erp.pojo.TblOrder;

public class ExcelUtil {
	/**
	 * 导出用户的所有列表到excel
	 * @param goodsList 商品列表
	 * @param outputStream 输出流
	 */
	/*public static void exportUserExcel(List<ExcelGoodsByTimeAndGys> goodsList, ServletOutputStream outputStream) {
		try {
			//1、创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			//1.1、创建合并单元格对象
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 4);//起始行号，结束行号，起始列号，结束列号
			
			//1.2、头标题样式
			HSSFCellStyle style1 = createCellStyle(workbook, (short)16);
			
			//1.3、列标题样式
			HSSFCellStyle style2 = createCellStyle(workbook, (short)13);
			
			//2、创建工作表
			HSSFSheet sheet = workbook.createSheet("商品列表");
			//2.1、加载合并单元格对象
			sheet.addMergedRegion(cellRangeAddress);
			//设置默认列宽
			sheet.setDefaultColumnWidth(25);
			
			//3、创建行
			//3.1、创建头标题行；并且设置头标题
			HSSFRow row1 = sheet.createRow(0);
			HSSFCell cell1 = row1.createCell(0);
			//加载单元格样式
			cell1.setCellStyle(style1);
			cell1.setCellValue("商品列表");
			
			//3.2、创建列标题行；并且设置列标题
			HSSFRow row2 = sheet.createRow(1);
			String[] titles = {"商品名","商品ID"};
			for(int i = 0; i < titles.length; i++){
				HSSFCell cell2 = row2.createCell(i);
				//加载单元格样式
				cell2.setCellStyle(style2);
				cell2.setCellValue(titles[i]);
			}
			
			//4、操作单元格；将用户列表写入excel
			if(goodsList != null){
				for(int j = 0; j < goodsList.size(); j++){
					HSSFRow row = sheet.createRow(j+2);
					HSSFCell cell11 = row.createCell(0);
					cell11.setCellValue(goodsList.get(j).getGoodsname());
					HSSFCell cell12 = row.createCell(1);
					cell12.setCellValue(goodsList.get(j).getGoodsuuid());
					HSSFCell cell13 = row.createCell(2);
					cell13.setCellValue(goodsList.get(j).getDept());
					HSSFCell cell14 = row.createCell(3);
					cell14.setCellValue(goodsList.get(j).isGender()?"男":"女");
					HSSFCell cell15 = row.createCell(4);
					cell15.setCellValue(goodsList.get(j).getEmail());
				}
			}
			//5、输出
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * 创建单元格样式
	 * @param workbook 工作簿
	 * @param fontSize 字体大小
	 * @return 单元格样式
	 */
	private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontSize) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		//创建字体
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗字体
		font.setFontHeightInPoints(fontSize);
		//加载字体
		style.setFont(font);
		return style;
	}
	
	
	public static void exportUserExcel(List<TblOrder> list, ServletOutputStream outputStream) {
		try {
			//1、创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			//1.1、创建合并单元格对象
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 6);//起始行号，结束行号，起始列号，结束列号
			
			//1.2、头标题样式
			HSSFCellStyle style1 = createCellStyle(workbook, (short)16);
			
			//1.3、列标题样式
			HSSFCellStyle style2 = createCellStyle(workbook, (short)13);
			
			//2、创建工作表
			HSSFSheet sheet = workbook.createSheet("用户列表");
			//2.1、加载合并单元格对象
			sheet.addMergedRegion(cellRangeAddress);
			//设置默认列宽
			sheet.setDefaultColumnWidth(20);
			
			//3、创建行
			//3.1、创建头标题行；并且设置头标题
			HSSFRow row1 = sheet.createRow(0);
			HSSFCell cell1 = row1.createCell(0);
			//加载单元格样式
			cell1.setCellStyle(style1);
			cell1.setCellValue("订单列表");
			
			//3.2、创建列标题行；并且设置列标题
			HSSFRow row2 = sheet.createRow(1);
			String[] titles = {"订单号","供应商","制单人","制单时间","订单商品总量","订单商品总额","订单状态"};
			for(int i = 0; i < titles.length; i++){
				HSSFCell cell2 = row2.createCell(i);
				//加载单元格样式
				cell2.setCellStyle(style2);
				cell2.setCellValue(titles[i]);
			}
			System.out.println(list);
			//4、操作单元格；将用户列表写入excel
			if(list != null){
				for(int j = 0; j < list.size(); j++){
					HSSFRow row = sheet.createRow(j+2);
					HSSFCell cell11 = row.createCell(0);
					cell11.setCellValue(Common.convertEmptyToNull(list.get(j).getOrdernum()));
					HSSFCell cell12 = row.createCell(1);
					cell12.setCellValue(Common.convertEmptyToNull(list.get(j).getTblSupplier().getName()));
					HSSFCell cell13 = row.createCell(2);
					cell13.setCellValue(Common.convertLongToString(list.get(j).getCreater()));
					HSSFCell cell14 = row.createCell(3);
					cell14.setCellValue(Common.convertLongToString(list.get(j).getCreatetime()));
					HSSFCell cell15 = row.createCell(4);
					cell15.setCellValue(Common.convertIntToString(list.get(j).getTotalnum()));
					HSSFCell cell16 = row.createCell(5);
					cell16.setCellValue(Common.convertDoubleToString(list.get(j).getTotalprice()));
					HSSFCell cell17 = row.createCell(6);
					cell17.setCellValue(Common.convertIntToString(list.get(j).getType()));
					
				}
			}
			//5、输出
			workbook.write(outputStream);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
