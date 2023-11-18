package com.bankingsystem.bank.model.mapper;

import com.bankingsystem.bank.model.Client;
import com.bankingsystem.bank.model.Document;
import com.bankingsystem.bank.model.dto.ClientDto;
import com.bankingsystem.bank.model.dto.DocumentDto;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapperImplementation implements DocumentMapper{

    @Override
    public DocumentDto documentToDocumentDto(Document document) {
        if (document == null) {
            return null;
        }

        DocumentDto.DocumentDtoBuilder documentDtoBuilder = DocumentDto.builder();

        documentDtoBuilder.clientDto(clientToClientDto(document.getClient()));
        documentDtoBuilder.expirationDate(document.getExpirationDate());

        return documentDtoBuilder.build();
    }

    @Override
    public Document documentDtoToDocument(DocumentDto documentDto) {
        if (documentDto == null) {
            return null;
        }

        Document.DocumentBuilder documentBuilder = Document.builder();

        documentBuilder.client(clientDtoToClient(documentDto.getClientDto()));
        documentBuilder.expirationDate(documentDto.getExpirationDate());

        return documentBuilder.build();
    }


    protected ClientDto clientToClientDto(Client client) {
        if (client == null) {
            return null;
        }

        ClientDto.ClientDtoBuilder clientDtoBuilder = ClientDto.builder();

        clientDtoBuilder.firstName(client.getFirstName());
        clientDtoBuilder.lastName(client.getLastName());
        clientDtoBuilder.address(client.getAddress());
        clientDtoBuilder.reputation(client.getReputation());

        return clientDtoBuilder.build();
    }

    protected Client clientDtoToClient(ClientDto clientDto) {
        if (clientDto == null ) {
            return null;
        }

        Client.ClientBuilder clientBuilder = Client.builder();

        clientBuilder.firstName(clientDto.getFirstName());
        clientBuilder.lastName(clientDto.getLastName());
        clientBuilder.address(clientDto.getAddress());
        clientBuilder.reputation(clientDto.getReputation());

        return clientBuilder.build();
    }

}
