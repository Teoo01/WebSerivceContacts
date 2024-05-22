# WebSerivceContacts
Semplice web service per la gestione dei contatti


# Indice  
- [Web Service JSON](#Web-service-JSON)
  - [ID](##ID)
  - [Email](##email)
- [Web Service CSV](#Web-service-CSV)
  - [ID](##ID)
  - [Email](##email)


# Web Service JSON
## ID
Linguaggio: Java  
Tipo: Maven  
Versiona Java: 17  

### Comandi
Creare un contatto:  
```curl -X POST http://localhost:8080/api/contacts -H "Content-Type: application/json" -d '{"firstName": "John", "lastName": "Doe", "email": "john.doe@example.com", "phoneNumber": "1234567890"}'```

Leggere tutti i contatti:  
```curl http://localhost:8080/api/contacts```

Aggiornare un contatto:  
```curl -X PUT http://localhost:8080/api/contacts/1 -H "Content-Type: application/json" -d '{"firstName": "John", "lastName": "Doe", "email": "john.doe@example.com", "phoneNumber": "0987654321"}'```

Cancellare un contatto:  
```curl -X DELETE http://localhost:8080/api/contacts/1```


## Email
Linguaggio: Java  
Tipo: Maven  
Versiona Java: 17  

### Comandi
Creare un contatto:  
```curl -X POST http://localhost:8080/api/contacts -H "Content-Type: application/json" -d '{"firstName": "John", "lastName": "Doe", "email": "john.doe@example.com", "phoneNumber": "1234567890"}'```

Leggere tutti i contatti:  
```curl http://localhost:8080/api/contacts```

Aggiornare un contatto:  
```curl -X PUT http://localhost:8080/api/contacts/john.doe@example.com -H "Content-Type: application/json" -d '{"firstName": "John", "lastName": "Doe", "email": "john.doe@example.com", "phoneNumber": "0987654321"}'```

Cancellare un contatto:  
```curl -X DELETE http://localhost:8080/api/contacts/john.doe@example.com```

# Web Service CSV
## ID
### Comandi
## Email
### Comandi
