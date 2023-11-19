package com.bankingsystem.bank.controller;

import com.bankingsystem.bank.model.Client;
import com.bankingsystem.bank.model.dto.ClientDto;
import com.bankingsystem.bank.model.mapper.ClientMapper;
import com.bankingsystem.bank.repository.ClientRepository;
import com.bankingsystem.bank.service.ClientServiceImplementation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientServiceImplementation clientService;

    @MockBean
    private ClientController clientController;
    @Autowired
    private ClientRepository clientRepository;

    @Test
    void shouldBeSavedTheNewClient() throws Exception {
        ClientDto clientDto = new ClientDto("test","test","test","test",1, "test");

        when(clientController.saveNewClient(clientDto));
        String clientId = ClientMapper.CLIENT_MAPPER.toClient(clientDto).getId();

        mockMvc.perform(post("/client"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(clientId)));

        verify(clientController).getTheLastClientAddedToDB();
    }

    @Test
    void shouldBeEligibleMessage() throws Exception {
        ClientDto clientDto = clientRepository.getTheLastClientAdded();
        String clientId = ClientMapper.CLIENT_MAPPER.toClient(clientDto).getId();

        when(clientController.isTheClientEligible(clientId));
        mockMvc.perform(get("client/{clientId}/eligibility"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(clientId)));
        verify(clientController).isTheClientEligible(clientId);
    }

    @Test
    void shouldBePerformedClientCheck() throws Exception {
        ClientDto clientDto = clientRepository.getTheLastClientAdded();
        String clientId = ClientMapper.CLIENT_MAPPER.toClient(clientDto).getId();

        when(clientController.performClientCheck(clientId));
        mockMvc.perform(get("client/{clientId}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(clientId)));
        verify(clientController).performClientCheck(clientId);
    }
}
