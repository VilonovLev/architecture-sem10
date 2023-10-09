package com.vilonov.PetClinic.controllers;

import com.vilonov.PetClinic.controllers.utils.BaseController;
import com.vilonov.PetClinic.controllers.utils.BaseResponse;
import com.vilonov.PetClinic.models.Client;
import com.vilonov.PetClinic.models.Pet;
import com.vilonov.PetClinic.services.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/api/pet")
public class PetController extends BaseController {
    private final PetService petService;

    {
        petService = new PetService();
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
    public BaseResponse post(@RequestBody Pet pet) {
        return petService.addPet(pet)?
                new BaseResponse(SUCCESS_STATUS, CODE_SUCCESS):new BaseResponse(ERROR_STATUS, AUTH_FAILURE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pet>> getAll() {
        return (ResponseEntity<List<Pet>>) petService.getAllPet();
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse updatePet(@RequestBody Pet pet) {
        return petService.updatePet(pet)?
                new BaseResponse(SUCCESS_STATUS, CODE_SUCCESS):new BaseResponse(ERROR_STATUS, AUTH_FAILURE);
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse deletePet(@RequestBody Pet pet) {
        return petService.deletePet(pet)?
                new BaseResponse(SUCCESS_STATUS, CODE_SUCCESS):new BaseResponse(ERROR_STATUS, AUTH_FAILURE);
    }
}

