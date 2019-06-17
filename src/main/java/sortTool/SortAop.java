package sortTool;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SortAop {
    SortAop(){
        System.out.println("sort aop start");
    }

    @Pointcut(value = "execution(* sortTool.AutoSort.move(..))")
    public void move(){}

    @Around(value = "move()")
    public Object writeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String newPath = (String)args[0];
        String oldPath = (String)args[1];
        Object rvt = joinPoint.proceed(args);
        System.out.print(oldPath);
        System.out.println("   ========");
        System.out.print("========>   ");
        System.out.print(newPath);
        System.out.println("\r\n\r\n");
        return rvt;
    }





}
