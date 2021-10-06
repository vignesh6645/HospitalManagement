package com.example.hospital.servieceImplements;

import com.example.hospital.dto.AppointmentDto;
import com.example.hospital.entity.Appointment;
import com.example.hospital.entity.Disease;
import com.example.hospital.entity.Doctor;
import com.example.hospital.entity.Patient;
import com.example.hospital.repository.AppointmentRepository;
import com.example.hospital.repository.DiseaseRepository;
import com.example.hospital.repository.DoctorRepository;
import com.example.hospital.repository.PatientRepository;
import com.example.hospital.serviece.AppointmentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AppointmentServieceImplements implements AppointmentInterface {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;

    @Override
    public Appointment AddAppointmentInfo(AppointmentDto appointmentDTO) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentName(appointmentDTO.getAppointmentName());
        appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
        Appointment finalAppointment = appointment;
        appointmentDTO.getPatientId().forEach(patientDTO -> {
            Optional<Patient> patient=patientRepository.findByPatientId(patientDTO.getPatientId());
            if(patient.isPresent())
            {
                finalAppointment.setPatientId(patient.get());
            }
            else
            {

            }
        });
        Appointment finalAppointment1 = appointment;
        appointmentDTO.getDoctorId().forEach(doctorDTO -> {
            Optional<Doctor> doctor=doctorRepository.findByDoctorId(doctorDTO.getDoctorId());
            if (doctor.isPresent())
            {
                finalAppointment1.setDoctorId(doctor.get());
            }
            else
            {

            }
        });
        Appointment finalAppointment2 = appointment;
        appointmentDTO.getDiseaseId().forEach(diseaseDTO -> {
            Optional<Disease> disease=diseaseRepository.findByDiseaseId(diseaseDTO.getDiseaseId());
            if (disease.isPresent())
            {
                finalAppointment2.setDiseaseId(disease.get());
            }
            else
            {

            }
        });
        appointment= appointmentRepository.save(appointment);

        return appointment;
    }


    @Override
    public Optional<Appointment> GetAppointmentById(Integer id) {
        Optional<Appointment> appointment=appointmentRepository.findById(id);
        return appointment;
    }

    @Override
    public Appointment deleteById(int id){
        Appointment appointment = new Appointment();
        appointmentRepository.deleteById(id);
        return appointment;
    }

    @Override
    public Optional<Appointment> UpdateAppointmentById(AppointmentDto appointmentDTO) {
        Optional<Appointment> existAppointment = appointmentRepository.findById(appointmentDTO.getAppointmentId());
        if (existAppointment.isPresent())
        {
            existAppointment.get().setAppointmentName(appointmentDTO.getAppointmentName());
            existAppointment.get().setAppointmentTime(appointmentDTO.getAppointmentTime());
        }
        else
        {

        }
        appointmentDTO.getPatientId().forEach(patientDTO -> {
            Optional<Patient> patientId=patientRepository.findByPatientId(patientDTO.getPatientId());
            if (patientId.isPresent())
            {
                existAppointment.get().setPatientId(patientId.get());
            }
            else
            {

            }
        });

        appointmentDTO.getDoctorId().forEach(doctorDTO -> {
            Optional<Doctor> doctorId=doctorRepository.findByDoctorId(doctorDTO.getDoctorId());
            if (doctorId.isPresent())
            {
                existAppointment.get().setDoctorId(doctorId.get());
            }
            else
            {

            }
        });

        appointmentDTO.getDiseaseId().forEach(diseaseDTO -> {
            Optional<Disease> diseaseId=diseaseRepository.findByDiseaseId(diseaseDTO.getDiseaseId());
            if (diseaseId.isPresent())
            {
                existAppointment.get().setDiseaseId(diseaseId.get());
            }
            else
            {

            }
        });
        appointmentRepository.save(existAppointment.get());

        return existAppointment;
    }

}
