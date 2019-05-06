package service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        ApplicationContext app = new ClassPathXmlApplicationContext("conf/spring-service.xml");
        Crawler c = (Crawler) app.getBean("picCrawler");
        System.out.println(c.getClass());
        String temp = "https://www.baidu.com";

        List list = c.analysisDoc(c.getConnection(temp));
        for(int i=0;i<list.size();i++){
            c.writeIn(list.get(i),"logs/p" + i + ".jpg");
        }
    }

}
