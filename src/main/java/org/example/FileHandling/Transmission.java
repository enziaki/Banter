package org.example.FileHandling;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Transmission {

  //Sending the file
  public static void ConnectionSend(DataInputStream dataInput, DataOutputStream dataOutput, String Ip, int port, String path){
    try {
      Socket readSocket = new Socket(Ip, port);
      dataInput = new DataInputStream(readSocket.getInputStream());
      dataOutput = new DataOutputStream(readSocket.getOutputStream());
      System.out.println("Initiated a SEND Thread!");

      SendFile.sendFile(path, dataOutput);
      dataInput.close();
      dataOutput.close();
      readSocket.close(); // new addition to readSocket

    }catch(Exception i){i.printStackTrace();}
  }

  // Receiving the File
 public static void ConnectionRecv(DataInputStream dataInput, DataOutputStream dataOutput, int port, String path){
    try (ServerSocket recvSocket = new ServerSocket(port)){
      Socket acceptRecvSocket = recvSocket.accept();
      dataInput = new DataInputStream(acceptRecvSocket.getInputStream());
      dataOutput = new DataOutputStream(acceptRecvSocket.getOutputStream());
      System.out.println("Initiated a RECEIVE Thread");

      RecvFile.recvFile(path, dataInput);
      dataInput.close();
      dataOutput.close();
      acceptRecvSocket.close();
    }catch (Exception e){e.printStackTrace();}
  }
 }