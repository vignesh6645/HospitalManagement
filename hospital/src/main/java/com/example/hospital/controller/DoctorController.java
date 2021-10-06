package com.example.hospital.controller;

import com.example.hospital.baseResponse.BaseResponse;
import com.example.hospital.baseResponse.PageResponse;
import com.example.hospital.dto.DoctorDto;
import com.example.hospital.entity.Doctor;
import com.example.hospital.serviece.DoctorInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/doctor")
@RestController
public class DoctorController {

    @Autowired
    private DoctorInterface doctorInterface;

    @PostMapping("/create")
    public BaseResponse<Doctor> AddDoctorInfo(@RequestBody DoctorDto doctorDTO) {
        BaseResponse<Doctor> baseResponse=null;
        baseResponse = BaseResponse.<Doctor>builder().Data(doctorInterface.AddDoctorInfo(doctorDTO)).build();
        return baseResponse;
    }

    @GetMapping("/getById/{id}")
    public BaseResponse<Optional<Doctor>> getDoctorById(@PathVariable int id){
        BaseResponse<Optional<Doctor>> baseResponse=null;
        baseResponse = BaseResponse.<Optional<Doctor>>builder().Data(doctorInterface.GetDoctorById(id)).build();
        return baseResponse;

    }

    @PutMapping("/update")
    public BaseResponse<Optional<Doctor>> updateInfo(@RequestBody DoctorDto doctorDTO){
        BaseResponse<Optional<Doctor>> baseResponse=null;
        baseResponse = BaseResponse.<Optional<Doctor>>builder().Data(doctorInterface.UpdateDoctorById(doctorDTO)).build();
        return baseResponse;
    }

    @GetMapping("/pagination/{offset}/{pageSize}/{doctorName}")
    private PageResponse<Doctor> getDoctorWithPagination(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String doctorName){
        return doctorInterface.GetDoctorWithPagination(offset, pageSize, doctorName);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        doctorInterface.deleteById(id);
        return "Success";
    }
}

