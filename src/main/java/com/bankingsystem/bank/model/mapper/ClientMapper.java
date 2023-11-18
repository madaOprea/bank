package com.bankingsystem.bank.model.mapper;

import com.bankingsystem.bank.model.Client;
import com.bankingsystem.bank.model.dto.ClientDto;
import com.bankingsystem.bank.model.dto.ClientEvaluatedDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper CLIENT_MAPPER = Mappers.getMapper(ClientMapper.class);

    List<ClientDto> toClientDtos(List<Client> clientsList);
    ClientDto toClientDto(Client client);
    Client toClient(ClientDto clientDto);

    ClientEvaluatedDto toClientEvaluatedDto(Client client);
    Client toClient(ClientEvaluatedDto clientDto);
}
