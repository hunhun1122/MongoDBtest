package com.gwm.monodb.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionUtils {

	private static Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);  
	  
   
    
    
    /**
     * 获取父类的泛型类
     * @param clazz
     * @return
     */
    public static <T> Class<T> getSuperClassGenricType(final Class clazz) {  
        return getSuperClassGenricType(clazz, 0);  
    }  
  
    
    public static Class getSuperClassGenricType(final Class clazz, final int index) {  
  
        Type genType = clazz.getGenericSuperclass();  
  
        if (!(genType instanceof ParameterizedType)) {  
            logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");  
            return Object.class;  
        }  
  
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();  
  
        if (index >= params.length || index < 0) {  
            logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "  
                    + params.length);  
            return Object.class;  
        }  
        if (!(params[index] instanceof Class)) {  
            logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");  
            return Object.class;  
        }  
  
        return (Class) params[index];  
    }  
  
   
}
