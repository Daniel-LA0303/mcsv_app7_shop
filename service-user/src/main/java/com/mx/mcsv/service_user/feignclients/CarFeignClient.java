package com.mx.mcsv.service_user.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mx.mcsv.service_user.model.Car;

@FeignClient(name = "service-car")
//@RequestMapping("/car")
public interface CarFeignClient {

    @PostMapping("/car")
    Car save(@RequestBody Car car);

    @GetMapping("/car/byuser/{userId}")
    List<Car> getCars(@PathVariable("userId") int userId);
}
