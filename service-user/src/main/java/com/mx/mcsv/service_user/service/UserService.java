package com.mx.mcsv.service_user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mx.mcsv.service_user.entity.User;
import com.mx.mcsv.service_user.feignclients.BikeFeignClient;
import com.mx.mcsv.service_user.feignclients.CarFeignClient;
import com.mx.mcsv.service_user.model.Bike;
import com.mx.mcsv.service_user.model.Car;
import com.mx.mcsv.service_user.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RestTemplate restTemplate;
    
    @Autowired
    CarFeignClient carFeignClient;

    @Autowired
    BikeFeignClient bikeFeignClient;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        User userNew = userRepository.save(user);
        return userNew;
    }
    
    public List getCars(int userId) {
    	
    	//http://service-car/car/byuser/
    	try {
            Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + jwt.getTokenValue());
            System.out.println("servicio");
            ResponseEntity<List> cars = restTemplate.exchange(
                "http://service-car/car/byuser/" + userId,
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                List.class
            );
            System.out.println("exito");
            return cars.getBody();
        } catch (Exception e) {
            // Loguear el error
            System.err.println("Error en getCars: " + e.getMessage());
            throw e; // Relanzar la excepción para que el CircuitBreaker la capture
        }
    }

    public List getBikes(int userId) {
    	//http://service-bike/bike/byuser/
    	try {
            Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + jwt.getTokenValue());
            
            ResponseEntity<List> bikes = restTemplate.exchange(
                "http://service-bike/bike/byuser/" + userId,
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                List.class
            );
            
            return bikes.getBody();
        } catch (Exception e) {
            // Loguear el error
            System.err.println("Error en getBikes: " + e.getMessage());
            throw e; // Relanzar la excepción para que el CircuitBreaker la capture
        }
    }
    
    public Car saveCar(int userId, Car car) {
        car.setUserId(userId);
        Car carNew = carFeignClient.save(car);
        return carNew;
    }

    public Bike saveBike(int userId, Bike bike) {
        bike.setUserId(userId);
        Bike bikeNew = bikeFeignClient.save(bike);
        return bikeNew;
    }

    public Map<String, Object> getUserAndVehicles(int userId) {
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            result.put("Mensaje", "no existe el usuario");
            return result;
        }
        result.put("User", user);
        List<Car> cars = carFeignClient.getCars(userId);
        if(cars.isEmpty())
            result.put("Cars", "ese user no tiene coches");
        else
            result.put("Cars", cars);
        List<Bike> bikes = bikeFeignClient.getBikes(userId);
        if(bikes.isEmpty())
            result.put("Bikes", "ese user no tiene motos");
        else
            result.put("Bikes", bikes);
        return result;
    }
}