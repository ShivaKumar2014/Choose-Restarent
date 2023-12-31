package com.example.choosing.web;

import com.example.choosing.model.Restaurant;
import com.example.choosing.service.RestaurantService;
import com.example.choosing.to.RestaurantTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/restaurants", produces = MediaTypes.HAL_JSON_VALUE)
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/{id}/lunch")
    public ResponseEntity<RestaurantTo> getLunchToday(@PathVariable("id") Restaurant restaurant) {
        return new ResponseEntity<>(
                restaurantService.getWithLunchToday(restaurant), HttpStatus.OK);
    }
}
