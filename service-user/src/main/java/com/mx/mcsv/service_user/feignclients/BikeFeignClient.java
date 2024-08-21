package com.mx.mcsv.service_user.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mx.mcsv.service_user.model.Bike;

@FeignClient(name = "service-bike")
//@RequestMapping("/bike")
public interface BikeFeignClient {

    @PostMapping("/bike")
    Bike save(@RequestBody Bike bike);

    @GetMapping("/bike/byuser/{userId}")
    List<Bike> getBikes(@PathVariable("userId") int userId);
}