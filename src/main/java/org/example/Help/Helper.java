package org.example.Help;

public class Helper {
  public static void help(){
    String help = "java -jar Banter1.x-NOGUI.jar <option> <file> <address>\n"
            + "[--send/--recv] - For sending and Receiving\n Receiving requires a filename as well"
            + "[filename] - File to be sent/received"
            + "[ip address] - Ip Address for Destination or Source\n Multiple addresses supported for --send"
            + "Ex - java -jar Banter-1.0-NOGUI.jar --send test.txt 10.11.18.20\n";
    System.err.println(help);
  }
}
