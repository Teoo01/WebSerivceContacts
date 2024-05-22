# WebSerivceContacts
Semplice web service per la gestione dei contatti.  
Le due versioni sono chiamate in base all'indice di ricerca che viene utilizzato.

# Indice  
- [Web Service JSON Id](#Web-service-JSON-Id)
- [Web Service JSON Email](#Web-service-JSON-Email)
- [Web Service CSV Id](#Web-service-CSV-Id)
- [Web Service CSV Email](#Web-service-CSV-Email)


# Web Service JSON Id
Linguaggio: Java  
Tipo: Maven  
Versiona Java: 17  

## Comandi
Creare un contatto:  
```
curl -X POST http://localhost:8080/api/contacts -H "Content-Type: application/json" -d '{"firstName": "John", "lastName": "Doe", "email": "john.doe@example.com", "phoneNumber": "1234567890"}'
```

Leggere tutti i contatti:  
```curl http://localhost:8080/api/contacts```

Aggiornare un contatto:  
```curl -X PUT http://localhost:8080/api/contacts/1 -H "Content-Type: application/json" -d '{"firstName": "John", "lastName": "Doe", "email": "john.doe@example.com", "phoneNumber": "0987654321"}'```

Cancellare un contatto:  
```curl -X DELETE http://localhost:8080/api/contacts/1```


# Web Service JSON Email
Linguaggio: Java  
Tipo: Maven  
Versiona Java: 17  

## Comandi
Creare un contatto:  
```curl -X POST http://localhost:8080/api/contacts -H "Content-Type: application/json" -d '{"firstName": "John", "lastName": "Doe", "email": "john.doe@example.com", "phoneNumber": "1234567890"}'```

Leggere tutti i contatti:  
```curl http://localhost:8080/api/contacts```

Aggiornare un contatto:  
```curl -X PUT http://localhost:8080/api/contacts/john.doe@example.com -H "Content-Type: application/json" -d '{"firstName": "John", "lastName": "Doe", "email": "john.doe@example.com", "phoneNumber": "0987654321"}'```

Cancellare un contatto:  
```curl -X DELETE http://localhost:8080/api/contacts/john.doe@example.com```

# Web Service CSV Id
## Comandi
# Web Service CSV Email
## Comandi
