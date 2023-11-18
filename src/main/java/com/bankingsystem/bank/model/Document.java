package com.bankingsystem.bank.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Entity
@Table(name="document")
public class Document {

    @Id
    @Column(name = "document_uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "client_uuid")
    private Client client;

    @Column
    private LocalDate expirationDate;
}
