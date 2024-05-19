INSERT INTO properties (id, type, bought_price, current_price,build_year, square_feet,rented,address) VALUES (20,'APPARTEMENT',1500000.00,1750.00,1990,'150m2',true,'Dennerode 15 2024MB Amsterdam'),
                                                                                                         (21,'VRIJSTAANDEHUIS',100000.00,12500.00,2020,'250m2',true,'Uitweg 1 1515 SL Vinkeveen'),
                                                                                                         (22,'PENTHOUSE',900.00,1100.00,2009,'45',true,'diemerweg 12,4554MG Amsterdam');
INSERT INTO maintenances (id,type_of_Maintenance, maintenance_date,priority ) VALUES (20,'ROUTINE_MAINTENANCE', '2024-03-15','HIGH'),
                                                                         (21,'SAFETY_MAINTENANCE', '2024-03-20','MEDIUM');

INSERT INTO complaints (id, date_of_complaint, complaint_message) VALUES (1,'2024-03-20','floor heating not working'),
                                                                         (2,'2024-05-20','floor heating not working'),
                                                                         (3,'2024-04-20','floor heating not working');

INSERT INTO investors(username, password,first_name,last_name,date_of_birth,address) VALUES ('jan', '$2a$12$CWUoMhcf9W/aPg6GFgxU2e1XfDROYEVc0hx/26E8FdpZddst.Eupq','derrick','rose','2000-12-12','rozengracht 12');
INSERT INTO tenants(username,password,first_name,last_name,date_of_birth) VALUES ('markis','$2a$12$CWUoMhcf9W/aPg6GFgxU2e1XfDROYEVc0hx/26E8FdpZddst.Eupq','mark','anthony','1999-09-21');
INSERT INTO investors (username,password,first_name,last_name,date_of_birth) VALUES('admin','$2a$12$NzllTH0aGjIo5DcjajglL.6CZbeGWYXfMcnSmxMgIMUPNS01dfWhi','piet','jansen','1990-10-10');
INSERT INTO roles (username, authority_roles) VALUES ('jan','ROLE_INVESTOR');
INSERT INTO roles (username, authority_roles) VALUES ('mark','ROLE_USER');
INSERT INTO roles (username, authority_roles) VALUES ('admin','ROLE_ADMIN');
INSERT INTO lease_agreement (id, rental_price, rental_period, start_date, end_date, payment_terms) VALUES (5,1500.00,'1 Year','2024-01-01','2024-01-01','MONTHLY');


UPDATE complaints SET tenant_username = 'markis' WHERE id = 2;
UPDATE maintenances SET property_id = 20 WHERE id = 20;
UPDATE maintenances SET property_id = 21 WHERE id = 21;
UPDATE properties SET investor_username = 'jan' WHERE id = 20;
UPDATE tenants SET property_id = 20 WHERE username = 'markis';
UPDATE complaints SET property_id = 20 WHERE id = 2;
UPDATE lease_agreement SET property_id = 20 WHERE id = 5;
UPDATE lease_agreement SET investor_username = 'jan' WHERE id = 5;
UPDATE lease_agreement SET tenant_username = 'markis' WHERE id = 5;