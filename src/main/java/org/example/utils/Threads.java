package org.example.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import org.example.FileHandling.*;

public class Threads implements Runnable{
  // create Send file and receive file threads and an executor
  DataInputStream dataInput;
  DataOutputStream dataOutput;
  String IP, path;
  int port;

  public Threads(int port, DataInputStream dataInput, DataOutputStream dataOutput, String IP, String path){
    this.port = port;
    this.dataInput = dataInput;
    this.dataOutput = dataOutput;
    this.IP = IP;
    this.path = path;
  }

  @Override
  public void run() {
    Transmission.ConnectionSend(dataInput, dataOutput, IP, port, path);
  }
  public void start(){
    new Thread(this).start();
  }
}