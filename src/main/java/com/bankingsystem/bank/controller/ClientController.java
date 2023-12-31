package com.bankingsystem.bank.controller;

import com.bankingsystem.bank.model.dto.*;
import com.bankingsystem.bank.service.*;

import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientServiceImplementation clientServiceImplementation;
    @Autowired
    private DocumentServiceImplementation documentServiceImplementation;

    @GetMapping("/{clientId}/eligibility")
    @ApiOperation(value = "Check Client Eligibility", notes = "Check if a client is eligible for enrollment")
    public ResponseEntity<String> isTheClientEligible(@PathVariable String clientId) {
        log.info(this.getClass() + " *** performClientCheck method");
        boolean isEligible = clientServiceImplementation.isClientEligible(clientId);

        if (isEligible) {
            return ResponseEntity.status(HttpStatus.OK).body("Client is eligible for enrollment.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Client is not eligible for enrollment.");
        }
    }

    @GetMapping("/{clientId}")
    @ApiOperation(value = "Perform Client Check", notes = "Check the reputation for enrollment")
    public ResponseEntity<String> performClientCheck(@PathVariable String clientId) {
        log.info(this.getClass() + " *** performClientCheck method");

        String result = clientServiceImplementation.performClientCheck(clientId);

        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/getLastClient")
    @ApiOperation(value = "Get the last client", notes = "After introducing the client you can check the client")
    public ResponseEntity<ClientDto> getTheLastClientAddedToDB() {
        log.info(this.getClass() + " *** getTheLastClientAddedToDB");

        ClientDto lastClient = clientServiceImplementation.getTheLastClientAdded();

        if (lastClient != null) {
            return ResponseEntity.status(HttpStatus.OK).body(lastClient);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    @ApiOperation(value = "Save New Client", notes = "Create a new client")
    public ResponseEntity<ClientDto> saveNewClient(@RequestBody @Valid ClientDto clientDto) {
        log.info(this.getClass() + " *** saveNewClient method");

        try {
            clientDto = clientServiceImplementation.saveNewClient(clientDto);
            return ResponseEntity.status(HttpStatus.OK).body(clientDto);
        } catch(Exception e) {
            log.error(" --- The error is: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{clientId}")
    @ApiOperation(value = "Update Client Income", notes = "Update the income of an existing client")
    public ResponseEntity<ClientEvaluatedDto> updateIncome(@PathVariable String clientId, @RequestBody @Valid ClientEvaluatedDto clientDto) {
        log.info(this.getClass() + " *** updateIncomeClient method");

        try {
            clientServiceImplementation.updateIncome(clientId, clientDto.getIncome());
        } catch(Exception e) {
            log.error(" --- The error is: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientDto);
    }
}

