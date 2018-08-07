package com.example.hello.repositories;

import com.example.hello.model.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ContactRepository extends CrudRepository<Contact, Integer> {
    @Override
    Optional<Contact> findById(Integer id);

    @Override
    void delete(Contact contact);

    @Override
    Iterable<Contact> findAll();
}
