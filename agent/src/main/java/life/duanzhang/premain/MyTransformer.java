package life.duanzhang.premain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Objects;


/**
 * @description 这个转换器的作用就是 在MyApplication类中找到testPrint方法 然后在testPrint 方法前后各打印一个文案
 * @author duanzhang
 * @time 2022/10/13 17:19
 */
public class MyTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        //java自带的方法不进行处理
        if(className.startsWith("java") || className.startsWith("sun")){
            return classfileBuffer;
        }
        className = className.replace('/', '.');
        // 只处理MyApplication类
        if(!className.endsWith("MyApplication")) {
            return classfileBuffer;
        }
        try {
            //取出池
            ClassPool classPool = ClassPool.getDefault();
            //从池中取出类
            CtClass ctClass = classPool.get(className);
            //从类中取出方法
            CtMethod[] declaredMethods = ctClass.getDeclaredMethods();
            for (CtMethod declaredMethod : declaredMethods) {
                // 只处理testPrint方法
                if(Objects.equals("testPrint", declaredMethod.getName())) {
                    /**
                     * 在方法执行之前加入打印语句
                     */
                    declaredMethod.insertBefore("System.out.println(\"要执行了，\");");
                    /**
                     * 在方法执行之后加入打印语句
                     */
                    declaredMethod.insertAfter("System.out.println(\"执行完了！\");");
                }
            }
            return ctClass.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return classfileBuffer;
    }
}
