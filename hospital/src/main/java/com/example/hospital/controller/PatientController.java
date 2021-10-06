package com.example.hospital.controller;


import com.example.hospital.baseResponse.BaseResponse;
import com.example.hospital.baseResponse.PageResponse;
import com.example.hospital.dto.PatientDto;
import com.example.hospital.entity.Patient;
import com.example.hospital.serviece.PatientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/patient")
@RestController
public class PatientController {

    @Autowired
    private PatientInterface patientInterface;

    @PostMapping("/create")
    public BaseResponse<Patient> addPatient(@RequestBody PatientDto patientDTO) {
        BaseResponse<Patient> baseResponse=null;
        baseResponse = BaseResponse.<Patient>builder().Data(patientInterface.AddPatientInfo(patientDTO)).build();
        return baseResponse;

    }

    @GetMapping("/patient/{id}")
    public BaseResponse<Optional<Patient>> getPatientById(@PathVariable Integer id){
        BaseResponse<Optional<Patient>> baseResponse=null;
        baseResponse = BaseResponse.<Optional<Patient>>builder().Data(patientInterface.GetPatientById(id)).build();
        return baseResponse;

    }

    @DeleteMapping("/delete/{id}")
    public BaseResponse<Patient>  delete(@PathVariable int id) {
        BaseResponse<Patient> baseResponse=null;
        baseResponse = BaseResponse.<Patient>builder().Data(patientInterface.deleteById(id)).build();
        return baseResponse;
    }

    @PutMapping("/update")
    public BaseResponse<Optional<Patient>> update(@RequestBody PatientDto patientDTO){
        BaseResponse<Optional<Patient>> baseResponse=null;
        baseResponse = BaseResponse.<Optional<Patient>>builder().Data(patientInterface.UpdatePatientById(patientDTO)).build();
        return baseResponse;
    }

    @GetMapping("/pagination/{offset}/{pageSize}/{patientName}")
    private PageResponse<Patient> getPatientWithPagination(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String patientName){
        return patientInterface.GetPatientWithPagination(offset, pageSize, patientName);
    }
}
