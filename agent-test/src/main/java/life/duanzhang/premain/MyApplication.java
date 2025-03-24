package life.duanzhang.premain;

import java.util.concurrent.TimeUnit;

/**
 * 某个jvm进程
 */
public class MyApplication {

    //需添加 -javaagent:agent/target/agent-1.0-SNAPSHOT-jar-with-dependencies.jar 到Jvm参数
    public static void main(String[] args) {
        while (true) {
            testPrint();
        }
    }

    private static void testPrint() {
        System.out.println("这是我第 " + (System.currentTimeMillis() / 1000) + " 次打印");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }
}