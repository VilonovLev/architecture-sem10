package com.vilonov.PetClinic.services;
import com.vilonov.PetClinic.models.Pet;
import com.vilonov.PetClinic.repository.PetRepository;
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
public class PetService {

    @Autowired
    private PetRepository petRepository;


    @Transactional
    public List<Pet> getAllPet() {
        return petRepository.findAll();
    }
    @Transactional
    public Pet getPet(Integer petID) {
        Optional<Pet> optional = petRepository.findById(petID);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new RuntimeException("Incorrect pet ID");
    }

    @Transactional
    public boolean addPet(Pet pet) {
        if (!petRepository.findAll().contains(pet)) {
            petRepository.save(pet);
        }
        return petRepository.existsById(pet.getId());
    }

    @Transactional
    public boolean updatePet(Pet pet) {
        Optional<Pet> optional = petRepository.findById(pet.getId());
        if (optional.isPresent()) {
            return addPet(pet) && deletePet(optional.get());
        }
        return false;
    }

    @Transactional
    public boolean deletePet(Pet pet) {
        petRepository.delete(pet);
        return petRepository.existsById(pet.getId());
    }

    public Pet getPetByName(String name) {
        List<Pet> list= petRepository.findAll();
        Pet result = list.stream().filter(x ->x.getName().equals(name)).findAny().orElse(null);
        if (!(result == null)) {
            return result;
        }
        throw new RuntimeException("Incorrect pet ID");
    }
}
