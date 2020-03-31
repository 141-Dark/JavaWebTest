package Demos;


import org.junit.Test;

/*
*学习使用ThreadLocal,他是一个泛型类，主要用在一个类上，多个线程访问它时，每个线程都有副本，互不干扰
* 记住泛型类的使用，泛型<>中的可以是定义的接口，类。
* */
public class ThreadLocal_Study {
    @Test
    final public void test(){
        //ThreadLocal也是一个线程（主线程）
        ThreadLocal<String> t1 = new ThreadLocal<String>();
        //因为ThreadLocal<String>，所以只可以设置字符串
        t1.set("Hello");
        //获取
        //t1.remove();将t1中的hello移除

        //创建一个新的线程
        new Thread(){
            public void run(){
                t1.set("内部类");
                System.out.println("内容为： "+t1.get());
            }
        }.start();


        System.out.println(t1.get());
    }
}
