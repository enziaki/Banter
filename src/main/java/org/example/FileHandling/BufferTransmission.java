package org.example.FileHandling;

import org.example.utils.Connection;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class BufferTransmission {

  final static int BUFFER = 65536; //64 KB buffer size

  public static void getFile(int port) throws Exception{
    int read;
    int totalread = 0;
    byte[] buffer = new byte[BUFFER];

    InputStream clInputStream = Connection.listenSocket(port).getInputStream();
    while((read = clInputStream.read(buffer)) != -1){
      totalread += read;
    }
  }

  public static void sendFile(int port, String Ip, String filePath) throws Exception{
    FileInputStream fileInput = new FileInputStream(filePath);
    Socket serverSocket = Connection.sendSocket(port, Ip);
    OutputStream sockOutStream = serverSocket.getOutputStream();
    byte[] buffer = new byte[BUFFER];
    int read;
    int readtotal = 0;
    while((read = fileInput.read(buffer)) != -1){
      sockOutStream.write(buffer, 0, read);
      readtotal += read;
    }
    sockOutStream.close();
    fileInput.close();
    serverSocket.close();
  }
}