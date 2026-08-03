----------------------------------------------
------------------- DRIVER -------------------
----------------------------------------------

CREATE INDEX idx_driver_vehicle_no
    ON driver(vehicle_no);

----------------------------------------------
-------------------- RIDE --------------------
----------------------------------------------

CREATE INDEX idx_ride_rider_id
    ON ride(rider_id);

CREATE INDEX idx_ride_driver_id
    ON ride(driver_id);

----------------------------------------------
---------------- RIDE-REQUEST ----------------
----------------------------------------------

CREATE INDEX idx_ride_request_rider_id
    ON ride_request(rider_id);

----------------------------------------------
------------- WALLET-TRANSACTION -------------
----------------------------------------------

CREATE INDEX idx_wallet_transaction_wallet_id
    ON wallet_transaction(wallet_id);