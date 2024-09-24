package com.example.controller;

import com.example.pojo.FoodTruck;
import com.example.service.FoodTruckService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sky
 * @date 2024/9/24 - 0:58
 */
@RestController
public class FoodTruckController {
    @Autowired
    private FoodTruckService foodTruckService;
    /**
     * retrieve Nearest FoodTruck by user's Latitude and Longitude coordinates.
     *
     * @param userLatitude
     * @param userLongitude
     * @return the Nearest FoodTruck
     */
    @GetMapping("/nearestFoodTruck")
    public ResponseEntity<FoodTruck> retrieveNearestFoodTruck(@RequestParam String userLatitude, @RequestParam String userLongitude) {
        FoodTruck foodTruck = foodTruckService.getNearestFoodTruck(NumberUtils.toDouble(userLatitude), NumberUtils.toDouble(userLongitude));
        if (ObjectUtils.isNotEmpty(foodTruck)) {
            return new ResponseEntity<>(foodTruck, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
