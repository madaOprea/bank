package com.bankingsystem.bank.service;

import com.bankingsystem.bank.model.Client;
import com.bankingsystem.bank.model.dto.ClientDto;
import com.bankingsystem.bank.model.dto.ClientEvaluatedDto;
import com.bankingsystem.bank.model.dto.DocumentDto;

public interface ClientService {

    boolean isClient(String clientId);
    boolean isClientEligible(String clientId);
    boolean isDocumentExpired(DocumentDto documentDto);
    ClientDto getTheLastClientAdded();
    String performClientCheck(String clientId);
    String evaluateClient(int reputationScore);
    int queryReputationSystem(String clientId);

    ClientDto saveNewClient(ClientDto clientDto);
    ClientEvaluatedDto updateIncome(String clientId, String income);
}
