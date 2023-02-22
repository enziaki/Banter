package org.example.Help;

public class Helper {
  public static void help(){
    System.err.println("Requires 3 Arguments!");
    String help = "java Banter <option> <file> <address>\n"
            + "[--send/--recv] - For sending and Receiving\n"
            + "[filename] - File to be sent/received"
            + "[ip address] - Ip Address for Destination or Source\n"
            + "Ex - java Banter --send test.txt \"10.11.18.20\"\n";
    System.out.println(help);
  }
}