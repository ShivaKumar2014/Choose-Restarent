package com.example.choosing.web;

import com.example.choosing.exceptions.RestaurantNotFoundException;
import com.example.choosing.model.Restaurant;
import com.example.choosing.service.ChoiceService;
import com.example.choosing.userdetails.UserPrincipal;
import com.example.choosing.util.ChoiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/choice", produces = MediaTypes.HAL_JSON_VALUE)
public class ChoiceController {
    private final ChoiceService choiceService;

    @Autowired
    public ChoiceController(ChoiceService choiceService) {
        this.choiceService = choiceService;
    }

    @GetMapping
    public ResponseEntity<Restaurant> current(@AuthenticationPrincipal UserPrincipal userPrincipal) throws RestaurantNotFoundException {
        return new ResponseEntity<>(choiceService.getCurrent(userPrincipal.getUser()), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Restaurant> choice(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("id") Restaurant restaurant) {
        ChoiceStatus choiceStatus = choiceService.choiceStatus(userPrincipal.getUser(), restaurant);
        return new ResponseEntity<>(choiceStatus.getChoice().getRestaurant(), choiceStatus.isCreated() ? HttpStatus.CREATED : HttpStatus.OK);
    }
}
