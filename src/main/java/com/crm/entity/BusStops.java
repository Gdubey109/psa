package com.crm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bus_stops")
public class BusStops {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generates ID
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stop_id", nullable = false) // Make nullable=false for consistency
    private Stop stop;

    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false) // Make nullable=false for consistency
    private Bus bus;

    @Column(name = "stop_order", nullable = false)
    private Integer stopOrder;
}
