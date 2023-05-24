package cn.knightzz.minis;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 王天赐
 * @title: ClassPathXmlApplicationContext
 * @projectName geek-mini-spring
 * @description:
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-05-24 09:29
 */
public class ClassPathXmlApplicationContext {

    /**
     * 存储封装Bean的id和对应的ClassName的BeanDefinition
     */
    List<BeanDefinition> beanDefinitions = new ArrayList<>();

    /**
     * 存储实例化的bean
     */
    Map<String, Object> singletons = new HashMap<>();

    /**
     * 实例化
     *
     * @param fileName 描述bean的XML文件地址
     */
    public ClassPathXmlApplicationContext(String fileName) {
        this.readXml(fileName);
        this.instanceBeans();
    }

    /**
     * 利用将beanDefinitionList中的对应Bean实例化
     */
    private void instanceBeans() {

        beanDefinitions.forEach(beanDefinition -> {

            try {
                Class<?> aClass = Class.forName(beanDefinition.getClassName());
                Object instance = aClass.newInstance();
                singletons.put(beanDefinition.getId(), instance);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * 读取描述Bean的XML文件
     *
     * @param fileName
     */
    private void readXml(String fileName) {
        SAXReader saxReader = new SAXReader();
        try {
            URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
            Document document = saxReader.read(xmlPath);
            Element rootElement = document.getRootElement();
            // 对配置的每一个 Bean 进行处理
            for (Element element : (List<Element>) rootElement.elements()) {
                String beanId = element.attributeValue("id");
                String className = element.attributeValue("class");
                BeanDefinition beanDefinition = new BeanDefinition(beanId, className);
                beanDefinitions.add(beanDefinition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getBean(String beanName) {
        return singletons.get(beanName);
    }

}
