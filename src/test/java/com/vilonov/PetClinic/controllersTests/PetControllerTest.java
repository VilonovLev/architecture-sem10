package com.vilonov.PetClinic.controllersTests;


import com.vilonov.PetClinic.controllers.ClientController;
import com.vilonov.PetClinic.controllers.PetController;
import com.vilonov.PetClinic.models.Client;
import com.vilonov.PetClinic.models.Pet;
import com.vilonov.PetClinic.services.ClientService;
import com.vilonov.PetClinic.services.PetService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PetControllerTest {

    @Mock
    private PetService petService;
    @InjectMocks
    private PetController petController;

    private List<Pet> list = new ArrayList<>();

    private Pet currentPet;

    @BeforeEach
    void setUp() {
        currentPet = new Pet(1, "Lorem", Timestamp.valueOf("1988-08-07 00:00:00"));
        list.add(currentPet);
    }

    @Test
    void getClientByIdTest() {
        when(petService.getPet(anyInt())).thenReturn(currentPet);
        var responseEntity = petController.get(anyInt());
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void updateClientOKTest() {
        when(petService.updatePet(currentPet)).thenReturn(true);
        assertEquals(HttpStatus.OK, petController.updatePet(currentPet));
    }

    @Test
    void editClientBAD_REQUESTTest() {
        when(petService.updatePet(currentPet)).thenReturn(false);
        assertEquals(HttpStatus.BAD_REQUEST, petController.updatePet(currentPet));
    }

    @Test
    void addClientOKTest() {
        when(petService.addPet(currentPet)).thenReturn(true);
        assertEquals(HttpStatus.OK, petController.post(currentPet));
    }

    @Test
    void addClientBAD_REQUESTTest() {
        when(petService.addPet(currentPet)).thenReturn(false);
        assertEquals(HttpStatus.BAD_REQUEST, petController.post(currentPet));
    }
}
