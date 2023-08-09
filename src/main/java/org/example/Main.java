package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import org.example.FileHandling.*;
import org.example.Help.*;
import org.example.utils.*;

public class Main {
  private static DataOutputStream dataOutput = null;
  private static DataInputStream dataInput = null;
  private static final int port = 6969;

  public static void main(String[] args) throws Exception{

    // Valid IP string measure
    boolean IPstatus = true;
    

    // menu that returns an int depending on the operation
    int operation = Menu.menu(args.length, args[0]);
    System.out.println("Operation " + operation);

    // driver code
    if(IPstatus == true){
     
      if(operation == 0){
        Helper.help();
        // System.exit(1);
      }

      else if(operation == 1){
        System.out.println("Listening on " + port + " ....");
        Transmission.ConnectionRecv(dataInput, dataOutput, port, args[1]);
        System.out.println(Chunks.displayNomenclature(Chunks.getSize(args[1])));
      }

      else if(operation == 2){
        //validate IP
        for (int i = 2; i <= args.length; i++){
          if(IPRegex.checkIP(args[i]) == true){
            IPstatus = true;
          }
          else{
            Helper.IPHelp();
            IPstatus = false;
            System.exit(1);
          }
    }
        System.out.println("Starting on " + (args.length - 2) + " Thread(s).");
        for (int i = 2; i < args.length; i++){
          Threads sendThread  = new Threads(port, dataInput, dataOutput, args[i], args[1]);
          sendThread.start();
        } 
      }
      else{
        Helper.help();
      }
    }


  }
}
// else if(args[0].equals("--buffer-send")){
      //   System.out.println(Chunks.displayNomenclature(Chunks.getSize(args[1])));
      //   long startTime = System.currentTimeMillis();
      //   // BufferTransmission.sendFile(port, args[2], args[1]);
      //   long endTime = System.currentTimeMillis();
      //   System.out.println("Sent the file in " + (endTime - startTime) + " ms");
      // }
      // else if(args[0].equals("--buffer-recv")){
      //   long recvStartTime = System.currentTimeMillis();
      //   // BufferTransmission.getFile(port);
      //   long recvEndTime = System.currentTimeMillis();
      //   System.out.println("Received the file in " + (recvEndTime - recvStartTime) + " ms");
      // }
