package com.mx.mcsv.service_bike.service;

import com.mx.mcsv.service_bike.entity.Bike;
import com.mx.mcsv.service_bike.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    @Autowired
    BikeRepository bikeRepository;

    public List<Bike> getAll() {
        return bikeRepository.findAll();
    }

    public Bike getBikeById(int id) {
        return bikeRepository.findById(id).orElse(null);
    }

    public Bike save(Bike bike) {
        Bike bikeNew = bikeRepository.save(bike);
        return bikeNew;
    }

    public List<Bike> byUserId(int userId) {
        return bikeRepository.findByUserId(userId);
    }
}
