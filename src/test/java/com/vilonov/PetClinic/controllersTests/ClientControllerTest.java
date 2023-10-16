package com.vilonov.PetClinic.controllersTests;

import com.vilonov.PetClinic.controllers.ClientController;
import com.vilonov.PetClinic.controllers.utils.BaseController;
import com.vilonov.PetClinic.controllers.utils.BaseResponse;
import com.vilonov.PetClinic.models.Client;
import com.vilonov.PetClinic.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {
    @Mock
    private ClientService clientService;
    @InjectMocks
    private ClientController controller;

    private List<Client> list = new ArrayList<>();

    private Client currentClient;

    @BeforeEach
    void setUp() {
        currentClient = new Client(1, "Lorem", "Ipsum", Timestamp.valueOf("1988-08-07 00:00:00"));
        list.add(currentClient);
    }

    @Test
    void getAllClientTest() {
        when(clientService.getAllClients()).thenReturn(list);
        var responseEntity = controller.getAll();
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test
    void getClientByIdTest() {
        when(clientService.get(anyInt())).thenReturn(currentClient);
        var responseEntity = controller.getClient(anyInt());
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void updateClientOKTest() {
        when(clientService.updateClient(currentClient)).thenReturn(true);
        assertEquals(HttpStatus.OK, controller.update(currentClient));
    }

    @Test
    void editClientBAD_REQUESTTest() {
        when(clientService.updateClient(currentClient)).thenReturn(false);
        assertEquals(HttpStatus.BAD_REQUEST, controller.update(currentClient));
    }

    @Test
    void addClientOKTest() {
        when(clientService.addClient(any(Client.class))).thenReturn(true);
        assertEquals(HttpStatus.OK, controller.post(currentClient));
    }

    @Test
    void addClientBAD_REQUESTTest() {
        when(clientService.addClient(any(Client.class))).thenReturn(false);
        assertEquals(HttpStatus.BAD_REQUEST, controller.post(currentClient));
    }
}
