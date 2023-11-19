package com.bankingsystem.bank.controller;

import com.bankingsystem.bank.model.Document;
import com.bankingsystem.bank.service.DocumentServiceImplementation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentServiceImplementation documentServiceImplementation;

    @PostMapping("/{clientId}")
    public ResponseEntity<Document> saveDocument(@PathVariable String clientId) {
        log.info(this.getClass() + " *** saveNewClient method");

        Document document = documentServiceImplementation.generateDocument(clientId);
        return ResponseEntity.status(HttpStatus.OK).body(document);
    }
}

