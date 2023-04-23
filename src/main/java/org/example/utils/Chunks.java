package org.example.utils;

import java.io.File;
import java.io.FileNotFoundException;
public class Chunks {
  // Server side filesize calculation
  public static long getSize(String pathToFile) throws FileNotFoundException{
    File file = new File(pathToFile);
    return file.length();

  }

  // Calculating the size with the correct Nomenclature
  public static String displayNomenclature(Long filesize){
    double fs = filesize;
    String Nomenclature = null;
    double fsKB = fs/1024;
    double fsMB = fsKB/1024;
    double fsGB = fsMB/1024;
    if (fsGB > 1){Nomenclature = fsGB + " GB";}
    else if(fsMB > 1){Nomenclature = fsMB + " MB";}
    else if(fsKB > 1){Nomenclature = fsKB + " KB";}
    else{Nomenclature = fs + " bytes";}
    return Nomenclature;
  }


}


