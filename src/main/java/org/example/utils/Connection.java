package org.example.utils;

import java.net.ServerSocket;
import java.net.Socket;

public class Connection {

  public static Socket listenSocket(int port) throws Exception{
      System.out.println("Listening....");
      ServerSocket listenSocket = new ServerSocket(port);
      Socket acceptListen = listenSocket.accept();
      return acceptListen;
  }

  public static Socket sendSocket(int port, String Ip) throws Exception{
    Socket acceptConn = new Socket(Ip, port);
    System.out.println("Opened a Read socket on IP: " + Ip + " and port: " + port);
    return acceptConn;
  }

}