package com.bankingsystem.bank.model.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ClientDto {

    private String firstName;
    private String lastName;
    private String address;
    private String income;
    private int reputation;

    private String result;
}
