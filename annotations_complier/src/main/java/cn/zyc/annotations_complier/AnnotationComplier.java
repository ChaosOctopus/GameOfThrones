package cn.zyc.annotations_complier;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

import cn.zyc.annotations.BindPath;

/**
 * Created by zhaoyuanchao on 2019-07-05.
 * 用来替换 utils
 */
@AutoService(Processor.class)  //注册注解处理器
public class AnnotationComplier extends AbstractProcessor {
    //生成文件
    private Filer filer;

    /**
     * 初始化生成文件
     * @param processingEnvironment
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
    }

    /**
     * 声明我们注解处理器要注解哪些对象
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new HashSet<>();
        types.add(BindPath.class.getCanonicalName());
        return types;
    }

    /**
     * 声明注解支持的java版本号
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        System.out.println("1111111");
        //去拿到模块中所用用到BindPath的节点类
        Set<? extends Element> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(BindPath.class);
        //结构化数据
        Map<String,String> map = new HashMap<>();
        //由于都是类节点 所以我们用TypeElement
        for (Element element : elementsAnnotatedWith) {
            TypeElement typeElement = (TypeElement) element;
            //获取注解的值
            String vlaue = typeElement.getAnnotation(BindPath.class).value();
            //获取activity的类名
            String clazzName = typeElement.getQualifiedName().toString();
            map.put(vlaue,clazzName);
        }

        //将获取到的key和value写成类
        if (map.size() > 0 ){
            Writer writer = null;
            //创建文件名，根据时间戳去分类名
            String clazzName = "ActivityUtils"+System.currentTimeMillis();
            //创建java文件
            try {
                JavaFileObject sourceFile = filer.createSourceFile("cn.zyc.annoutils" + "." + clazzName);
                writer = sourceFile.openWriter();
                //往文件写入内容
                writer.write("package cn.zyc.annoutils;\n"+
                        "\n"+
                        "import cn.zyc.westeros.ARouter;\n"+
                        "import cn.zyc.westeros.IRouter;\n"+
                        "\n"+
                        "public class "+ clazzName+" implements IRouter{\n"+
                        "\n"+
                        " @Override\n"+
                        " public void putActivity(){");
                //编列map对象 将所有注解处理器标注的对象 写入文件中
                Iterator<String> iterator = map.keySet().iterator();
                while (iterator.hasNext()){
                    String next = iterator.next();
                    String value = map.get(next);
                    writer.write("ARouter.getInstance().putActivity(\""+next+"\","+
                            value+".class);\n");
                }
                writer.write("}\n}");

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (writer != null){
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return false;
    }
}
