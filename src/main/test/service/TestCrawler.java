package service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sortTool.AutoSort;

public class TestCrawler {
    @Test
    public void testCrawler() throws Exception {
        ApplicationContext app = new ClassPathXmlApplicationContext("conf/spring-service.xml");
        AutoSort as = (AutoSort) app.getBean("autoSortImpl");
        System.out.println(as.getClass());
        as.sort();
    }
}
