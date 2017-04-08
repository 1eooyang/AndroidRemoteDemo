package com.mmyz;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class RemoteRegister {
    public static void register(){
        try {
            Class<?> clazz = Class.forName(IRemoteConfig.PACKAGE_NAME + "." + IRemoteConfig.CLASS_NAME);
            Method method = clazz.getDeclaredMethod(IRemoteConfig.METHOD_NAME);
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
