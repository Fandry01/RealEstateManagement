INSERT INTO properties (id, type, bought_price, current_price,build_year, square_feet,rented,address) VALUES (20,'Appartment',1500000.00,1750.00,1990,'150m2',true,'Dennerode 15 2024MB Amsterdam'),
                                                                                                         (21,'Vrijstaand',100000.00,12500.00,2020,'250m2',true,'Uitweg 1 1515 SL Vinkeveen'),
                                                                                                         (32,'Studio',900.00,1100.00,2009,'45',true,'diemerweg 12,4554MG Amsterdam');
INSERT INTO maintenances (id,type_of_Maintenance, maintenance_date ) VALUES (20,'floorRepair', '2024-03-15'),
                                                                         (21,'refill heating system', '2024-03-20');

INSERT INTO complaints (id, date_of_complaint, complaint_message) VALUES (20,'2024-03-20','floor heating not working'),
                                                                         (25,'2024-05-20','floor heating not working'),
                                                                         (35,'2024-04-20','floor heating not working');

INSERT INTO investors(username, password,first_name,last_name,date_of_birth,address) VALUES ('jan', 'deman12','derrick','rose','2000-12-12','rozengracht 12');
INSERT INTO tenants(username,password,first_name,last_name,date_of_birth,rental_periode,rent_price) VALUES ('mark','deman12','mark','anthony','1999-09-21','1Year','1500.00');
INSERT INTO roles (username, authority_roles) VALUES ('jan','ROLE_ADMIN');
INSERT INTO roles (username, authority_roles) VALUES ('mark','ROLE_USER');


UPDATE properties SET complaint_id = 20 WHERE id = 20;
UPDATE tenants SET complaint_id = 20 WHERE username = 'mark';
UPDATE properties SET investor_username = 'jan' WHERE id = 20;
UPDATE tenants SET property_id = 20 WHERE username = 'mark';