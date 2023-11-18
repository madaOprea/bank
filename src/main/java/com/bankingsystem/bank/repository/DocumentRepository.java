package com.bankingsystem.bank.repository;

import com.bankingsystem.bank.model.Client;
import com.bankingsystem.bank.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, String> {

    Document findByClient(Client clientId);
}
