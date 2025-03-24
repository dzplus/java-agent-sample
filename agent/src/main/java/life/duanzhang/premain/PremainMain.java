package life.duanzhang.premain;

import java.lang.instrument.Instrumentation;

public class PremainMain {

    /**
     * 注意，这个premain方法签名是Java Agent约定的，不要随意修改
     * @param agentArgs
     * @param instrumentation
     */
    public static void premain(String agentArgs, Instrumentation instrumentation) {
        instrumentation.addTransformer(new MyTransformer());
    }

//    /**
//     * 注意，这个agentmain方法签名是Java Agent约定的，不要随意修改
//     * @param agentArgs
//     * @param instrumentation
//     */
//    public static void agentmain(String agentArgs, Instrumentation instrumentation) {
//        instrumentation.addTransformer(new MyTransformer());
//    }

//    Agent-Class： xxxx
//    Can-Redefine-Classes：true
//    Can-Retransform-Classes：true
}
