package com.bankingsystem.bank.repository;

import com.bankingsystem.bank.model.Client;
import com.bankingsystem.bank.model.dto.ClientDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

    @Query(value = "select * from clients order by created_date desc LIMIT 1", nativeQuery = true)
    ClientDto getTheLastClientAdded();
}