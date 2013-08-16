package com.naonworks.iptms.common;

import java.io.StringWriter;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;


public class CommonUtil {
	/**
	 * 
	 * <pre>
	 * 설명 : json 형태로 변환 
	 * </pre>
	 * 
	 *@param obj
	 *@return
	 *@throws Exception
	 */
	public static String convertObjectToJson(Object obj) throws Exception {
	      StringWriter sw = new StringWriter();   // serialize
	      ObjectMapper mapper = new ObjectMapper(); 
	      MappingJsonFactory jsonFactory = new MappingJsonFactory();
	      mapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
	      JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(sw);
	      mapper.writeValue(jsonGenerator, obj);
	      sw.close();
	      
	      return sw.getBuffer().toString();
	}
}
