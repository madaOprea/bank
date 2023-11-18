package com.bankingsystem.bank.service;

import com.bankingsystem.bank.model.Client;
import com.bankingsystem.bank.model.Document;
import com.bankingsystem.bank.model.dto.ClientDto;
import com.bankingsystem.bank.model.dto.DocumentDto;
import com.bankingsystem.bank.model.mapper.ClientMapper;
import com.bankingsystem.bank.model.mapper.DocumentMapper;
import com.bankingsystem.bank.repository.ClientRepository;
import com.bankingsystem.bank.repository.DocumentRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DocumentServiceImplementation implements DocumentService {
    private static final Logger logger = LoggerFactory.getLogger(DocumentServiceImplementation.class);
    private DocumentMapper documentMapper;
    private ClientMapper clientMapper;

    private DocumentRepository documentRepository;
    private ClientRepository clientRepository;

    @Override
    public Document generateDocument(String clientId) {
        Optional<Client> currentClient = clientRepository.findById(clientId);

        // Generate document
        Document document = new Document();
        document.setClient(currentClient.get());
        document.setExpirationDate(LocalDate.of(2011, 1,11));
        documentRepository.save(document);
        return document;
    }
}
