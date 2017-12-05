package com.xqt.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.apache.commons.lang3.StringUtils;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Wangwei
 * Date: 2016/7/15
 * Desc:
 */
public class JacksonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        // 日期格式化，也可以使用注解：@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        objectMapper.setDateFormat(new CustomDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.registerModule(new JodaModule());
    }

    private JacksonUtil(){}

    /**
     * 读取json字符串
     * @param json json串
     * @return 返回mapper
     * @throws IOException
     */
    public static Map<String, Object> readJSON(String json) throws IOException {
        if (StringUtils.isEmpty(json)) {
            return new HashMap<>();
        }
        return objectMapper.readValue(json, Map.class);
    }

    /**
     * 过滤出需要的属性
     * @param obj 需要过滤的对象
     * @param properties 需要过滤的属性
     * @return Object
     */
    public static Object filterIncludeProperties(Object obj, String... properties) throws IOException {
        String str = filterIncludePropToStr(obj,properties);
        return toObject(str);
    }

    /**
     * 过滤出需要的属性
     * @param obj 需要过滤的对象
     * @param properties 需要过滤的属性
     * @return map
     */
    public static Object filterIncludePropertiesToMap(Object obj, String... properties) throws IOException {
        String str = filterIncludePropToStr(obj,properties);
        return toMap(str);
    }

    /**
     * 过滤出需要的属性
     * @param obj 需要过滤的对象
     * @param properties 需要过滤的属性
     * @return Object
     */
    private static String filterIncludePropToStr(Object obj, String... properties) throws IOException {
        FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter", SimpleBeanPropertyFilter.filterOutAllExcept(properties));
        ObjectWriter writer = objectMapper.writer(filters);
        return writer.writeValueAsString(obj);
    }

    @SuppressWarnings("unused")
    public static Object filterValue(Object obj, String property) throws JsonProcessingException {
        PropertyFilter theFilter = new SimpleBeanPropertyFilter() {
            @Override
            public void serializeAsField
                    (Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer)
                    throws Exception {
                if (!writer.getName().equals(property)) {
                    writer.serializeAsField(pojo, jgen, provider);
                }  /*else {
                   int intValue = ((PageBean) pojo).getIntValue();
                    if (intValue >= 0) {
                        writer.serializeAsField(pojo, jgen, provider);
                    }
                }*/
            }
            @Override
            protected boolean include(BeanPropertyWriter writer) {
                return true;
            }
            @Override
            protected boolean include(PropertyWriter writer) {
                return true;
            }
        };
        FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter", theFilter);
        // 日期格式化，也可以使用注解：@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        ObjectWriter writer = objectMapper.writer(filters);
        return toObject(writer.writeValueAsString(obj));
    }

    /**
     * 数组转对象
     * @param json 字节数组
     * @return Object
     */
    public static Object toObject (String json) {
        JsonNode newNode = null;
        try {
            final ObjectReader reader = objectMapper.reader();
            newNode =  reader.readTree(json);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return newNode;
    }

    /**
     * 数组转对象
     * @param json 字节数组
     * @return Object
     */
    public static Object toMap (String json) throws IOException {
        return objectMapper.readValue(json,Map.class);
    }
}
