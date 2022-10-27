package ru.itmo.SecondService.service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itmo.SecondService.entity.Route;

@Service
public class NavigatorService {


    public Route findLongestOrShortestRoute(int idFrom, int idTo, int shortest) {
        final String uri = "http://localhost:8080/service_1-1.0-SNAPSHOT/api/navigator/routes/" + idFrom + "/" + idTo + "/" + shortest;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(uri, null, Route.class);
    }
}
