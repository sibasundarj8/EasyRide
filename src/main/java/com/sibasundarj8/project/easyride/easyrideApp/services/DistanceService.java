package com.sibasundarj8.project.easyride.easyrideApp.services;

import org.locationtech.jts.geom.Point;

public interface DistanceService {
    double calculateDistance(Point source, Point destination);
}