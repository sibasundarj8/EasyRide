-- Enable PostGIS
CREATE EXTENSION IF NOT EXISTS postgis;

--------------------------------------------------
-- USER
--------------------------------------------------

CREATE TABLE app_user (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255)
);

--------------------------------------------------
-- USER ROLE
--------------------------------------------------

CREATE TABLE user_role (
    user_id BIGINT NOT NULL,
    roles VARCHAR(50) NOT NULL,
    CONSTRAINT fk_user_role_user
        FOREIGN KEY (user_id)
            REFERENCES app_user(id)
            ON DELETE CASCADE,
    CONSTRAINT chk_role
        CHECK (roles IN ('ADMIN','DRIVER','RIDER'))
);

--------------------------------------------------
-- RIDER
--------------------------------------------------

CREATE TABLE rider (
    id BIGINT PRIMARY KEY,
    rating_sum INTEGER NOT NULL DEFAULT 0,
    rating_count INTEGER NOT NULL DEFAULT 0,
    rating DOUBLE PRECISION NOT NULL DEFAULT 0.0,

    CONSTRAINT fk_rider_user
        FOREIGN KEY (id)
            REFERENCES app_user(id)
            ON DELETE CASCADE
);

--------------------------------------------------
-- DRIVER
--------------------------------------------------

CREATE TABLE driver (
    id BIGINT PRIMARY KEY,
    vehicle_no VARCHAR(255) NOT NULL,
    vehicle_type VARCHAR(30),
    rating_sum INTEGER NOT NULL DEFAULT 0,
    rating_count INTEGER NOT NULL DEFAULT 0,
    rating DOUBLE PRECISION NOT NULL DEFAULT 0.0,
    available BOOLEAN NOT NULL DEFAULT TRUE,
    current_location geometry(Point,4326),

    CONSTRAINT fk_driver_user
        FOREIGN KEY (id)
            REFERENCES app_user(id)
            ON DELETE CASCADE,

    CONSTRAINT chk_vehicle_type CHECK (
        vehicle_type IN (
            'SEDAN',
            'SUV',
            'MOTORBIKE',
            'AUTO_RICKSHAW'
        )
    )
);

--------------------------------------------------
-- RIDE REQUEST
--------------------------------------------------

CREATE TABLE ride_request (
    id BIGSERIAL PRIMARY KEY,

    pickup_location geometry(Point,4326),
    drop_off_location geometry(Point,4326),

    requested_time TIMESTAMP,

    rider_id BIGINT,

    payment_method VARCHAR(20),

    ride_request_status VARCHAR(20),

    fare DOUBLE PRECISION,

    CONSTRAINT fk_request_rider
        FOREIGN KEY (rider_id)
            REFERENCES rider(id),

    CONSTRAINT chk_request_payment
        CHECK (payment_method IN ('CASH','WALLET')),

    CONSTRAINT chk_request_status CHECK (
        ride_request_status IN (
            'PENDING',
            'CONFIRMED',
            'CANCELLED'
        )
    )
);

--------------------------------------------------
-- RIDE
--------------------------------------------------

CREATE TABLE ride (
    id BIGSERIAL PRIMARY KEY,

    pickup_location geometry(Point,4326),
    drop_off_location geometry(Point,4326),

    created_time TIMESTAMP,

    rider_id BIGINT,
    driver_id BIGINT,

    payment_method VARCHAR(20),
    ride_status VARCHAR(20),

    otp VARCHAR(255),

    fare DOUBLE PRECISION,

    started_at TIMESTAMP,
    ended_at TIMESTAMP,

    rider_rated BOOLEAN NOT NULL DEFAULT FALSE,
    driver_rated BOOLEAN NOT NULL DEFAULT FALSE,

    CONSTRAINT fk_ride_rider
        FOREIGN KEY (rider_id)
            REFERENCES rider(id),

    CONSTRAINT fk_ride_driver
        FOREIGN KEY (driver_id)
            REFERENCES driver(id),

    CONSTRAINT chk_ride_payment CHECK (
        payment_method IN (
            'CASH',
            'WALLET'
        )
    ),

    CONSTRAINT chk_ride_status CHECK (
        ride_status IN (
            'CANCELLED',
            'CONFIRMED',
            'ONGOING',
            'ENDED'
        )
    )
);

--------------------------------------------------
-- PAYMENT
--------------------------------------------------

CREATE TABLE payment (
    id BIGSERIAL PRIMARY KEY,

    payment_method VARCHAR(20),

    ride_id BIGINT UNIQUE,

    amount DOUBLE PRECISION,

    payment_status VARCHAR(20),

    payment_time TIMESTAMP,

    CONSTRAINT fk_payment_ride
        FOREIGN KEY (ride_id)
            REFERENCES ride(id),

    CONSTRAINT chk_payment_method CHECK (
        payment_method IN (
                'CASH',
                'WALLET'
        )
    ),

    CONSTRAINT chk_payment_status CHECK (
        payment_status IN (
                'PENDING',
                'CONFIRMED',
                'REFUNDED'
        )
    )
);

--------------------------------------------------
-- WALLET
--------------------------------------------------

CREATE TABLE wallet (
    id BIGSERIAL PRIMARY KEY,

    user_id BIGINT UNIQUE NOT NULL,

    balance DOUBLE PRECISION,

    CONSTRAINT fk_wallet_user
        FOREIGN KEY (user_id)
            REFERENCES app_user(id)
);

--------------------------------------------------
-- WALLET TRANSACTION
--------------------------------------------------

CREATE TABLE wallet_transaction (
    id BIGSERIAL PRIMARY KEY,

    amount DOUBLE PRECISION,

    transaction_type VARCHAR(20),

    transaction_method VARCHAR(20),

    ride_id BIGINT,

    transaction_id VARCHAR(255),

    wallet_id BIGINT,

    timestamp TIMESTAMP,

    CONSTRAINT fk_wallet_transaction_wallet
        FOREIGN KEY (wallet_id)
            REFERENCES wallet(id),

    CONSTRAINT fk_wallet_transaction_ride
        FOREIGN KEY (ride_id)
            REFERENCES ride(id),

    CONSTRAINT chk_transaction_type CHECK (
        transaction_type IN (
            'CREDIT',
            'DEBIT'
        )
    ),

    CONSTRAINT chk_transaction_method CHECK (
        transaction_method IN (
            'BANKING',
            'RIDE'
        )
    )
);