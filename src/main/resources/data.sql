INSERT INTO properties (id, type, bought_price, current_price,build_year, square_feet,rented,address) VALUES (1,'Appartment',1500000.00,1750.00,1990,'150m2',true,'Dennerode 15 2024MB Amsterdam'),
                                                                                                         (2,'Vrijstaand',100000.00,12500.00,2020,'250m2',true,'Uitweg 1 1515 SL Vinkeveen'),
                                                                                                         (3,'Studio',900.00,1100.00,2009,'45',true,'diemerweg 12,4554MG Amsterdam');
INSERT INTO maintenances (id,type_of_Maintenance, maintenance_date ) VALUES (1,'floorRepair', '2024-03-15'),
                                                                         (2,'refill heating system', '2024-03-20');

INSERT INTO complaints (id, date_of_complaint, complaint_message) VALUES (1,'2024-03-20','floor heating not working');