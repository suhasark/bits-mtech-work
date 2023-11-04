-- Seed data in the system
insert into PAYMENT_TYPE(Payment_Method,Payment_Description,Last_Updated_By,Last_Updated_On) values('Cash','Cash Payment','Admin',CURRENT_TIMESTAMP());

insert into PARKING_SLOT(Slot_number,Occupancy_Status,Floor_Number,Last_Updated_By,Last_Updated_On) values(201,'AVAILABLE',-2,'Admin',CURRENT_TIMESTAMP());
insert into PARKING_SLOT(Slot_number,Occupancy_Status,Floor_Number,Last_Updated_By,Last_Updated_On) values(202,'AVAILABLE',-2,'Admin',CURRENT_TIMESTAMP());
insert into PARKING_SLOT(Slot_number,Occupancy_Status,Floor_Number,Last_Updated_By,Last_Updated_On) values(203,'AVAILABLE',-2,'Admin',CURRENT_TIMESTAMP());
insert into PARKING_SLOT(Slot_number,Occupancy_Status,Floor_Number,Last_Updated_By,Last_Updated_On) values(204,'AVAILABLE',-2,'Admin',CURRENT_TIMESTAMP());
insert into PARKING_SLOT(Slot_number,Occupancy_Status,Floor_Number,Last_Updated_By,Last_Updated_On) values(205,'AVAILABLE',-2,'Admin',CURRENT_TIMESTAMP());

insert into PARKING_SLOT(Slot_number,Occupancy_Status,Floor_Number,Last_Updated_By,Last_Updated_On) values(101,'AVAILABLE',-1,'Admin',CURRENT_TIMESTAMP());
insert into PARKING_SLOT(Slot_number,Occupancy_Status,Floor_Number,Last_Updated_By,Last_Updated_On) values(102,'AVAILABLE',-1,'Admin',CURRENT_TIMESTAMP());
insert into PARKING_SLOT(Slot_number,Occupancy_Status,Floor_Number,Last_Updated_By,Last_Updated_On) values(103,'AVAILABLE',-1,'Admin',CURRENT_TIMESTAMP());
insert into PARKING_SLOT(Slot_number,Occupancy_Status,Floor_Number,Last_Updated_By,Last_Updated_On) values(104,'AVAILABLE',-1,'Admin',CURRENT_TIMESTAMP());
insert into PARKING_SLOT(Slot_number,Occupancy_Status,Floor_Number,Last_Updated_By,Last_Updated_On) values(105,'AVAILABLE',-1,'Admin',CURRENT_TIMESTAMP());

insert into PARKING_SLOT(Slot_number,Occupancy_Status,Floor_Number,Last_Updated_By,Last_Updated_On) values(001,'AVAILABLE',0,'Admin',CURRENT_TIMESTAMP());
insert into PARKING_SLOT(Slot_number,Occupancy_Status,Floor_Number,Last_Updated_By,Last_Updated_On) values(002,'AVAILABLE',0,'Admin',CURRENT_TIMESTAMP());
insert into PARKING_SLOT(Slot_number,Occupancy_Status,Floor_Number,Last_Updated_By,Last_Updated_On) values(003,'AVAILABLE',0,'Admin',CURRENT_TIMESTAMP());
insert into PARKING_SLOT(Slot_number,Occupancy_Status,Floor_Number,Last_Updated_By,Last_Updated_On) values(004,'AVAILABLE',0,'Admin',CURRENT_TIMESTAMP());
insert into PARKING_SLOT(Slot_number,Occupancy_Status,Floor_Number,Last_Updated_By,Last_Updated_On) values(005,'AVAILABLE',0,'Admin',CURRENT_TIMESTAMP());

insert into BILLING_POLICY(Billing_Policy_Id,Description,Start_Time_In_Minutes,End_Time_In_Minutes,Parking_Rate,Last_Updated_By,Last_Updated_On) values(100,'0 to 120',0,120,10,'Admin',CURRENT_TIMESTAMP());

insert into VEHICLE(Registration_Number,Vehicle_Type,Vehicle_Brand,Vehicle_Model,Vehicle_Color,Registration_Type,Owner,Last_Updated_By,Last_Updated_On)VALUES('KA51Z5732','FOUR_WHEELER','HONDA','JAZZ','RED','PRIVATE',100,current_timestamp(),null );
insert into VEHICLE(Registration_Number,Vehicle_Type,Vehicle_Brand,Vehicle_Model,Vehicle_Color,Registration_Type,Owner,Last_Updated_By,Last_Updated_On)VALUES('KA01MV5922','FOUR_WHEELER','HONDA','BRIO','RED','PRIVATE',100,current_timestamp(),null );
insert into VEHICLE(Registration_Number,Vehicle_Type,Vehicle_Brand,Vehicle_Model,Vehicle_Color,Registration_Type,Owner,Last_Updated_By,Last_Updated_On)VALUES('KA51EQ1825','TWO_WHEELER','TVS','JUPITER','BLACK','PRIVATE',100,current_timestamp(),null );
insert into VEHICLE(Registration_Number,Vehicle_Type,Vehicle_Brand,Vehicle_Model,Vehicle_Color,Registration_Type,Owner,Last_Updated_By,Last_Updated_On)VALUES('KA05NE1744','TWO_WHEELER','TVS','JUPITER','BLACK','PRIVATE',100,current_timestamp(),null );
insert into PARKING_TRANSACTION(Parking_Transaction_Id,Vehicle_In_Time,Vehicle_Out_Time,Occupied_Slot_Number,Vehicle_Registration_Number,Ticket_Id,Last_Updated_By,Last_Updated_On) VALUES(1,current_timestamp(),null, 005,'KA51Z5732',null,'Admin',current_timestamp());
insert into PARKING_TRANSACTION(Parking_Transaction_Id,Vehicle_In_Time,Vehicle_Out_Time,Occupied_Slot_Number,Vehicle_Registration_Number,Ticket_Id,Last_Updated_By,Last_Updated_On) VALUES(2,current_timestamp(),null, 105,'KA01MV5922',null,'Admin',current_timestamp());
insert into PARKING_TRANSACTION(Parking_Transaction_Id,Vehicle_In_Time,Vehicle_Out_Time,Occupied_Slot_Number,Vehicle_Registration_Number,Ticket_Id,Last_Updated_By,Last_Updated_On) VALUES(3,current_timestamp(),null, 205,'KA51EQ1825',null,'Admin',current_timestamp());
