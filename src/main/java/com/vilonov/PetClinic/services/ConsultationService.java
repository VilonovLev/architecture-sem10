package com.vilonov.PetClinic.services;

import com.vilonov.PetClinic.models.Consultation;
import com.vilonov.PetClinic.repository.ConsultationRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;


    @Transactional
    public List<Consultation> getAllConsultation() {
        return consultationRepository.findAll();
    }

    @Transactional
    public Consultation getConsultation(Integer consultationId) {
        Optional<Consultation> optional = consultationRepository.findById(consultationId);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new RuntimeException("Incorrect client ID");
    }

    @Transactional
    public boolean addConsultation(Consultation consultation) {
        if (!consultationRepository.findAll().contains(consultation)) {
            consultationRepository.save(consultation);
        }
        return consultationRepository.existsById(consultation.getId());
    }

    @Transactional
    public boolean updateConsultation(Consultation consultation) {
        Optional<Consultation> optional = consultationRepository.findById(consultation.getId());
        if (optional.isPresent()) {
            return addConsultation(consultation) && deleteConsultation(optional.get());
        }
        return false;
    }

    @Transactional
    public boolean deleteConsultation(Consultation consultation) {
        consultationRepository.delete(consultation);
        return consultationRepository.existsById(consultation.getId());
    }


}
