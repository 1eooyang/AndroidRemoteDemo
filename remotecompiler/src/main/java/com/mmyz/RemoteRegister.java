package com.mmyz;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class RemoteRegister {
    public static void register(){
        try {
            Class<?> clazz = Class.forName(Config.PACKAGE_NAME + "." + Config.CLASS_NAME);
            Method method = clazz.getDeclaredMethod(Config.METHOD_NAME);
            method.invoke(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
