package org.example.FileHandling;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class SendFile{
  public static void sendFile(String filename, DataOutputStream writeData)throws Exception{
    int counter = 0;
    File fileSend = new File(filename);
    FileInputStream fileInput = new FileInputStream(fileSend);

    // sending the file
    writeData.writeLong(fileSend.length());
    // breaking the file into chunks
    byte[] buffer = new byte[4 * 1024];
    //while loop to send the file
    while ((counter = fileInput.read(buffer)) != -1){
      writeData.write(buffer, 0, counter);
      writeData.flush();
    }
    fileInput.close();
  }
}