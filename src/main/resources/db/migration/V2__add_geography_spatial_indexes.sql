-- driver location
CREATE INDEX idx_driver_available_location
    ON driver
        USING GIST (current_location)
    WHERE available = true;

-- ride locations
CREATE INDEX idx_ride_pickup_location
    ON ride
        USING GIST (pickup_location);

CREATE INDEX idx_ride_dropoff_location
    ON ride
        USING GIST (drop_off_location);