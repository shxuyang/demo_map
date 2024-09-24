package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Sky
 * @date 2024/9/24 - 0:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodTruck {
    private double latitude;
    private double longitude;
    private String name;
}
