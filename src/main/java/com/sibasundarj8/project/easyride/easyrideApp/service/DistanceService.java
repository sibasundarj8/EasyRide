package com.sibasundarj8.project.easyride.easyrideApp.service;

import org.locationtech.jts.geom.Point;

public interface DistanceService {
    double calculateDistance(Point source, Point destination);
}