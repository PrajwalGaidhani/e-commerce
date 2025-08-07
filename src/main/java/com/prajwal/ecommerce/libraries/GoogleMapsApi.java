package com.prajwal.ecommerce.libraries;



import com.prajwal.ecommerce.libraries.models.GLocation;
import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class GoogleMapsApi {

    public int estimate(GLocation src, GLocation dest) {
        // Call google maps service to get the number of seconds required to travel from src to dest
        Random random = new Random();
        return random.nextInt(100000000) + 1;
    }
}