package com.example.hospital.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
@SQLDelete(sql = "UPDATE user SET is_delete = 1 WHERE user_id = ?")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active", columnDefinition = "integer default 0")
    private int isActive;

    @Column(name = "is_delete", columnDefinition = "integer default 0")
    private int isDelete;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private LocalDateTime updateDateTime;


    @OneToMany(mappedBy = "userId",cascade = CascadeType.ALL)
    private List<Patient> patients = new ArrayList<>();

    @OneToMany(mappedBy = "userId",cascade = CascadeType.ALL)
    private List<Doctor> doctors = new ArrayList<>();
}
