package com.example.hospital.servieceImplements;

import com.example.hospital.baseResponse.PageResponse;
import com.example.hospital.dto.DoctorDto;
import com.example.hospital.entity.Doctor;
import com.example.hospital.entity.User;
import com.example.hospital.repository.*;
import com.example.hospital.serviece.DoctorInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class DoctorServieceImplements implements DoctorInterface {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Doctor AddDoctorInfo(DoctorDto doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setDoctorName(doctorDTO.getDoctorName());
        doctorDTO.getUserId().forEach(userDTO -> {
            Optional<User> users=userRepository.findById(userDTO.getId());
            if (users.isPresent())
            {
                doctor.setUser(users.get());
                doctor.setDoctorId(doctorDTO.getDoctorId());
                doctor.setDoctorName(doctorDTO.getDoctorName());
            }
            else
            {

            }
        });
        doctorRepository.save(doctor);
        return doctor;
    }

    @Override
    public Optional<Doctor> GetDoctorById(int id) {
        Optional<Doctor> doctor=doctorRepository.findById(id);

        return doctor;
    }

    @Override
    public Doctor deleteById(int id){
        Doctor doctor = new Doctor();
        doctorRepository.deleteById(id);
        return doctor;
    }

    @Override
    public Optional<Doctor>  UpdateDoctorById(DoctorDto doctorDTO) {
        Optional<Doctor> exitsDoctor = doctorRepository.findById(doctorDTO.getDoctorId());
        if (exitsDoctor.isPresent())
        {
            exitsDoctor.get().setDoctorName(doctorDTO.getDoctorName());
        }
        else
        {

        }
        doctorDTO.getUserId().forEach(userDTO -> {
            Optional<User> users=userRepository.findById(userDTO.getId());
            if (users.isPresent())
            {
                exitsDoctor.get().setUser(users.get());
            }
            else
            {

            }

        });
        doctorRepository.save(exitsDoctor.get());
        return exitsDoctor;
    }

    @Override
    public PageResponse<Doctor> GetDoctorWithPagination(int offset, int pageSize, String doctorName) {
        PageResponse pageResponse=new PageResponse();
        try {
            Pageable paging= PageRequest.of(offset,pageSize);
            Page<Doctor> doctors = doctorRepository.searchAllByDoctorNameLike("%" + doctorName + "%", paging);
            pageResponse.setResponse(doctors);
            pageResponse.setRecordCount(doctors.getTotalPages());
        } catch (
                NoSuchElementException e) {

        }
        return pageResponse;
    }
}
