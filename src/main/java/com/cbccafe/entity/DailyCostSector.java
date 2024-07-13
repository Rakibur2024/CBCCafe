package com.cbccafe.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "daily_cost_sectors")
public class DailyCostSector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(name = "sector_name")
    private String sectorName;

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
