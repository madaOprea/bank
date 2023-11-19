package com.bankingsystem.bank.service;

import com.bankingsystem.bank.model.Client;
import com.bankingsystem.bank.model.dto.ClientDto;
import com.bankingsystem.bank.model.mapper.ClientMapper;
import com.bankingsystem.bank.repository.ClientRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.mockito.Mockito.*;

public class ClientServiceTest {

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveNewClient() {
        ClientDto clientDto = new ClientDto("FirstName", "LastName", "Address","Income",1, "");

        when(clientMapper.toClient(any(ClientDto.class)));
        when(clientRepository.save(any(Client.class)));
        when(clientMapper.toClientDto(any(Client.class)));

        ClientDto savedClient = clientService.saveNewClient(clientDto);

        verify(clientMapper, times(1)).toClient(clientDto);
        verify(clientRepository, times(1)).save(any(Client.class));
        verify(clientMapper, times(1)).toClientDto(any(Client.class));
    }
}
