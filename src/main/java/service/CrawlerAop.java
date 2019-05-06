package service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CrawlerAop {

    private String className = "";

    CrawlerAop(){
        System.out.println("****CrawlerAop****");
    }

    @Pointcut(value = "execution(* service.Crawler.getConnection(String)) && args(url)",argNames = "url")
    public void getCon(String url){}

    @Pointcut(value="execution(* service.Crawler.analysisDoc(..))")
    public void analysis(){}

    @Pointcut(value = "execution(* service.Crawler.writeIn(Object,String)) && args(o,path)",argNames = "o,path")
    public void write(Object o,String path){}


    @Before(value = "getCon(url)",argNames = "url")
    public void start(String url){
        System.out.println("connection url : " + url );
    }

    @Before(value = "write(o,path)",argNames = "o,path")
    public void logUrl(Object o,String path){
        System.out.println((String)o);

    }

    @Around(value = "analysis()")
    public Object aroundAnalysis(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("before around ");
        Object obj = joinPoint.proceed();
        if(className == ""){
            className = obj.getClass().getName();
            System.out.println("class name " + className);
        }
        System.out.println("after around ");
        return obj;
    }










}
