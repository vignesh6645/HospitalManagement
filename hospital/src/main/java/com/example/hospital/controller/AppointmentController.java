package com.example.hospital.controller;

import com.example.hospital.baseResponse.BaseResponse;
import com.example.hospital.dto.AppointmentDto;
import com.example.hospital.entity.Appointment;
import com.example.hospital.entity.User;
import com.example.hospital.serviece.AppointmentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RequestMapping("/appointment")
@RestController
public class AppointmentController {

    @Autowired
    private AppointmentInterface appointmentInterface;

    @PostMapping("/create")
    public BaseResponse<Appointment> addAppointmentInfo(@RequestBody AppointmentDto appointmentDto) {
        BaseResponse<Appointment> baseResponse=null;
        baseResponse = BaseResponse.<Appointment>builder().Data(appointmentInterface.AddAppointmentInfo(appointmentDto)).build();
        return baseResponse;
    }

    @GetMapping("/appointmentId/{id}")
    public BaseResponse<Optional<Appointment>> getAppointmentById(@PathVariable Integer id){
        BaseResponse<Optional<Appointment>> baseResponse = null;
        baseResponse = BaseResponse.<Optional<Appointment>>builder().Data(appointmentInterface.GetAppointmentById(id)).build();
        return baseResponse;
    }

    @PutMapping("/update")
    public BaseResponse<Optional<Appointment>> updateInfo(@RequestBody AppointmentDto appointmentDTO){
        BaseResponse<Optional<Appointment>> baseResponse=null;
        baseResponse = BaseResponse.<Optional<Appointment>>builder().Data(appointmentInterface.UpdateAppointmentById(appointmentDTO)).build();
        return baseResponse;
    }
    @DeleteMapping("/delete/{id}")
    public BaseResponse<Appointment> deleteAppointment(@PathVariable int id) {
        BaseResponse<Appointment> baseResponse = null;
        baseResponse=BaseResponse.<Appointment>builder().Data(appointmentInterface.deleteById(id)).build();
        return baseResponse;
    }
}
