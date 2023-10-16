package com.vilonov.PetClinic.controllers;

import com.vilonov.PetClinic.controllers.utils.BaseController;
import com.vilonov.PetClinic.controllers.utils.BaseResponse;
import com.vilonov.PetClinic.models.Client;
import com.vilonov.PetClinic.models.Pet;
import com.vilonov.PetClinic.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/api/pet")
public class PetController extends BaseController {
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping(value = "{name}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Pet> get(@PathVariable String name) {
        return ResponseEntity.ok(petService.getPetByName(name));
    }

    @GetMapping(value = "{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pet> get(@PathVariable Integer id) {
        return ResponseEntity.ok(petService.getPet(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus post(@RequestBody Pet pet) {
        return petService.addPet(pet)?HttpStatus.OK :HttpStatus.BAD_REQUEST;

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pet>> getAll() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("log.txt"));
            List<Pet> pt = petService.getAllPet();
            bufferedWriter.write(pt.toString());
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(petService.getAllPet());
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus updatePet(@RequestBody Pet pet) {
        return petService.updatePet(pet)?HttpStatus.OK :HttpStatus.BAD_REQUEST;
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus deletePet(@RequestBody Pet pet) {
        return petService.deletePet(pet)?HttpStatus.OK :HttpStatus.BAD_REQUEST;
    }
}

