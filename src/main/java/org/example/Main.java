package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import org.example.FileHandling.*;
import org.example.Help.Helper;
import org.example.utils.*;

public class Main {
  private static DataOutputStream dataOutput = null;
  private static DataInputStream dataInput = null;

  public static void main(String[] args) throws Exception {
    // defining a port
    int port = 6969;
    // Printing info about the file
    System.out.println(Chunks.displayNomenclature(Chunks.getSize(args[1])));
    // menu for the program
    if(args.length < 3){
      System.out.println("Arguments Missing!");
      Helper.help();
    }else if(args.length == 3) {
      if (args[0].equals("--send")) { // Send block calling
        Transmission.ConnectionSend(dataInput, dataOutput, args[2], port, args[1]);
      } else if (args[0].equals("--recv")) { // Receive block calling
        Transmission.ConnectionRecv(dataInput, dataOutput, args[2], port, args[1]);
      } else {
        System.out.println("***Listen mode remaining!!***");
      }
    }else if(args.length > 3){
      System.out.println("***Multithreading has not been implemented***");
    }
  }
}
