package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.example.FileHandling.*;
import org.example.Help.Helper;

public class Main {
  private static DataOutputStream dataOutput = null;
  private static DataInputStream dataInput = null;

  public static void main(String[] args) throws IOException {
    // defining a port
    int port = 6969;
    if (args.length != 3) {
      Helper.help();
    }
    // Client side sendFile block
    if (args[0].equals("--send")) {
      try (Socket clsock = new Socket(args[2], port)) {
        dataInput = new DataInputStream(clsock.getInputStream());
        dataOutput = new DataOutputStream(clsock.getOutputStream());
        System.out.println("Initiated!");

        // calling the SendFile method
        SendFile.sendFile(args[1], dataOutput);
        dataInput.close();
        dataOutput.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
      // Server side recvFile method
    } else if (args[0].equals("--recv")) {
      try (ServerSocket srvsock = new ServerSocket(port)) {
        System.out.println("Using port: " + port + " on addr: " + args[2]);

        // accepting client's request
        Socket acceptClSock = srvsock.accept();
        System.out.println("Connected!");
        dataInput = new DataInputStream(acceptClSock.getInputStream());
        dataOutput = new DataOutputStream(acceptClSock.getOutputStream());

        // calling the recvFile method
        RecvFile.recvFile(args[1], dataInput);
        dataInput.close();
        dataOutput.close();
        acceptClSock.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      System.err.println("Arguments missing!");
      Helper.help();
    }
  }
}