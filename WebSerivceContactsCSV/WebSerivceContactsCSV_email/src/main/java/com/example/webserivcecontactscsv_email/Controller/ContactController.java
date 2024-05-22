package com.example.webserivcecontactscsv_email.Controller;

import com.example.webserivcecontactscsv_email.Model.Contact;
import com.example.webserivcecontactscsv_email.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    //ottenere tutti i contatti salvati dentro al CSV
    @GetMapping
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    //cerca il contatto a cui è collegata l'email selezionata
    @GetMapping("/{email}")
    public Contact getContactByEmail(@PathVariable String email) {
        return contactRepository.findByEmail(email).orElse(null);
    }

    //creazione di un nuovo contatto
    @PostMapping
    public void createContact(@RequestBody Contact contact) {
        contactRepository.save(contact);
    }

    //modifica di un contatto a cui è collagata l'email selezionata
    @PutMapping("/{email}")
    public void updateContact(@PathVariable String email, @RequestBody Contact updatedContact) {
        Optional<Contact> existingContact = contactRepository.findByEmail(email);
        if (existingContact.isPresent()) {
            updatedContact.setId(existingContact.get().getId());
            contactRepository.update(updatedContact);
        }
    }

    //elimina il contatto a cui è collegata l'email
    @DeleteMapping("/{email}")
    public void deleteContact(@PathVariable String email) {
        contactRepository.deleteByEmail(email);
    }
}