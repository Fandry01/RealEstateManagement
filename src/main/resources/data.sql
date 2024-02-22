INSERT INTO properties (id, type, bought_price, current_price,build_year, square_feet,rented,address) VALUES (1,'Appartment',1500000.00,1750.00,1990,'150m2',true,'Dennerode 15 2024MB Amsterdam'),
                                                                                                         (2,'Vrijstaand',100000.00,12500.00,2020,'250m2',true,'Uitweg 1 1515 SL Vinkeveen'),
                                                                                                         (3,'Studio',900.00,1100.00,2009,'45',true,'diemerweg 12,4554MG Amsterdam');
INSERT INTO maintenances (id,type_of_Maintenance, maintenance_date ) VALUES (1,'floorRepair', '2024-03-15'),
                                                                         (2,'refill heating system', '2024-03-20');

INSERT INTO complaints (id, date_of_complaint, complaint_message) VALUES (1,'2024-03-20','floor heating not working'),
                                                                         (2,'2024-05-20','floor heating not working'),
                                                                         (3,'2024-04-20','floor heating not working');

INSERT INTO investors(username, password,first_name,last_name,date_of_birth,address) VALUES ('derrickRose', '$2a$12$Q8pV8rTv0yiCvIwR.NWgPuXBzFDpAJRrLKElOnI6twuwgUSQJ6c3q','derrick','rose','2000-12-12','rozengracht 12');
INSERT INTO tenants(username,password,first_name,last_name,date_of_birth,rental_periode,rent_price) VALUES ('markMillions','$2a$12$LKNWAn404x9V2hwDTEPd.e18kx7yYM87iY5rnR8nkkH7NmycaIbca','mark','millions','1999-09-21','2025-10-1','1500');
INSERT INTO roles (username, authority_roles) VALUES ('derrickRose','ROLE_ADMIN');
INSERT INTO roles (username, authority_roles) VALUES ('markMillions','ROLE_USER');


UPDATE properties SET complaint_id = 1 WHERE id = 1;
UPDATE tenants SET complaint_id = 1 WHERE username = 'markMillions';
UPDATE properties SET investor_username = 'derrickRose' WHERE id = 1;
UPDATE tenants SET property_id = 1 WHERE username = 'markMillions';