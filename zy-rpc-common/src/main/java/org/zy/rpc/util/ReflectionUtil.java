package org.zy.rpc.util;


import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;
import java.util.function.IntFunction;

public class ReflectionUtil {

    public static <T>T newInstance(Class<T> clazz, Object ...args){
        try {
            return clazz.getConstructor().newInstance(args);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static Method[] getPublicMethods(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        ArrayList<Method> publicMethods = new ArrayList<>();
        for (Method method : methods) {
            if (Modifier.isPublic(method.getModifiers())) {
                publicMethods.add(method);
            }
        }
        return publicMethods.toArray(Method[]::new);
    }

    public static <T>T invoke(Object o, Method method, Object[] args){
        try {
            if (Modifier.isStatic(method.getModifiers()))
                return (T) method.invoke(null, args);
            return (T) method.invoke(o, args);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
