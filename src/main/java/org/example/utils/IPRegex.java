package org.example.utils;

import java.util.regex.Pattern;

public class IPRegex { 
  
  private static final Pattern IPMatchString = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
  
  public static boolean checkIP(String IP){
    return IPMatchString.matcher(IP).matches();
  }

}
