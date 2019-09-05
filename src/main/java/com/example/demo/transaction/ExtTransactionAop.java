package com.example.demo.transaction;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
 * 〈事务AOP〉
 *
 * @author jinbiao
 * @create 2019.06.17
 */
@Aspect
public class ExtTransactionAop {
    @Autowired
    private ExtTransactionUtil extTransactionUtil;

    @AfterThrowing("execution(* com.example.demo..*.*(..))")
    public void exception() {
        extTransactionUtil.rollback();
    }

    @Around("execution(* com.example.demo..*.*(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (!existTransaction(proceedingJoinPoint)) {
            return;
        }
        extTransactionUtil.begin();
        proceedingJoinPoint.proceed();
        extTransactionUtil.commit();
    }


    /**
     * [检查是否加了注解]
     *
     * @param
     * @return
     * @author 金彪
     * @date 2019年07月22日
     * @version 1.0
     */
    public Boolean existTransaction(ProceedingJoinPoint proceedingJoinPoint) throws NoSuchMethodException {
        // 获取目标对象
        Class<?> classTarget = proceedingJoinPoint.getTarget().getClass();
        // 获取方法名称
        String methodName = proceedingJoinPoint.getSignature().getName();
        // 获取方法参数类型
        Class[] parameterTypes = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterTypes();
        // 获取目标对象方法
        Method method = classTarget.getMethod(methodName, parameterTypes);
        ExtTransaction declaredAnnotation = method.getDeclaredAnnotation(ExtTransaction.class);
        if (declaredAnnotation == null) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        /**
         * 1.声明事务注解
         * 2.定义Aop处理类
         * 2.1、加环绕通知处理、异常处理
         * 3.事务工具类
         * 4.检查是否加了注解
         * 5.开启事务提交事务回滚事务
         */
    }
}
