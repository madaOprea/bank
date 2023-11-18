package com.bankingsystem.bank.model.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ClientEvaluatedDto {

    private String income;
    private int reputation;
}
