package org.hatice.ikplus.entity.usermanagement;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.userenums.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String role;
    @Enumerated(EnumType.STRING)
    private UserGender gender;
    private String phoneNumber;
    private Date birthDate;
    @Enumerated(EnumType.STRING)
    private UserMaritalStatus maritalStatus;
    @Enumerated(EnumType.STRING)
    private UserBloodType bloodType;
    private String identificationNumber;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private UserEducationLevel educationLevel;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}