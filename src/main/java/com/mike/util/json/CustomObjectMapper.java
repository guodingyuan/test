package com.mike.util.json;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 
 * 类名称：CustomObjectMapper<br>
 * 类描述：解决SpringMVC使用@ResponseBody返回json时，日期格式默认显示为时间戳的问题。需配合<mvc:message-converters>使用 <br>
 * 创建人：qinjiaxue<br>
 * 创建时间：2016年5月10日 上午10:26:36<br>
 * @version v1.0
 *
 */
public class CustomObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = 2250726936200792007L;

    public CustomObjectMapper() {
        super();
        //设置null转换""  
        getSerializerProvider().setNullValueSerializer(new NullSerializer());
        //设置日期转换yyyy-MM-dd HH:mm:ss  
        setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 
     * 类名称：NullSerializer<br>
     * 类描述：null的JSON序列 <br>
     * 创建人：qinjiaxue<br>
     * 创建时间：2016年5月10日 上午10:40:18<br>
     * @version v1.0
     *
     */
    private class NullSerializer extends JsonSerializer<Object> {
        public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
            jgen.writeString("");
        }
    }
}
