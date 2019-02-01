package com.jiyin.thread;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
	private Socket socket;
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		OutputStream outputStream = null;
		PrintWriter printWriter = null;
		try {
			//根据输入输出流与客户连接
			inputStream = socket.getInputStream(); 						//输入流，接收客户端传递的信息
			inputStreamReader = new InputStreamReader(inputStream);		//将字节流转为字符流（提高效率）
			bufferedReader = new BufferedReader(inputStreamReader);		//加入缓冲区
			String temp = null; 										//临时变量
			String info = ""; 											//用于存储接收的信息
			//读取客户端接收的信息并输出
			while((temp = bufferedReader.readLine()) != null) {
				info += temp;
				System.out.println("服务端接收到客户端信息："
						+ info + ",当前客户端ip为："
						+ socket.getInetAddress().getHostAddress() 
						+ "， 主机名：" + socket.getInetAddress().getHostName());
			}
			//获取输出流，向客户端发送信息
			outputStream = socket.getOutputStream();
			//将输出流转为打印流
			printWriter = new PrintWriter(outputStream);
			//连接成功并把信息输入到客户端
			printWriter.println("您好，我是服务端，已接收到您的信息");
			//刷新
			printWriter.flush();
			//关闭Socket输出流
			socket.shutdownOutput();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {	//关闭资源
			printWriter.close();
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				inputStreamReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
