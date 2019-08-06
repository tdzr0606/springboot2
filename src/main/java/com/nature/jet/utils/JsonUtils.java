package com.nature.jet.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtils
{
    static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    public static final String JSON_NORMAL = "NORMAL";
    public static final String JSON_EXCEMPTY = "EXCEMPTY";
    public static final String JSON_EXCNULL = "EXCNULL";

    public static String toArrayJson(List list, String type)
    {
        String temp = "";
        if(list.size() < 1)
            return temp;
        switch(type)
        {
            case JsonUtils.JSON_NORMAL:
                return JsonUtils.toJson(list, null);
            case JsonUtils.JSON_EXCEMPTY:
                return JsonUtils.toJson(list, JsonInclude.Include.NON_EMPTY);
            case JsonUtils.JSON_EXCNULL:
                return JsonUtils.toJson(list, JsonInclude.Include.NON_NULL);
            default:
                return temp;
        }
    }

    public static String toJson(Object object, JsonInclude.Include includeType)
    {
        MappingJsonFactory f = new MappingJsonFactory();
        ObjectMapper mapper = f.getCodec();
        if(includeType != null)
        {
            mapper.setSerializationInclusion(includeType);
        }
        StringWriter sw = new StringWriter();
        try
        {
            JsonGenerator generator = f.createGenerator(sw);
            generator.writeObject(object);
            generator.close();
        }
        catch(Exception e)
        {
            logger.error("error", e);
            return "";
        }
        return sw.toString();
    }

    public static <T> T parseJson(String jsonString, Class<T> c)
    {
        if(StringUtils.isBlank(jsonString))
            return null;
        MappingJsonFactory f = new MappingJsonFactory();
        ObjectMapper mapper = f.getCodec();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try
        {
            JsonParser parser = f.createParser(jsonString);
            return parser.readValueAs(c);
        }
        catch(Exception e)
        {
            logger.error("Error", e);
            return null;
        }
    }

    public static Map<String, Object> parseJSONMap(String jsonStr) throws IOException
    {
        if(StringUtils.isBlank(jsonStr))
            return null;
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(jsonStr, Map.class);
        return map;
    }

    /**
     * 把JSON字符串转化为List
     *
     * @param jsonString 要转化的字符串
     * @param type       对应类的Class
     * @param <T>
     * @return
     */
    public static <T> List<T> JSONArray(String jsonString, Class<T> type)
    {
        MappingJsonFactory f = new MappingJsonFactory();
        ObjectMapper mapper = f.getCodec();
        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, type);
        try
        {
            if(jsonString != null && jsonString.length() > 20)
            {
                return mapper.readValue(jsonString, javaType);
            }
            else
            {
                return new ArrayList<T>();
            }
        }
        catch(Exception e)
        {
            logger.error("Error", e);
            return new ArrayList<T>();
        }
    }

}