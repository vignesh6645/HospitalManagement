package com.example.hospital.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentDto {

    private Integer appointmentId;

    private String appointmentName;

    private Timestamp appointmentTime;

    private List<DiseaseDto> diseaseId;

    private List<PatientDto> patientId;

    private List<DoctorDto> doctorId;
}
