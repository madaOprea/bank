package com.bankingsystem.bank.service;

import com.bankingsystem.bank.model.Document;
import com.bankingsystem.bank.model.dto.DocumentDto;

public interface DocumentService {

    Document generateDocument(String clientId);
}
