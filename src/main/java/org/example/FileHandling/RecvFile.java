package org.example.FileHandling;

import java.io.DataInputStream;
import java.io.FileOutputStream;
public class RecvFile {
  public static void recvFile(String fileName, DataInputStream dataRead) throws Exception{
  int counter = 0;
  FileOutputStream recvFile = new FileOutputStream(fileName);

  // calculating filesize
  long size = dataRead.readLong();
  // breaking the file into chunks
  byte[] buffer = new byte[4 * 1024];

  //while loop to recv the file
  while(size > 0 && (counter = dataRead.read(buffer, 0, (int)Math.min(buffer.length, size))) != 1){
    recvFile.write(buffer, 0, counter);
    size -= counter;
  }
  System.out.println("File Acquired!");
  recvFile.close();
}
}

