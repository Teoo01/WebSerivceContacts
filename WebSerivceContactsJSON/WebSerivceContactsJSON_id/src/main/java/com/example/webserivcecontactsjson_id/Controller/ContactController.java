package com.example.webserivcecontactsjson_id.Controller;

import com.example.webserivcecontactsjson_id.Model.Contact;
import com.example.webserivcecontactsjson_id.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    //ottenere tutti i contatti salvati dentro al JSON
    @GetMapping
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    //cerca il contatto a cui è collegato id selezionato
    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    //creazione di un nuovo contatto
    @PostMapping
    public void createContact(@RequestBody Contact contact) {
        contactRepository.save(contact);
    }

    //modifica di un contatto a cui è collagato id selezionato
    @PutMapping("/{id}")
    public void updateContact(@PathVariable Long id, @RequestBody Contact updatedContact) {
        Optional<Contact> existingContact = contactRepository.findById(id);
        if (existingContact.isPresent()) {
            updatedContact.setId(id);
            contactRepository.update(updatedContact);
        }
    }

    //elimina il contatto a cui è collegato id
    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactRepository.deleteById(id);
    }
}
