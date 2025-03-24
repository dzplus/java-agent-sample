package life.duanzhang.agentmain;

import java.lang.instrument.Instrumentation;

public class AgentMainTest {

    public static void agentmain(String agentArgs, Instrumentation inst){

        System.out.println("打印全部加载的类：");

        Class[] allLoadedClasses = inst.getAllLoadedClasses();

        for (Class allLoadedClass : allLoadedClasses) {

            System.out.println(allLoadedClass.getName());

        }

    }
}
