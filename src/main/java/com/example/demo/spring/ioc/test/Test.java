package com.example.demo.spring.ioc.test;

import com.example.demo.DemoApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
	private static ApplicationContext ac;

	public static void main(String[] args) {
		/**
		 * 设值注入
		 */
		/*
		 * Computer cp = new Computer(); cp.setUsb(new UDisk());
		 * System.out.println(cp.readData());
		 */
		/**
		 * 构造注入
		 */
		/*
		 * Computer cp = new Computer(new MoveDisk());
		 * System.out.println(cp.readData());
		 */
		/**
		 * 从xml文件拿到spring容器中的bean
		 */
		/*
		 * ac = new ClassPathXmlApplicationContext("spring-config.xml"); Computer cp =
		 * ac.getBean(Computer.class); System.out.println(cp.readData2());
		 */
		/**
		 * 从注解拿到spring容器中的bean
		 */
		ac = new AnnotationConfigApplicationContext(DemoApplication.class);
		Computer cp = ac.getBean(Computer.class);
		System.out.println(cp.readData2());
	}
}
