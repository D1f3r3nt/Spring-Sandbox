package com.diferent.springsandbox.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "rooms", schema = "spring_sandbox")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String level;

    private String door;

    private Integer capacity;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
