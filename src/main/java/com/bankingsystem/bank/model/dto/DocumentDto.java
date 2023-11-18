package com.bankingsystem.bank.model.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class DocumentDto {

    private ClientDto clientDto;
    private LocalDate expirationDate;
}
