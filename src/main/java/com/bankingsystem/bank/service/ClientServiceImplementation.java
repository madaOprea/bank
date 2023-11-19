package com.bankingsystem.bank.service;

import com.bankingsystem.bank.model.*;
import com.bankingsystem.bank.model.dto.*;
import com.bankingsystem.bank.model.mapper.*;

import java.time.LocalDate;
import java.util.Optional;

import com.bankingsystem.bank.repository.ClientRepository;
import com.bankingsystem.bank.repository.DocumentRepository;
import lombok.AllArgsConstructor;
import org.slf4j.*;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ClientServiceImplementation implements ClientService {

    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImplementation.class);
    private DocumentMapper documentMapper;
    private ClientMapper clientMapper;

    private ClientRepository clientRepository;
    private DocumentRepository documentRepository;

    public boolean isClient(String clientId) {
        logger.info(this.getClass() + " *** isClient method");

        if (clientRepository.findById(clientId) != null) {
            return true;
        } else {
            logger.error("Client do not exist in database!");
            return false;
        }
    }

    public boolean isClientEligible(String clientId) {
        logger.info(this.getClass() + " *** isClientEligible method");

        if (!isClient(clientId)) {
            return false;
        }
        Optional<Client> client = clientRepository.findById(clientId);
        Document document = documentRepository.findByClient(client.get());

        if (document == null) {
            return false;
        }

        DocumentDto documentDto = documentMapper.documentToDocumentDto(document);

        if (!isDocumentExpired(documentDto)) {
            logger.error("Document is expired. Client ID: {}, Expiration Date: {}" +
                    clientId + " " + documentDto.getExpirationDate());
            return false;
        }

        ClientDto clientDto = clientMapper.toClientDto(client.get());
        int reputation = clientDto.getReputation();
        if (isDocumentExpired(documentDto) || reputation > 99 || !isClientEligible(clientId)) {
            logger.error("Client is not eligible. Client ID: {}, Expiration Date: {}, Reputation: {}, Exists: {}" +
                    clientId + " " + document.getExpirationDate() + " " + reputation + " " + isClientEligible(clientId));
            return false;
        } else if (reputation >= 21 && reputation <= 99) {
            return true;
        } else {
            logger.error("Client may have low reputation but still eligible. Client ID: {}, Reputation: {}"+ clientId + " " + reputation);
            return true;
        }
    }

    public boolean isDocumentExpired(DocumentDto documentDto) {
        logger.info(this.getClass() + " *** isDocumentExpired method");

        Document document = documentMapper.documentDtoToDocument(documentDto);
        LocalDate documentDate = document.getExpirationDate();
        return documentDate.isBefore(LocalDate.now());
    }

    public String evaluateClient(int reputationScore) {
        logger.info(this.getClass() + " *** evaluateClient method");

        if (reputationScore >= 1000) {
            return "Excellent";
        } else if (reputationScore >= 300) {
            return "Good";
        } else if (reputationScore >= 200) {
            return "Fair";
        } else if (reputationScore >= 100) {
            return "Poor";
        } else {
            return "Very Poor";
        }
    }

    public String performClientCheck(String clientId) {
        logger.info(this.getClass() + " *** performClientCheck method");

        Optional<Client> clientOptional = clientRepository.findById(clientId);
        Client client = clientOptional.get();
        Document document = documentRepository.findByClient(client);
        DocumentDto documentDto = documentMapper.documentToDocumentDto(document);

        String result;

        if (isDocumentExpired(documentDto)) {
            result = "Document Expired";
        } else {
            int reputationScore = queryReputationSystem(clientId);
            result = evaluateClient(reputationScore);
        }

        ClientDto resultDTO = new ClientDto();
        resultDTO.setResult(result);
        return resultDTO.getResult();
    }

    public int queryReputationSystem(String clientId) {
        logger.info(this.getClass() + " *** queryReputationSystem method");

        return clientRepository.findById(clientId).get().getReputation();
    }

    public ClientDto saveNewClient(ClientDto clientDto) {
        logger.info(this.getClass() + " *** saveNewClient method");

        Client newUser = clientMapper.toClient(clientDto)
                .builder()
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .address(clientDto.getAddress())
                .reputation(1).build();

        return clientMapper.toClientDto(clientRepository.save(newUser));
    }

    public ClientEvaluatedDto updateIncome(String clientId, String income) {
        logger.info(this.getClass() + " *** updateIncome method");

        Optional<Client> existingClientOptional = clientRepository.findById(clientId);
        if (!existingClientOptional.isPresent()) {
            throw new RuntimeException("Client not found with ID: " + clientId);
        }

        Client existingClient = existingClientOptional.get();
        existingClient.setIncome(income);
        existingClient.setReputation(existingClient.getReputation() + 10);
        clientRepository.save(existingClient);

        return clientMapper.toClientEvaluatedDto(existingClient);
    }
}
