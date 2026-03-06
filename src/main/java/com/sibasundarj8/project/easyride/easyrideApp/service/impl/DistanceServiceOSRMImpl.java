package com.sibasundarj8.project.easyride.easyrideApp.service.impl;

import com.sibasundarj8.project.easyride.easyrideApp.service.IDistanceService;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DistanceServiceOSRMImpl implements IDistanceService {

    private static final String OSRM_API_BASE_URL = "https://router.project-osrm.org/route/v1/driving/";
    private final RestClient restClient = RestClient.builder()
            .baseUrl(OSRM_API_BASE_URL)
            .build();

    @Override
    public double calculateDistance(Point source, Point destination) {
        String uri = buildUri(source, destination);

        OSRM_ResponseDto osrmResponse = restClient
                .get()
                .uri(uri)
                .retrieve()
                .body(OSRM_ResponseDto.class);

        if (osrmResponse == null || !"Ok".equals(osrmResponse.getCode())) {
            throw new RuntimeException("OSRM Failed !!");
        }

        if (osrmResponse.getRoutes() == null || osrmResponse.getRoutes().isEmpty()) {
            throw new RuntimeException("No route found !!");
        }

        return osrmResponse.getRoutes().getFirst().getDistance() / 1000D;
    }

    private String buildUri(Point source, Point destination) {
        return source.getX() + "," + source.getY() + ";" +
                destination.getX() + "," + destination.getY() +
                "?overview=false";
    }
}

@Getter
@Setter
class OSRM_ResponseDto {
    private String code;
    private List<OSRM_Route> routes;
}

@Getter
@Setter
class OSRM_Route {
    private double distance;
}