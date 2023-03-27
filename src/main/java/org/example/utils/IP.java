package org.example.util;

import java.io.IOException;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class IP {
  /*
  public static void getIP() throws IOException {
    Enumeration interfaces = NetworkInterface.getNetworkInterfaces();

  }

   */

  public static void main(String[] args) throws IOException{
    NetworkInterface nf = NetworkInterface.getByIndex(1);
    System.out.println(nf);
  }
}
