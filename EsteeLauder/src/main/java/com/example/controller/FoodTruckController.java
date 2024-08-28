package com.example.controller;

import com.example.service.FoodTruckService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sky
 * @date 2024/8/28 - 0:58
 */
@RestController
public class FoodTruckController {
    @GetMapping("/nearestFoodTruck")
    public String retrieveNearestFoodTruck(@RequestParam String userLatitude, @RequestParam String userLongitude) {
        FoodTruckService foodTruckService = new FoodTruckService();
        return foodTruckService.getNearestFoodTruck(NumberUtils.toDouble(userLatitude), NumberUtils.toDouble(userLongitude));
    }
}
