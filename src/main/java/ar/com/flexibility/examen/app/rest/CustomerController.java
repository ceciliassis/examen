package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;


    @PostMapping("")
    public ResponseEntity <Client> createClient(@RequestBody String email, String password, String name) {

        if (email.isEmpty () || password.isEmpty () || name.isEmpty ())
            return new ResponseEntity <> ( error, HttpStatus.NOT_ACCEPTABLE );

        Client newClient = clientRepository.save ( new client( name, email, password );
        return new ResponseEntity <> ( newClient, HttpStatus.OK );
    }

    @DeleteMapping("")
    public ResponseEntity <Client> deleteClient(@RequestBody Client client) {

        Client client = clientRepository.findByEmail(client.getEmail ());
        if (client.getName ()
//        if client1. Validar se o cliente existe
//        1.1. se existir -> deleta cliente
        repository.delete ( client.getId () );
        return new ResponseEntity <> ( client, HttpStatus.OK );
//        1.2. se não existir -> retorna um erro
    }

//    GET

//    PATCH (update parcial)

//    PUT (update total)

}
