package cn.erp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
	
	public static Integer convertStringToInt(String parameter){
		 Integer num=null;
		 try{
			 num= Integer.parseInt(parameter);
		 }
		 catch(Exception e){
			 throw new RuntimeException(ErrorCode.castExceptionMsg);
		 }
		 System.out.println(num);
		 return num;
	}
	
	
		//如果得到的null值就直接返回，如果是空值就转换成null，其余直接返回得到的值
		public static String convertEmptyToNull(String parameter){
			System.out.println(parameter);
			if(parameter!=null){
				if(parameter.isEmpty()){
					return null;
				}
			}
		    return parameter;
		}
		
		//Long转String
		public static String convertLongToString(Long parameter){
			String num=null;			
			if(parameter==null)
				return null;
			else if(parameter!=null){
				num=Long.toString(parameter);
			}
			return num;	
		}
		
		//Integer转String
		public static String convertIntToString(Integer parameter){
			String num=null;			
			if(parameter==null)
				return null;
			else if(parameter!=null){
				num=Integer.toString(parameter);
			}
			return num;	
		}
		
		//Double转String
		public static String convertDoubleToString(Double parameter){
			String num=null;			
			if(parameter==null)
				return null;
			else if(parameter!=null){
				num=Double.toString(parameter);
			}
			return num;	
		}
		
		//如果为空转为1
		public static Integer convertToOneIfNull(String parameter){
			return (parameter==null?1:convertStringToInt(parameter));
		}
		
		//把字符串转换为int （如果字符串为null或‘’ 返回null）
		public static Integer convertStringToIntIfNotNull(String parameter){
			Integer num=null;			
			if(parameter==null ||parameter.isEmpty())
				return null;
			else if(parameter!=null &&!parameter.isEmpty()){
				num=Integer.parseInt(parameter);
			}
			return num;			
		}
	
		//把字符串转换为long （如果字符串为null或‘’ 返回null）
		public static Long convertStringToLongIfNotNull(String parameter){
			Long num=null;			
			if(parameter==null ||parameter.isEmpty())
				return null;
			else if(parameter!=null &&!parameter.isEmpty()){
				num=Long.parseLong(parameter);
			}
			return num;			
		}
		
		//把字符串转换为double （如果字符串为null或‘’ 返回null）
		public static Double convertStringToDoubleIfNotNull(String parameter){
			Double num=null;			
			if(parameter==null ||parameter.isEmpty())
				return null;
			else if(parameter!=null &&!parameter.isEmpty()){
				num=Double.parseDouble(parameter);
			}
			return num;			
		}
		
		//日期转换为时间戳
		public static Long convertStringToTimestamps(String parameter) {
			Long timestamps=null;	
			if(parameter==null ||parameter.isEmpty())
				return null;
			else if(parameter!=null &&!parameter.isEmpty()){			
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
				try {
					timestamps=simpleDateFormat.parse(parameter).getTime()/1000;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
			return timestamps;		
		}
		
		//时间戳转换为日期
		public static String timeStampToDate(String seconds,String format) {  
	        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
	            return "";  
	        }  
	        if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";  
	        SimpleDateFormat sdf = new SimpleDateFormat(format);  
	        return sdf.format(new Date(Long.valueOf(seconds+"000")));  
	    }

}
