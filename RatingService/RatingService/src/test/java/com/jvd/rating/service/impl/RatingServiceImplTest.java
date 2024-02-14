package com.jvd.rating.service.impl;

import com.jvd.rating.entities.Rating;
import com.jvd.rating.repository.RatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class RatingServiceImplTest
{
    @InjectMocks
    private RatingServiceImpl service;

    @Mock
    private RatingRepository repository;


    @Test
    void create_And_Save_Rating()
    {
        Rating rating = new Rating();
        rating.setRatingId("123");
        rating.setRating(5);
        rating.setFeedback("Good");
        rating.setHotelId("736");
        rating.setUserId("111");
        Mockito.when(repository.save(rating)).thenReturn(rating);
        Rating response = service.create(rating);
        assertEquals(response,rating);
        assertEquals(response.getRatingId(),rating.getRatingId());
        assertEquals(response.getRating(),rating.getRating());
        assertEquals(response.getUserId(),rating.getUserId());
        assertEquals(response.getHotelId(),rating.getHotelId());
        assertEquals(response.getFeedback(),rating.getFeedback());
    }

    @Test
    void getAll_Rating_Data()
    {
        Rating rating1 = new Rating();
        rating1.setRatingId("123");
        rating1.setRating(5);
        rating1.setFeedback("Good");
        rating1.setHotelId("736");
        rating1.setUserId("111");

        Rating rating2 = new Rating();
        rating2.setRatingId("362");
        rating2.setRating(4);
        rating2.setFeedback("Nice");
        rating2.setHotelId("632");
        rating2.setUserId("163");

        List<Rating> ratingList = new ArrayList<>();
        ratingList.add(rating1);
        ratingList.add(rating2);

        Mockito.when(repository.findAll()).thenReturn(ratingList);
        List<Rating> response = service.getAll();

        assertEquals(response.get(0).getRating(),ratingList.get(0).getRating());
        assertEquals(response.get(0).getFeedback(),ratingList.get(0).getFeedback());
    }

    @Test
    void getRatingByUserId()
    {
        Rating rating1 = new Rating();
        rating1.setUserId("123");
        Rating rating2 = new Rating();
        rating2.setUserId("456");
        Rating rating3 = new Rating();
        rating3.setUserId("789");

        List<Rating> ratings = new ArrayList<>();
        ratings.add(rating1);
        ratings.add(rating2);
        ratings.add(rating3);

        Mockito.when(repository.findByUserId(Mockito.anyString())).thenReturn(ratings);
        List<Rating> response = service.getRatingByUserId("456");

        assertEquals(response.get(0).getUserId(),ratings.get(0).getUserId());
//        assertNotEquals(response.get(0).getUserId(),ratings.get(0).getUserId());
    }

    @Test
    void getRatingByHotelId()
    {
        Rating rating1 = new Rating();
        rating1.setHotelId("111");

        Rating rating2 = new Rating();
        rating2.setHotelId("222");

        List<Rating> ratingList = new ArrayList<>();
        ratingList.add(rating1);
        ratingList.add(rating2);

        Mockito.when(repository.findByHotelId(Mockito.anyString())).thenReturn(ratingList);
        List<Rating> response = service.getRatingByHotelId("111");

        assertEquals(response.get(0).getHotelId(),ratingList.get(0).getHotelId());
    }
}