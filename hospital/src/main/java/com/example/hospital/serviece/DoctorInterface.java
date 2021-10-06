package com.example.hospital.serviece;

import com.example.hospital.baseResponse.PageResponse;
import com.example.hospital.dto.DoctorDto;
import com.example.hospital.entity.Doctor;

import java.util.Optional;

public interface DoctorInterface {
    Doctor AddDoctorInfo(DoctorDto doctorDTO);

    Optional<Doctor> GetDoctorById(int id);

    Optional<Doctor> UpdateDoctorById(DoctorDto doctorDTO);

    PageResponse<Doctor> GetDoctorWithPagination(int offset, int pageSize, String doctorName);

    Doctor deleteById(int id);
}
