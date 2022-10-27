package ru.itmo.SecondService.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itmo.SecondService.entity.Route;

import java.io.IOException;
import java.util.List;

@Service
public class RouteService {

        public Route getRouteById(long id) {
            final String uri = "http://localhost:8080/service_1-1.0-SNAPSHOT/api/routes/" + id;
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(uri, Route.class);
        }

        public Integer deleteRouteById(long id) {
            final String uri = "http://localhost:8080/service_1-1.0-SNAPSHOT/api/routes/" + id;
            RestTemplate restTemplate = new RestTemplate();
            if(getRouteById(id) == null) {
                return -1;
            }
            restTemplate.delete(uri);
            return 0;
        }


        public Long getDistancesSum() {
            final String uri = "http://localhost:8080/service_1-1.0-SNAPSHOT/api/routes/distances/sum";
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.postForObject(uri, null, Long.class);
        }

        public Long getDistanceNumber(int value) {
            final String uri = "http://localhost:8080/service_1-1.0-SNAPSHOT/api/routes/distances/less/" + value;
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.postForObject(uri, null, Long.class);
        }


        public List getUniqueDistances(int pageNumber, int pageSize) {
            final String uri = "http://localhost:8080/service_1-1.0-SNAPSHOT/api/routes/distances/unique?pageSize=" + pageSize + "&pageNumber=" + pageNumber;
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.postForObject(uri, null, List.class);
        }


}
