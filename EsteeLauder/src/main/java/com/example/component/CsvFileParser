package com.example.component;

import com.example.exception.FetchException;
import com.example.pojo.FoodTruck;
import com.google.common.collect.Lists;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;

/**
 * @author Sky
 * @date 2024/9/24 - 0:58
 */
@Component
public class CsvFileParser {
    /**
     * retrieve required fields from CSV and convert to FoodTruck.
     *
     * @return
     */
    public List<FoodTruck> readCsvData() {
        String file = Objects.requireNonNull(this.getClass().getClassLoader().getResource("data/Mobile_Food_Facility_Permit.csv")).getPath();
        List<FoodTruck> foodTrucks = Lists.newArrayList();
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
            System.err.println("Fetch Food Truck Data Failed. " + e.getMessage());
            throw new FetchException("Fetch Food Truck Data Failed. " + e.getMessage());
        }
        return foodTrucks;
    }
}
