package com.example.hospital.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="Doctor")
@SQLDelete(sql = "UPDATE user SET is_delete = 1 WHERE user_id = ?")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Integer doctorId;

    @Column(name = "doctorName",nullable = false)
    private String doctorName;

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

    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "fk_user_id")
    private User userId;

    @OneToMany(mappedBy = "doctorId" , cascade = CascadeType.ALL)
    private List<Doctor>doctors =new ArrayList<>();

}
