package com.cbccafe.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "user name is required")
    @Column(name = "user_name")
    private String userName;

    @NotBlank(message = "password is required")
    @Column(name = "password")
    private String password;


    @OneToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "employee_id",
            referencedColumnName = "id"
    )
    private Employee employee;
}
