package cn.erp.util;

import java.text.SimpleDateFormat;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


/**
 * Utils - JSON
 * 
 */
public final class JsonUtils {



	/** ObjectMapper */
	// private static ObjectMapper mapper = new ObjectMapper()
	// .setSerializationInclusion(Include.NON_NULL);
	private static ObjectMapper objectMapper= null;;
	static{
		 objectMapper = new ObjectMapper();  
	    
	    //序列化的时候序列对象的所有属性  
	    objectMapper.setSerializationInclusion(Include.ALWAYS);  
	      
	    //反序列化的时候如果多了其他属性,不抛出异常  
	    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);  
	      
	    //如果是空对象的时候,不抛异常  
	    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);  
	      
	    //取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式  
	    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);  
	    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")) ;
		
	}
			

	/**
	 * 将对象转换为JSON字符串
	 * 
	 * @param value
	 *            对象
	 * @return JSOn字符串
	 */
	public static String toJson(Object value) {
		try {
			return objectMapper.writeValueAsString(value);
		} catch (Exception e) {
			throw new RuntimeException("转换异常");
		}
	}

	/**
	 * 将JSON字符串转换为对象
	 * 
	 * @param json
	 *            JSON字符串
	 * @param valueType
	 *            对象类型
	 * @return 对象
	 */
	public static <T> T toObject(String json, Class<T> valueType) {
		Assert.hasText(json);
		Assert.notNull(valueType);
		try {
			return objectMapper.readValue(json, valueType);
		} catch (Exception e) {
			throw new RuntimeException("转换异常");
		}
		
	}

	/**
	 * 将JSON字符串转换为对象
	 * 
	 * @param json
	 *            JSON字符串
	 * @param typeReference
	 *            对象类型
	 * @return 对象
	 */
	public static <T> T toObject(String json, TypeReference<?> typeReference) {
		Assert.hasText(json);
		Assert.notNull(typeReference);
		try {
			return objectMapper.readValue(json, typeReference);
		} catch (Exception e) {
			throw new RuntimeException("转换异常");
		}
		
	}

	/**
	 * 将JSON字符串转换为对象
	 * 
	 * @param json
	 *            JSON字符串
	 * @param javaType
	 *            对象类型
	 * @return 对象
	 */
	public static <T> T toObject(String json, JavaType javaType) {
		Assert.hasText(json);
		Assert.notNull(javaType);
		try {
			return objectMapper.readValue(json, javaType);
		} catch (Exception e) {
			throw new RuntimeException("转换异常");
		}
		
	}

	public static JavaType getCollectionType(Class<?> collectionClass,
			Class<?>... elementClasses) {
		return objectMapper.getTypeFactory().constructParametricType(collectionClass,
				elementClasses);
	}

}