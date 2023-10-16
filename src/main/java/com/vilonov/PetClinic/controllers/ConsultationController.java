package com.vilonov.PetClinic.controllers;

import com.vilonov.PetClinic.controllers.utils.BaseController;
import com.vilonov.PetClinic.controllers.utils.BaseResponse;
import com.vilonov.PetClinic.models.Consultation;
import com.vilonov.PetClinic.services.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultation")
public class ConsultationController extends BaseController {
    private final ConsultationService consultationService;

    @Autowired
    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse post(@RequestBody Consultation consultation) {
        return consultationService.addConsultation(consultation)?
                new BaseResponse(SUCCESS_STATUS, CODE_SUCCESS):new BaseResponse(ERROR_STATUS, AUTH_FAILURE);
    }

    @GetMapping(value = "{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consultation> getClient(@PathVariable Integer id) {
        return ResponseEntity.ok(consultationService.getConsultation(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Consultation>> getAll() {
        return (ResponseEntity<List<Consultation>>) consultationService.getAllConsultation();
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse update(@RequestBody Consultation consultation) {
        return consultationService.updateConsultation(consultation)?
                new BaseResponse(SUCCESS_STATUS, CODE_SUCCESS):new BaseResponse(ERROR_STATUS, AUTH_FAILURE);
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse delete(@RequestBody Consultation consultation) {
        return consultationService.deleteConsultation(consultation)?
                new BaseResponse(SUCCESS_STATUS, CODE_SUCCESS):new BaseResponse(ERROR_STATUS, AUTH_FAILURE);
    }
}

