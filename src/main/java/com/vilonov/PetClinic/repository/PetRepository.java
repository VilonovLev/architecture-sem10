package com.vilonov.PetClinic.repository;

import com.vilonov.PetClinic.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {
}
