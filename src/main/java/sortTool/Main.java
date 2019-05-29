package sortTool;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args){
        ApplicationContext app = new ClassPathXmlApplicationContext("conf/spring-service.xml");
        Sort s = (Sort) app.getBean("sort");
        System.out.println(s.getClass());
        s.sort();
    }

}
