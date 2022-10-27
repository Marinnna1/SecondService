package ru.itmo.SecondService.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.itmo.SecondService.entity.Route;
import ru.itmo.SecondService.service.NavigatorService;
import ru.itmo.SecondService.service.RouteService;

@RestController
@RequestMapping(value = "/navigator")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class NavigatorController {

    private final NavigatorService navigatorService;


    @PostMapping("/routes/{id-from}/{id-to}/{shortest}")
    public ResponseEntity findLongestOrShortestRoute(@PathVariable("id-from") int idFrom,
                                                     @PathVariable("id-to") int idTo,
                                                     @PathVariable("shortest") int shortest) {
        if(shortest != 1 && shortest != 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid parameter shortest");
        }
        Route route = navigatorService.findLongestOrShortestRoute(idFrom, idTo, shortest);
        if(route == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Route not found");
        }
        System.out.println(route);
        return ResponseEntity.status(HttpStatus.OK).body(route);
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class})
    public ResponseEntity handleBaseExceptions() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input");
    }




}
