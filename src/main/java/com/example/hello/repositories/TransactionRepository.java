package com.example.hello.repositories;

import com.example.hello.model.Contact;
import org.springframework.data.repository.CrudRepository;
import com.example.hello.model.Transaction;

import java.util.Optional;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    @Override
    Optional<Transaction> findById(Integer id);

    @Override
    void delete(Transaction transaction);

    @Override
    Iterable<Transaction> findAll();
}

