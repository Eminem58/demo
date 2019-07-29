package com.example.demo.spring.ioc.test;

import org.springframework.stereotype.Component;

@Component
public class MoveDisk implements Usb {
	@Override
	public String useUsb() {
		// TODO Auto-generated method stub
		return "连接【移动硬盘】读取数据";
	}

}
