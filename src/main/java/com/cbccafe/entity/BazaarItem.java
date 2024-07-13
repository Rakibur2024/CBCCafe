package com.cbccafe.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bazaar_items")
public class BazaarItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "entered_at")
    @JsonFormat(pattern = "yyyy-MM-dd" , timezone = "UTC")
    private LocalDate enteredAt;

    @Column(name = "entered_by")
    private String enteredBy;

    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd" , timezone = "UTC")
    private LocalDate updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

}
