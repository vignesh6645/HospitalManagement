package com.example.hospital.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Appointment")
@SQLDelete(sql = "UPDATE user SET is_delete = 1 WHERE user_id = ?")

public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Integer id;

    @Column(name = "appointmentName")
    private String appointmentName;

    @Column(name = "appointmentTime")
    private Timestamp appointmentTime;

    @Column(name = "is_active",columnDefinition = "integer default 0")
    private int isActive;

    @Column(name = "is_delete",columnDefinition = "integer default 0")
    private int isDelete;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private LocalDateTime updateDateTime;

    @ManyToOne
    @JoinColumn(name = "fk_doctor_id")
    private Doctor doctorId;

    @ManyToOne
    @JoinColumn(name = "fk_patient_id")
    private Patient patientId;

    @OneToOne
    @JoinColumn(name = "fk_disease_id")
    private Disease diseaseId;
}
