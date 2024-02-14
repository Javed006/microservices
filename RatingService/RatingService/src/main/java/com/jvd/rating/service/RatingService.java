package com.jvd.rating.service;

import com.jvd.rating.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating create (Rating rating);
    List<Rating> getAll();
    List<Rating> getRatingByUserId(String userId);
    List<Rating> getRatingByHotelId(String hotelId);
}
