package com.jiyin.thread;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	/**
	 * Socket的客户端
	 */
	public static void main(String[] args) {
		Socket socket = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		OutputStream outputStream = null;
		PrintWriter printWriter = null;
		try {
			//1.建立客户端Socket
			socket = new Socket("localhost", 8006);
			//输出流，向服务端发送信息
			outputStream = socket.getOutputStream();
			//将输出流转为成打印流
			printWriter = new PrintWriter(outputStream);
			//把信息发送到服务端中输出
			printWriter.println("我是客户");
			//刷新
			printWriter.flush();
			//关闭Socket输出流
			socket.shutdownOutput();
			//输入流，接收服务端信息
			inputStream = socket.getInputStream();
			//把字节流转为字符流
			inputStreamReader = new InputStreamReader(inputStream);
			//加入到缓冲区
			bufferedReader = new BufferedReader(inputStreamReader);
			String temp = null;
			String info = "";
			while ((temp = bufferedReader.readLine()) != null) {
				info += temp;
				System.out.println("客户端接收服务端发送信息：" + info);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
