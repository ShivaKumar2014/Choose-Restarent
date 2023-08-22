package com.example.choosing.to;

import com.example.choosing.model.Restaurant;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class RestaurantTo {
    private List<LunchTo> lunches;
    private String name;

    public RestaurantTo(Restaurant restaurant, List<LunchTo> lunches) {
        this.name = restaurant.getName();
        this.lunches = lunches;
    }
}
