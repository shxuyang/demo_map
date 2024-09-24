package com.example.service;

import com.example.component.CsvFileParser;
import com.example.pojo.FoodTruck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sky
 * @date 2024/9/24 - 0:58
 */
@Service
public class FoodTruckService {
    @Autowired
    private CsvFileParser csvFileParser;

    /**
     * retrieve all FoodTrucks with required fields
     *
     * @return
     */
    public List<FoodTruck> getAllFoodTruck() {
        List<FoodTruck> foodTrucks = csvFileParser.readCsvData();
        return foodTrucks;
    }

    public FoodTruck getNearestFoodTruck(double userLatitude, double userLongitude) {
        //Create an instance of the food truck in the current location by the current location of the user
        FoodTruck userLocation = new FoodTruck(userLatitude, userLongitude, "User Location");

        //Find the nearest food truck
        FoodTruck nearestTruck = null;
        double minDistance = Double.MAX_VALUE;
        List<FoodTruck> foodTrucks = getAllFoodTruck();
        for (FoodTruck truck : foodTrucks) {
            double distance = calculateDistance(userLocation, truck);
            if (distance < minDistance) {
                minDistance = distance;
                nearestTruck = truck;
            }
        }
        return nearestTruck;
    }
    /**
     * Haversine formula to calculate the distance latitude and longitude coordinates
     *
     * @param userLocation
     * @param other
     * @return the distance
     */
    private double calculateDistance(FoodTruck userLocation, FoodTruck other) {
        // The average radius of the Earth(kilometers)
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
