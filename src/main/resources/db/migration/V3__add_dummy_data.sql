--------------------------------------------------------
-- USERS (10 Riders + 10 Drivers)
--------------------------------------------------------

INSERT INTO app_user(id, name, email, password) VALUES
                                                    (1,'Rider 1','rider1@gmail.com','1234'),
                                                    (2,'Rider 2','rider2@gmail.com','1234'),
                                                    (3,'Rider 3','rider3@gmail.com','1234'),
                                                    (4,'Rider 4','rider4@gmail.com','1234'),
                                                    (5,'Rider 5','rider5@gmail.com','1234'),
                                                    (6,'Rider 6','rider6@gmail.com','1234'),
                                                    (7,'Rider 7','rider7@gmail.com','1234'),
                                                    (8,'Rider 8','rider8@gmail.com','1234'),
                                                    (9,'Rider 9','rider9@gmail.com','1234'),
                                                    (10,'Rider 10','rider10@gmail.com','1234'),

                                                    (11,'Driver 1','driver1@gmail.com','1234'),
                                                    (12,'Driver 2','driver2@gmail.com','1234'),
                                                    (13,'Driver 3','driver3@gmail.com','1234'),
                                                    (14,'Driver 4','driver4@gmail.com','1234'),
                                                    (15,'Driver 5','driver5@gmail.com','1234'),
                                                    (16,'Driver 6','driver6@gmail.com','1234'),
                                                    (17,'Driver 7','driver7@gmail.com','1234'),
                                                    (18,'Driver 8','driver8@gmail.com','1234'),
                                                    (19,'Driver 9','driver9@gmail.com','1234'),
                                                    (20,'Driver 10','driver10@gmail.com','1234');

--------------------------------------------------------
-- USER ROLES
--------------------------------------------------------

INSERT INTO user_role(user_id, roles) VALUES
                                          (1,'RIDER'),
                                          (2,'RIDER'),
                                          (3,'RIDER'),
                                          (4,'RIDER'),
                                          (5,'RIDER'),
                                          (6,'RIDER'),
                                          (7,'RIDER'),
                                          (8,'RIDER'),
                                          (9,'RIDER'),
                                          (10,'RIDER'),

                                          (11,'DRIVER'),
                                          (12,'DRIVER'),
                                          (13,'DRIVER'),
                                          (14,'DRIVER'),
                                          (15,'DRIVER'),
                                          (16,'DRIVER'),
                                          (17,'DRIVER'),
                                          (18,'DRIVER'),
                                          (19,'DRIVER'),
                                          (20,'DRIVER');

--------------------------------------------------------
-- RIDERS
--------------------------------------------------------

INSERT INTO rider(id, rating) VALUES
                                  (1,4.8),
                                  (2,4.6),
                                  (3,4.9),
                                  (4,4.7),
                                  (5,4.5),
                                  (6,4.8),
                                  (7,4.4),
                                  (8,4.9),
                                  (9,4.6),
                                  (10,4.7);

--------------------------------------------------------
-- DRIVERS
--------------------------------------------------------

INSERT INTO driver
(id, vehicle_no, vehicle_type, rating, available, current_location)
VALUES
    (11,'OD02AB1001','SEDAN',4.9,true,ST_SetSRID(ST_MakePoint(85.8245,20.2961),4326)),
    (12,'OD02AB1002','SUV',4.8,true,ST_SetSRID(ST_MakePoint(85.8290,20.3000),4326)),
    (13,'OD02AB1003','AUTO_RICKSHAW',4.6,true,ST_SetSRID(ST_MakePoint(85.8350,20.3020),4326)),
    (14,'OD02AB1004','MOTORBIKE',4.7,true,ST_SetSRID(ST_MakePoint(85.8400,20.3050),4326)),
    (15,'OD02AB1005','SEDAN',4.9,true,ST_SetSRID(ST_MakePoint(85.8450,20.3100),4326)),
    (16,'OD02AB1006','SUV',4.8,true,ST_SetSRID(ST_MakePoint(85.8500,20.3150),4326)),
    (17,'OD02AB1007','AUTO_RICKSHAW',4.5,true,ST_SetSRID(ST_MakePoint(85.8550,20.3200),4326)),
    (18,'OD02AB1008','MOTORBIKE',4.7,true,ST_SetSRID(ST_MakePoint(85.8600,20.3250),4326)),
    (19,'OD02AB1009','SEDAN',4.8,true,ST_SetSRID(ST_MakePoint(85.8650,20.3300),4326)),
    (20,'OD02AB1010','SUV',4.9,true,ST_SetSRID(ST_MakePoint(85.8700,20.3350),4326));

--------------------------------------------------------
-- WALLETS
--------------------------------------------------------

INSERT INTO wallet(id, user_id, balance) VALUES
                                             (1,1,5000),
                                             (2,2,5000),
                                             (3,3,5000),
                                             (4,4,5000),
                                             (5,5,5000),
                                             (6,6,5000),
                                             (7,7,5000),
                                             (8,8,5000),
                                             (9,9,5000),
                                             (10,10,5000),
                                             (11,11,2500),
                                             (12,12,2500),
                                             (13,13,2500),
                                             (14,14,2500),
                                             (15,15,2500),
                                             (16,16,2500),
                                             (17,17,2500),
                                             (18,18,2500),
                                             (19,19,2500),
                                             (20,20,2500);

--------------------------------------------------------
-- RESET SEQUENCES
--------------------------------------------------------

SELECT setval('app_user_id_seq', 20, true);
SELECT setval('wallet_id_seq', 20, true);