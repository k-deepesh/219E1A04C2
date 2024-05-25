package com.example.affordmedTest.Afford.medical.Tech;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping
public class AverageCalculatorController {

    private static final int WINDOW_SIZE = 10;
    private List<Integer> numbers = new ArrayList<>();

    @GetMapping("/numbers/{numberid}")
    public ResponseEntity<Map<String, Object>> getNumbers(@PathVariable String numberid) {
        List<Integer> fetchedNumbers;
        switch (numberid) {
            case "p":
                fetchedNumbers = fetchPrimeNumbersFromTestServer();
                break;
            case "e":
                fetchedNumbers = fetchEvenNumbersFromTestServer();
                break;
            case "f":
                fetchedNumbers = fetchFibonacciNumbersFromTestServer();
                break;
            case "r":
                fetchedNumbers = fetchRandomNumbersFromTestServer();
                break;
            default:
                return ResponseEntity.badRequest().build(); // Handle invalid qualifier
        }
        storeNumbers(fetchedNumbers);
        double average = calculateAverage();

        Map<String, Object> response = new HashMap<>();
        response.put("numbers", fetchedNumbers);
        response.put("windowPrevState", numbers.subList(0, Math.min(numbers.size(), WINDOW_SIZE)));
        response.put("windowCurrState", numbers.subList(0, Math.min(numbers.size(), WINDOW_SIZE)));
        response.put("avg", average);

        return ResponseEntity.ok(response);
    }

    private List<Integer> fetchPrimeNumbersFromTestServer() {
        // Logic to fetch prime numbers from the test server
        return Arrays.asList(2, 3, 5, 7, 11); // Dummy data for demonstration
    }

    private List<Integer> fetchEvenNumbersFromTestServer() {
        // Logic to fetch even numbers from the test server
        return Arrays.asList(2, 4, 6, 8, 10); // Dummy data for demonstration
    }

    private List<Integer> fetchFibonacciNumbersFromTestServer() {
        // Logic to fetch Fibonacci numbers from the test server
        return Arrays.asList(0, 1, 1, 2, 3, 5, 8, 13, 21, 34); // Dummy data for demonstration
    }

    private List<Integer> fetchRandomNumbersFromTestServer() {
        // Logic to fetch random numbers from the test server
        return Arrays.asList(10, 20, 30, 40, 50); // Dummy data for demonstration
    }

    private void storeNumbers(List<Integer> fetchedNumbers) {
        for (int num : fetchedNumbers) {
            if (!numbers.contains(num)) {
                numbers.add(num);
            }
        }
        while (numbers.size() > WINDOW_SIZE) {
            numbers.remove(0);
        }
    }

    private double calculateAverage() {
        if (numbers.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return (double) sum / numbers.size();
    }
}
