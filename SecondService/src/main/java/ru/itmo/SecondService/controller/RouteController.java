package ru.itmo.SecondService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.itmo.SecondService.entity.Route;
import ru.itmo.SecondService.service.RouteService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/routes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class RouteController {

    private final RouteService routeService;



    @GetMapping("/{id}")
    public ResponseEntity findRouteById(@PathVariable("id") long id) throws IOException {
         Route route = routeService.getRouteById(id);
         if(route == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Route not found");
        }
         return ResponseEntity.status(HttpStatus.OK).body(route);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRouteById(@PathVariable("id") long id) throws IOException {
        Integer status = routeService.deleteRouteById(id);
        if(status == -1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Route not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Route Deleted");
    }

    @PostMapping("/distances/sum")
    public ResponseEntity getRouteSumDistance() {
        Long sum = routeService.getDistancesSum();
        if(sum == null) {
            return ResponseEntity.status(HttpStatus.OK).body("There are no distances");
        }
        return ResponseEntity.status(HttpStatus.OK).body(sum);
    }


    @PostMapping("/distances/less/{value}")
    public ResponseEntity getLessDistances(@PathVariable("value") int value) {
        Long distanceNumber = routeService.getDistanceNumber(value);
        return ResponseEntity.status(HttpStatus.OK).body(distanceNumber);
    }


    @PostMapping("/distances/unique")
    public ResponseEntity getUniqueDistances(Integer pageNumber, Integer pageSize) {
        if(pageNumber == null || pageSize == null || pageNumber <= 0 || pageSize <= 0) {
            return ResponseEntity.status(HttpStatus.valueOf(400)).body("Invalid parameters");
        }
        List uniqueDistances = routeService.getUniqueDistances(pageNumber, pageSize);
        if(uniqueDistances.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Distances not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(uniqueDistances);
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class})
    public ResponseEntity handleBaseExceptions() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input");
    }


}

