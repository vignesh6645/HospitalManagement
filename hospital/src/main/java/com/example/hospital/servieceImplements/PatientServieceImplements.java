package com.example.hospital.servieceImplements;

import com.example.hospital.baseResponse.PageResponse;
import com.example.hospital.dto.PatientDto;
import com.example.hospital.entity.Patient;
import com.example.hospital.entity.User;
import com.example.hospital.repository.PatientRepository;
import com.example.hospital.repository.UserRepository;
import com.example.hospital.serviece.PatientInterface;
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
public class PatientServieceImplements implements PatientInterface {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient AddPatientInfo(PatientDto patientDTO) {
        Patient patient = new Patient();
        patient.setPatientName(patientDTO.getPatientName());
        Patient finalPatient = patient;
        patientDTO.getUserId().forEach(userDTO -> {
            Optional<User> users=userRepository.findById(userDTO.getId());
            if(users.isPresent())
            {
                finalPatient.setUserId(users.get());
            }

        });
        patient.setPatientId(patientDTO.getPatientId());
        patient.setPatientName(patientDTO.getPatientName());
        patientRepository.save(patient);

        return patient;
    }

    @Override
    public Optional<Patient> GetPatientById(Integer id) {
        Optional<Patient> patient=patientRepository.findById(id);
        return patient;

    }

    @Override
    public Patient deleteById(int id){
        Patient patient = new Patient();
        userRepository.deleteById(id);
        return patient;
    }

    @Override
    public Optional<Patient> UpdatePatientById(PatientDto patientDTO) {
        Optional<Patient> exitsPatient = patientRepository.findById(patientDTO.getPatientId());
        if (exitsPatient.isPresent())
        {
            exitsPatient.get().setPatientName(patientDTO.getPatientName());
        }

            patientDTO.getUserId().forEach(userDTO -> {
            Optional<User> users=userRepository.findById(userDTO.getId());
            if (users.isPresent())
            {
                exitsPatient.get().setUserId(users.get());
            }
            else
            {

            }

        });
        patientRepository.save(exitsPatient.get());
        return exitsPatient;
    }
    @Override
    public PageResponse<Patient> GetPatientWithPagination(int offset, int pageSize, String patientName) {
        PageResponse pageResponse=new PageResponse();
        try {
            Pageable paging= PageRequest.of(offset,pageSize);
            Page<Patient> patients = patientRepository.searchAllByPatientNameLike("%" + patientName + "%", paging);
            pageResponse.setResponse(patients);
            pageResponse.setRecordCount(patients.getTotalPages());
        } catch (
                NoSuchElementException e) {

        }
        return pageResponse;
    }
}
