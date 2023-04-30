package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import org.example.FileHandling.*;
import org.example.Help.*;
import org.example.utils.*;

public class Main {
  private static DataOutputStream dataOutput = null;
  private static DataInputStream dataInput = null;

  public static void main(String[] args) throws Exception {
    // defining a port
    int port = 6969;
    // menu for the program
    if(args.length == 0){
      System.out.println("Listening mode not available");
    }
    else if(args.length == 3) {
      if (args[0].equals("--send")) { // Send block calling
        System.out.println(Chunks.displayNomenclature(Chunks.getSize(args[1])));
        Transmission.ConnectionSend(dataInput, dataOutput, args[2], port, args[1]);
      } else if (args[0].equals("--recv")) { // Receive block calling
        Transmission.ConnectionRecv(dataInput, dataOutput, args[2], port, args[1]);
        System.out.println(Chunks.displayNomenclature(Chunks.getSize(args[1])));
      }
      else if(args[0].equals("--buffer-send")){
        System.out.println(Chunks.displayNomenclature(Chunks.getSize(args[1])));
        long startTime = System.currentTimeMillis();
        BufferTransmission.sendFile(port, args[2], args[1]);
        long endTime = System.currentTimeMillis();
        System.out.println("Sent the file in " + (endTime - startTime) + " ms");
      }
      else if(args[0].equals("--buffer-recv")){
        long recvStartTime = System.currentTimeMillis();
        BufferTransmission.getFile(port);
        long recvEndTime = System.currentTimeMillis();
        System.out.println("Received the file in " + (recvEndTime - recvStartTime) + " ms");
      }
     }
    else if(args.length > 3 && args[0].equals("--send")){
      System.out.println("Starting " + (args.length - 2) + " Threads!");
      System.out.println(args.length - 2 + " Nodes given");
      for(int i = 2; i < args.length; i++){
      Threads t = new Threads(port, dataInput, dataOutput, args[i], args[1]);
      t.start();
      }
    }
    else{
      System.out.println("Arguments Missing!");
      Helper.help();
    }
  }
}
