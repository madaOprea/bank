package com.bankingsystem.bank.model.mapper;

import com.bankingsystem.bank.model.Client;
import com.bankingsystem.bank.model.dto.ClientDto;
import com.bankingsystem.bank.model.dto.ClientEvaluatedDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientMapperImplementation implements ClientMapper {
    @Override
    public List<ClientDto> toClientDtos(List<Client> clientsList) {
        return null;
    }

    @Override
    public ClientDto toClientDto(Client client) {
        if (client == null ) {
            return null;
        }

        ClientDto.ClientDtoBuilder clientDtoBuilder = ClientDto.builder();
        clientDtoBuilder.firstName(client.getFirstName());
        clientDtoBuilder.lastName(client.getLastName());
        clientDtoBuilder.address(client.getAddress());
        clientDtoBuilder.income(client.getIncome());
        clientDtoBuilder.reputation(client.getReputation());

        return clientDtoBuilder.build();
    }

    @Override
    public Client toClient(ClientDto clientDto) {
        if (clientDto == null) {
            return null;
        }

        Client.ClientBuilder clientBuilder = Client.builder();
        clientBuilder.firstName(clientDto.getFirstName());
        clientBuilder.lastName(clientDto.getLastName());
        clientBuilder.address(clientDto.getAddress());
        clientBuilder.income(clientDto.getIncome());
        clientBuilder.reputation(clientDto.getReputation());

        return clientBuilder.build();
    }

    @Override
    public ClientEvaluatedDto toClientEvaluatedDto(Client client) {
        if (client == null) {
            return null;
        }

        ClientEvaluatedDto.ClientEvaluatedDtoBuilder clientEvaluatedDto = ClientEvaluatedDto.builder();
        clientEvaluatedDto.income(client.getIncome());
        clientEvaluatedDto.reputation(client.getReputation());

        return clientEvaluatedDto.build();
    }

    @Override
    public Client toClient(ClientEvaluatedDto clientEvaluatedDto){
        if (clientEvaluatedDto == null) {
            return null;
        }

        Client.ClientBuilder clientBuilder = Client.builder();
        clientBuilder.income(clientEvaluatedDto.getIncome());
        clientBuilder.reputation(clientEvaluatedDto.getReputation());

        return clientBuilder.build();
    }
}
