package com.bankingsystem.bank.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "client")
@NoArgsConstructor
public class Client {

    @Id
    @Column(name = "client_uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String address;

    @Column
    private String income;

    @Column
    private int reputation;
}
