package com.vilonov.PetClinic.controllers;

import com.vilonov.PetClinic.controllers.utils.BaseController;
import com.vilonov.PetClinic.controllers.utils.BaseResponse;
import com.vilonov.PetClinic.models.Client;
import com.vilonov.PetClinic.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/api/client")
public class ClientController extends BaseController {


    public final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public HttpStatus post(@RequestBody Client client) {
            return clientService.addClient(client)? HttpStatus.OK :HttpStatus.BAD_REQUEST;
    }

    @GetMapping(value = "{id}",produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClient(@PathVariable Integer id) {
        return ResponseEntity.ok(clientService.get(id));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> getAll() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public HttpStatus update(@RequestBody Client client) {
        return clientService.updateClient(client)? HttpStatus.OK :HttpStatus.BAD_REQUEST;
    }

    @DeleteMapping(produces = APPLICATION_JSON_VALUE)
    public HttpStatus delete(@RequestBody Client client) {
        return clientService.deleteClient(client)? HttpStatus.OK :HttpStatus.BAD_REQUEST;
    }

}
