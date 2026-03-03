package com.sibasundarj8.project.easyride.easyrideApp.util;

import com.sibasundarj8.project.easyride.easyrideApp.dto.PointDto;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

public class GeometryUtil {

    public static Point createPoint(PointDto pointDto) {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

        Coordinate coordinate = new Coordinate(
                pointDto.getCoordinates()[0],   // longitude
                pointDto.getCoordinates()[1]    // latitude
        );

        return geometryFactory.createPoint(coordinate);
    }
}