package top.sguotao.processor;

import com.google.auto.service.AutoService;

import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import top.sguotao.annotation.Test;

/**
 * 自定义注解处理器
 */
@AutoService(Processor.class)
public class TestProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
    }

    //指定注解处理器支持的Java版本
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    //指定注解处理器支持的注解类型,Test是我们自定义的注解
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(Test.class.getCanonicalName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        // 准备在gradle的控制台打印信息
        Messager messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, "注解处理器开始扫描...");
        messager.printMessage(Diagnostic.Kind.NOTE, "实现@Test注解的class 数量: " + annotations.size());


        // 打印注解
        for (TypeElement te : annotations) {
            for (Element e : roundEnvironment.getElementsAnnotatedWith(te)) {
                String className = e.toString();
                Test classAutoLoad = e.getAnnotation(Test.class);
                messager.printMessage(Diagnostic.Kind.NOTE, "扫描到:" + className + "..." + classAutoLoad);
            }
        }

        //该方法返回ture表示该注解已经被处理, 后续不会再有其他处理器处理; 返回false表示仍可被其他处理器处理.
        return true;
    }
}
