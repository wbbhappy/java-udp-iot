package com.jiyin.iot;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

public class UDPService {
    private final static int PORT = 9000;
    public static void main(String[] args) {
        System.out.println("ServiceLog: listening......");
        try {
            DatagramSocket socket = new DatagramSocket(PORT);
            while (true) {
                try {
                    DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
                    socket.receive(request);                                            //获取发送端dp
                    String daytime = new Date().toString();                             //系统时间
                    byte[] data = daytime.getBytes("ASCII");
                    String data2 = new String(request.getData(),0,request.getLength()); //拼接字符串, 发送值
                    DatagramPacket response = new DatagramPacket(data, data.length, request.getAddress(), request.getPort());
                    socket.send(response);				                                //发送返回值
                    System.out.println("ServiceLog:" + daytime + " " + request.getAddress() + "\n data:" + data2);
                    if(data2.equals("hello"))
                        System.out.println("i'm full!!!!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

