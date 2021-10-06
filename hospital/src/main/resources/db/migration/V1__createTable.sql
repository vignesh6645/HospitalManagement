CREATE TABLE User(
user_id int NOT NULL AUTO_INCREMENT,
userName varchar(255) NOT NULL,
password varchar(255) NOT NULL,
is_active int default 0,
is_delete int default 0,
created_at timestamp,
modified_at timestamp,
PRIMARY KEY (user_id)
);
CREATE TABLE Patient(
patient_id int NOT NULL AUTO_INCREMENT,
patientName varchar(255) NOT NULL,
is_active int default 0,
is_delete int default 0,
created_at timestamp,
modified_at timestamp,
fk_user_id int,
PRIMARY KEY (patient_id)
);
CREATE TABLE Doctor(
doctor_id int NOT NULL AUTO_INCREMENT,
doctorName varchar(255) NOT NULL,
is_active int default 0,
is_delete int default 0,
created_at timestamp,
modified_at timestamp,
fk_user_id int,
PRIMARY KEY (doctor_id)
);
CREATE TABLE disease(
disease_id int NOT NULL AUTO_INCREMENT,
diseaseName varchar(255) NOT NULL,
is_active int default 0,
is_delete int default 0,
created_at timestamp,
modified_at timestamp,
PRIMARY KEY (disease_id)
);
CREATE TABLE Appointment(
appointment_id int NOT NULL AUTO_INCREMENT,
appointmentName varchar(255) NOT NULL,
is_active int default 0,
is_delete int default 0,
created_at timestamp,
modified_at timestamp,
fk_patient_id int,
fk_doctor_id int,
fk_disease_id int,
PRIMARY KEY (appointment_id)
);

alter table patient
add foreign key (fk_user_id) references user(user_id);

alter table Doctor
add foreign key (fk_user_id) references user(user_id);

alter table Appointment
add foreign key (fk_patient_id) references patient(patient_id),
add foreign key (fk_doctor_id) references Doctor(doctor_id),
add foreign key (fk_disease_id) references disease(disease_id);

