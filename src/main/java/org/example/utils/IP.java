package org.example.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class IP{
  public void displayInterface(NetworkInterface netint) throws SocketException{
    Enumeration<InetAddress> inter = netint.getInetAddresses();
    for (InetAddress iaddr : Collections.list(inter)){
      String ipAddr = iaddr.getHostAddress();
      System.out.println("Ip addr: " + ipAddr );
    }
  }

  public void printInfo() throws SocketException{
    Enumeration<NetworkInterface> netint = NetworkInterface.getNetworkInterfaces();
    System.out.println("Available IP addresses: ");
    for( NetworkInterface i : Collections.list(netint)){
      displayInterface(i);
    }
  }
}