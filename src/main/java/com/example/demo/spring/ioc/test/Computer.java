package com.example.demo.spring.ioc.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Computer {
	@Autowired
	private MoveDisk md;
	@Autowired
	private UDisk u;
	private Usb usb;

	public Computer() {
		super();
	}

	/**
	 * 构造注入
	 */
	public Computer(Usb usb) {
		this.usb = usb;
	}

	/**
	 * 设值注入
	 */
	public void setUsb(Usb usb) {
		this.usb = usb;
	}

	public String readData() {
		return usb.useUsb();
	}

	public String readData2() {
		return md.useUsb() + u.useUsb();
	}
}
