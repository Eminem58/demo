package com.example.demo.web;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Test {
	private static ServerSocket serverSocket;

	public static void main(String[] args) {
		try {
			// 服务器监听端口号8080
			serverSocket = new ServerSocket(8080);

			// 等待接收请求，这是一个阻塞的方法，当请求到来的时候才会继续向下执行
			Socket socket = serverSocket.accept();

			// 获取请求内容
			InputStream is = socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(is, "UTF-8");

			// 输出请求内容
			while (true) {
				System.out.print((char) reader.read());

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
