package com.example.webserivcecontactsjson_email.Repository;

import com.example.webserivcecontactsjson_email.Model.Contact;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ContactRepository {

    private final String filePath = "src/main/resources/contacts.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AtomicLong counter = new AtomicLong();

    //id incrementale con controllo
    public ContactRepository() {
        List<Contact> contacts = findAll();
        if (!contacts.isEmpty()) {
            long maxId = contacts.stream().mapToLong(Contact::getId).max().orElse(0L);
            counter.set(maxId + 1);
        } else {
            counter.set(1);
        }
    }

    //stampa tutti i contatti salvati nel json
    public List<Contact> findAll() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<Contact>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    //ricerca del contatto per email
    public Optional<Contact> findByEmail(String email) {
        return findAll().stream().filter(contact -> contact.getEmail().equals(email)).findFirst();
    }

    //salva il contatto
    public void save(Contact contact) {
        List<Contact> contacts = findAll();
        contact.setId(counter.getAndIncrement());
        contacts.add(contact);
        writeToFile(contacts);
    }

    //aggiorna il contatto
    public void update(Contact updatedContact) {
        List<Contact> contacts = findAll();
        contacts.removeIf(contact -> contact.getEmail().equals(updatedContact.getEmail()));
        contacts.add(updatedContact);
        writeToFile(contacts);
    }

    //cancella il contatto per email
    public void deleteByEmail(String email) {
        List<Contact> contacts = findAll();
        contacts.removeIf(contact -> contact.getEmail().equals(email));
        writeToFile(contacts);
    }

    //scrive sul file json
    private void writeToFile(List<Contact> contacts) {
        try {
            objectMapper.writeValue(new File(filePath), contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}