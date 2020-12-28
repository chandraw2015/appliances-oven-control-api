
INSERT INTO oven (id, name, model, version , ovenState) VALUES
(1,'Murphy', 'TN-202', '2.0.5' , 'STOPPED');



INSERT INTO program (id, name, temperature, time , oven_id) VALUES
(1,'Recipe1', '350', '6000' , '1');
INSERT INTO program (id, name, temperature, time , oven_id) VALUES
(2,'Recipe2', '420', '5000' , '1');

