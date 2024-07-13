package com.cbccafe.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "single_items")
public class SingleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(name = "item_name")
    private String itemName;

    @NotNull(message = "price is required")
    @Column(name = "price")
    private Double price;

    @Column(name = "active_status")
    private Integer activeStatus;

    @Column(name = "approval_status")
    private Integer approvalStatus;

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
