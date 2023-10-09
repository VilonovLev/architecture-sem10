package com.vilonov.PetClinic.repository;

import com.vilonov.PetClinic.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
