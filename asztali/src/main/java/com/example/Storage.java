package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

  public static ArrayList<Restoration> readRestorations() {
    try {
      return tryReadRestorations();
    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage());
      return new ArrayList<Restoration>();
    }

  }
  
  public static ArrayList<Restoration> tryReadRestorations() throws FileNotFoundException {
    ArrayList<Restoration> resList = new ArrayList<Restoration>();

    File file = new File("restauralasok.csv");
    try(Scanner sc = new Scanner(file)) {
      sc.nextLine();
      while (sc.hasNext()) {
        String line = sc.nextLine();
        String[] parts = line.split(";");

        Restoration res = new Restoration();

        res.setId(Integer.parseInt(parts[0]));
        res.setPaintingid(Integer.parseInt(parts[1]));
        res.setDatum(LocalDate.parse(parts[2]));
        res.setMuvelet(parts[3]);
        res.setMegjegyzes(parts[4]);
        resList.add(res);
      }
    }
    return resList;
  }

  public static void writeRestorations(ArrayList<Restoration> resList) {
    try {
      tryWriteRestorations(resList);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
  
  public static void tryWriteRestorations(ArrayList<Restoration> resList) throws IOException {
    FileWriter fw = new FileWriter("restauralasok.csv");
    fw.write("id;paintingid;datum;muvelet;megjegyzes\n");

    for (Restoration res : resList) {
      fw.write(
        res.getId() + ";" + 
        res.getPaintingid() + ";" + 
        res.getDatum() + ";" + 
        res.getMuvelet() + ";" + 
        res.getMegjegyzes() + "\n");
    }
    fw.close();
  }
  
}
