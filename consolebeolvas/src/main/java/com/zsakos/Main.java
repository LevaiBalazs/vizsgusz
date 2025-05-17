package com.zsakos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String csvFile = "C:/Users/zsako/Downloads/office_rentals_2024.csv";
        String line;
        String csvSeparator = ",";

        List<Berles> berlesek = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // fejléc átugrása

            while ((line = br.readLine()) != null) {
                String[] adatok = line.split(csvSeparator);

                int uid = Integer.parseInt(adatok[0].trim());
                int officeId = Integer.parseInt(adatok[1].trim());
                LocalDate startDate = LocalDate.parse(adatok[2].trim());
                LocalDate endDate = LocalDate.parse(adatok[3].trim());
                double dailyRate = Double.parseDouble(adatok[4].trim());
                String name = adatok[5].trim();
                String location = adatok[6].trim();

                Berles berles = new Berles(uid, officeId, startDate, endDate, dailyRate, name, location);
                berlesek.add(berles);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Berles b : berlesek) {
            System.out.println(b);
        }


        //monthlyIncome(berlesek);
        //System.out.println("Total: "+yearlyTotal(berlesek));

        //System.out.println("Sok penz: "+highest(berlesek));
        //System.out.println("OFFICE: " + noOffice(berlesek));
        System.out.println("Neve: " + neves(berlesek));
    }

    public static double monthlyIncome(List<Berles> berlesek) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Válasszon egy hónapot: ");
        String monthStr = scanner.nextLine();
        int monthNum = Integer.parseInt(monthStr);

        String monthName = Month.of(monthNum).toString();

        System.out.println(monthName);

        double monthlyTotal = 0;
        int[] daysInMonths = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};


        for (Berles berles : berlesek) {
            LocalDate startDate = berles.getStartDate();
            LocalDate endDate = berles.getEndDate();
            double dailyRate = berles.getDailyRate();

            String startDateStr = startDate.getMonth().toString();
            String endDateStr = endDate.getMonth().toString();


            if (startDateStr.equals(monthName) && endDateStr.equals(monthName)) {
                long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
                monthlyTotal += dailyRate * (daysBetween + 1);
            } else if (startDateStr.equals(monthName)) {
                monthlyTotal += dailyRate * (daysInMonths[monthNum - 1] - startDate.getDayOfMonth() + 1);
            } else if (endDateStr.equals(monthName)) {
                monthlyTotal += dailyRate * endDate.getDayOfMonth();
            }
        }

        System.out.println("Ebben a hónapban a beszedett összeg: " + monthlyTotal);

        return monthlyTotal;
    }

    public static double yearlyTotal(List<Berles> berlesek) {
        double totalIncome = 0.0;

        for (Berles berles : berlesek) {
            LocalDate startDate = berles.getStartDate();
            LocalDate endDate = berles.getEndDate();
            double dailyRate = berles.getDailyRate();
            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

            totalIncome += (daysBetween + 1) * dailyRate;
        }
        return totalIncome;
    }

    public static double highest(List<Berles> berlesek) {

        double income = 0.0;


        for (Berles berles : berlesek) {
            LocalDate startDate = berles.getStartDate();
            LocalDate endDate = berles.getEndDate();
            double dailyRate = berles.getDailyRate();
            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
            double income2 = (daysBetween + 1) * dailyRate;
            if (income2 > income) {
                income = income2;
            }
        }

        return income;

    }

    public static int noOffice(List<Berles> berlesek) {

        int office = 0;

        List asd = new ArrayList<>();

        for (Berles berles : berlesek) {
            int officeId = berles.getOfficeId();
            asd.add(officeId);
        }


        // Pick all elements one by one
        for (int i = 0; i < asd.size(); i++) {
            int j = 0;
            for (j = 0; j < i; j++)
                if (asd.get(i) == asd.get(j))
                    break;

            // If not printed earlier,
            // then print it
            if (i == j)
                office++;
        }


        return office;
    }


    public static String neves(List<Berles> berlesek) {

        List names = new ArrayList<>();
        List name = new ArrayList<>();

        for (Berles berles : berlesek) {
            String offName = berles.getLocation();
            names.add(offName);
            if (!name.contains(offName)){
                name.add(offName);
            }

        }

        int occur1 = 0;
        String nameOfOffice = "";

        for (int i = 0; i < name.size(); i++){
            int occurrences;
             occurrences = Collections.frequency(names, name.get(i));
            if( occur1 < occurrences ){
                occur1 = occurrences;
                nameOfOffice = name.get(i).toString();
            }
        }


        return String.format("The %s, was rented %d times", nameOfOffice, occur1);
    }


}
