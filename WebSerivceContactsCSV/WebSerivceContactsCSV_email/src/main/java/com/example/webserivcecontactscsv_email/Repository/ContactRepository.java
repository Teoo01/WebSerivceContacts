package com.example.webserivcecontactscsv_email.Repository;

import com.example.webserivcecontactscsv_email.Model.Contact;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ContactRepository {

    private final String filePath = "src/main/resources/contacts.csv";
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

    //stampa tutti i contatti salvati nel CSV
    public List<Contact> findAll() {
        List<Contact> contacts = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader("id", "firstName", "lastName", "email", "phoneNumber").withSkipHeaderRecord(true))) {

            for (CSVRecord csvRecord : csvParser) {
                Contact contact = new Contact();
                contact.setId(Long.parseLong(csvRecord.get("id")));
                contact.setFirstName(csvRecord.get("firstName"));
                contact.setLastName(csvRecord.get("lastName"));
                contact.setEmail(csvRecord.get("email"));
                contact.setPhoneNumber(csvRecord.get("phoneNumber"));
                contacts.add(contact);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
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

    //scrive sul file CSV
    private void writeToFile(List<Contact> contacts) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("id", "firstName", "lastName", "email", "phoneNumber"))) {

            for (Contact contact : contacts) {
                csvPrinter.printRecord(contact.getId(), contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhoneNumber());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}