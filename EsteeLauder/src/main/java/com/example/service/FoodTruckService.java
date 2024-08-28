package com.example.service;

import com.example.pojo.FoodTruck;
import com.google.common.collect.Lists;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;

public class FoodTruckService {
    public String getNearestFoodTruck(double userLatitude, double userLongitude) {
        List<FoodTruck> foodTrucks = Lists.newArrayList();
        String file = Objects.requireNonNull(this.getClass().getClassLoader().getResource("Mobile_Food_Facility_Permit.csv")).getPath();
        try (Reader in = new FileReader(file)) {
            CSVParser parser = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(in);
            for (CSVRecord record : parser) {
                double latitude = NumberUtils.toDouble(record.get("Latitude"));
                double longitude = NumberUtils.toDouble(record.get("Longitude"));
                String applicant = record.get("Applicant");
                foodTrucks.add(new FoodTruck(latitude, longitude, applicant));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getResult(userLatitude, userLongitude, foodTrucks);
    }

    public String getResult(double userLatitude, double userLongitude, List<FoodTruck> foodTrucks) {
        FoodTruck userLocation = new FoodTruck(userLatitude, userLongitude, "User Location");

        FoodTruck nearestTruck = null;
        double minDistance = Double.MAX_VALUE;
        for (FoodTruck truck : foodTrucks) {
            double distance = calculateDistance(userLocation, truck);
            if (distance < minDistance) {
                minDistance = distance;
                nearestTruck = truck;
            }
        }

        String result = StringUtils.EMPTY;
        if (ObjectUtils.isNotEmpty(nearestTruck)) {
            result = "The nearest foodTruck is " + nearestTruck.getName() + " and the distance is " + minDistance + " kilometers";
        }
        return result;
    }

    private double calculateDistance(FoodTruck userLocation, FoodTruck other) {
        final int R = 6371;
        double latDistance = Math.toRadians(other.getLatitude() - userLocation.getLatitude());
        double lonDistance = Math.toRadians(other.getLongitude() - userLocation.getLongitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(userLocation.getLatitude())) * Math.cos(Math.toRadians(other.getLatitude()))
                + Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}