package cn.zyc.westeros;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dalvik.system.DexFile;

/**
 * Created by zhaoyuanchao on 2019-07-05.
 */
public class ARouter {

    private Map<String,Class<? extends Activity>> classMap = new HashMap<>();
    private static ARouter aRouter = new ARouter();
    private Context context;
    private ARouter(){

    }

    public static ARouter getInstance(){
       return aRouter;
    }

    public void init(Application application){
        this.context = application;
        //获取注解处理器所有生成的类,根据包名全部获取出来
        List<String> allAnnotationClasses = getAllAnnotationClasses("cn.zyc.annoutils");
        //反射执行执行类中的方法，加入到集合中
        for (String allAnnotationClass : allAnnotationClasses) {
            try {
                Class<?> aClass = Class.forName(allAnnotationClass);
                //因为注解生成的类都实现了IRouter所以验证下
                if (IRouter.class.isAssignableFrom(aClass)){

                    try {
                        IRouter iRouter = (IRouter) aClass.newInstance();
                        iRouter.putActivity();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private List<String> getAllAnnotationClasses(String pkg) {
        List<String> clazzList = new ArrayList<>();
        String path = null;
        try {
            //获取AKP的完整路径
            path = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir;
            //根据APK的完整路径获取编译后的dex文件
            try {
                DexFile dexFile = new DexFile(path);
                //获取编译后的dex文件中所有的class
                Enumeration<String> entries = dexFile.entries();
                while (entries.hasMoreElements()){
                    //遍历class的所有包名
                    String name = entries.nextElement();
                    //判断包名是否包含 我的注解类生成的包名
                    if (name.contains(pkg)){
                        //加入到集合中
                        clazzList.add(name);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return clazzList;

    }

    public void putActivity(String key,Class clazz){
        if (key != null && clazz != null){
            classMap.put(key,clazz);
        }
    }

    public void JumpActivity(String key, Bundle bundle){
        Class<? extends Activity> aClass = classMap.get(key);
        if (aClass != null){
            Intent intent = new Intent();
            intent.setClass(context,aClass);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (bundle != null){
                intent.putExtra(key, bundle);
            }
            context.startActivity(intent);
        }

    }
}
