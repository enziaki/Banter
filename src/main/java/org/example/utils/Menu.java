package org.example.utils;

public class Menu {

  static int ops = 0;

 public static int menu(int argsLength, String mode){

  if (mode.equals("--gui") && argsLength == 1){
    ops = 0;
  }
  else if(mode.equals("--recv") && argsLength == 2){
    ops = 1;
  }
  else if(mode.equals("--send") && argsLength >= 3){
    ops = 2;
  }
  else{
    ops = -1;
  }
  return ops;
 } 
  
}
