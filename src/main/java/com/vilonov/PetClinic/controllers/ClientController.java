package com.vilonov.PetClinic.controllers;

import com.vilonov.PetClinic.controllers.utils.BaseController;
import com.vilonov.PetClinic.controllers.utils.BaseResponse;
import com.vilonov.PetClinic.models.Client;
import com.vilonov.PetClinic.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/api/client")
public class ClientController extends BaseController {

    public final ClientService clientService;

    {
        clientService = new ClientService();
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public BaseResponse post(@RequestBody Client client) {
            return clientService.addClient(client)?
            new BaseResponse(SUCCESS_STATUS, CODE_SUCCESS):new BaseResponse(ERROR_STATUS, AUTH_FAILURE);
    }

    @GetMapping(value = "{id}",produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClient(@PathVariable Integer id) {
        return ResponseEntity.ok(clientService.get(id));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> getAll() {
        return (ResponseEntity<List<Client>>) clientService.getAllClients();
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public BaseResponse update(@RequestBody Client client) {
        return clientService.updateClient(client)?
                new BaseResponse(SUCCESS_STATUS, CODE_SUCCESS):new BaseResponse(ERROR_STATUS, AUTH_FAILURE);
    }

    @DeleteMapping(produces = APPLICATION_JSON_VALUE)
    public BaseResponse delete(@RequestBody Client client) {
        return clientService.deleteClient(client)?
                new BaseResponse(SUCCESS_STATUS, CODE_SUCCESS):new BaseResponse(ERROR_STATUS, AUTH_FAILURE);
    }

}
