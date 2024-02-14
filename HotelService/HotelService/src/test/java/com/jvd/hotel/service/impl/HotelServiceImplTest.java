package com.jvd.hotel.service.impl;

import com.jvd.hotel.entities.Hotel;
import com.jvd.hotel.repositories.HotelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HotelServiceImplTest
{
    @InjectMocks
    private HotelServiceImpl service;

    @Mock
    private HotelRepository repository;


    @Test
    void create()
    {
        Hotel hotel = new Hotel();
        hotel.setId("111");
        hotel.setName("ARAKARA");
        hotel.setLocation("BENGALURU");
        hotel.setAbout("NICE INFRASTRUCTURE");

        Mockito.when(repository.save(hotel)).thenReturn(hotel);
        Hotel response = service.create(hotel);
        assertEquals(response.getId(),hotel.getId());
        assertEquals(response.getName(),hotel.getName());
        assertEquals(response.getLocation(),hotel.getLocation());
        assertEquals(response.getAbout(),hotel.getAbout());
    }

    @Test
    void getAll()
    {
        Hotel h1 = new Hotel();
        h1.setId("1");
        h1.setName("Navaratna");
        h1.setLocation("Davanagere");
        h1.setAbout("Nice Outlook");

        Hotel h2 = new Hotel();
        h2.setId("2");
        h2.setName("Prathibha");
        h2.setLocation("Hassan");
        h2.setAbout("Good");

        List<Hotel> hotelList = new ArrayList<>();
        hotelList.add(h1);
        hotelList.add(h2);

        Mockito.when(repository.findAll()).thenReturn(hotelList);
        List<Hotel> response = service.getAll();

        assertEquals(response.get(0).getLocation(),hotelList.get(0).getLocation());
        assertEquals(response.get(0).getName(),hotelList.get(0).getName());

    }

    @Test
    void get()
    {
        Hotel hotel = new Hotel();
        hotel.setId("123");
        hotel.setName("Ramaratha");
        hotel.setLocation("Bengaluru");
        hotel.setAbout("Good");

        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Optional.of(hotel));
        Hotel response =service.get("123");

        assertEquals(response.getName(),hotel.getName());
        assertEquals(response.getId(),hotel.getId());
    }
}