package com.vilonov.PetClinic.repository;

import com.vilonov.PetClinic.models.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {
}
