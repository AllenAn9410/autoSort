package service;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExcelAop {

    ExcelAop(){
        System.out.println("****ExcelAop****");
    }

    @Before("execution(* service.Excel.read())")
    public void begain(){
        System.out.println("start");
    }
    @After("execution(* service.Excel.read())")
    public void close(){
        System.out.println("end");
    }
}
