package cn.knightzz.minis.test;

import cn.knightzz.minis.ClassPathXmlApplicationContext;
import cn.knightzz.minis.domain.User;

/**
 * @author 王天赐
 * @title: ApplicationTest
 * @projectName geek-mini-spring
 * @description:
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-05-24 10:14
 */
public class ApplicationTest {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring-application.xml");
        User user = (User) applicationContext.getBean("user");
        System.out.println(user);
    }
}
