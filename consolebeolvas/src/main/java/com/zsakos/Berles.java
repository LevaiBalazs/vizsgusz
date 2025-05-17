package com.zsakos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Berles {

    private int uId;
    private int officeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double dailyRate;
    private String name;
    private String location;

    public Berles(int uId, int officeId, LocalDate startDate, LocalDate endDate, double dailyRate, String name, String location) {
        this.uId = uId;
        this.officeId = officeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyRate = dailyRate;
        this.name = name;
        this.location = location;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getTotalPrice() {
        long days = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        return days * dailyRate;
    }

    @Override
    public String toString() {
        return String.format("UID: %d, OfficeID: %d, Name:%s  Start: %s, End: %s, Office: %s, Total Price: %.2f EUR",  uId, officeId, location,  startDate, endDate, name, getTotalPrice());
    }

}