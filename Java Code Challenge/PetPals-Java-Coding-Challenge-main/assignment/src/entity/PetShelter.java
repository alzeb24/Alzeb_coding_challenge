package entity;

import java.util.ArrayList;
import java.util.List;

public class PetShelter {
	private List<Pet> availablePets;
	
	public PetShelter() {
        availablePets = new ArrayList<>();
    }
	
	public void addPet(Pet pet) {
        availablePets.add(pet);
    }

    public void removePet(Pet pet) {
        availablePets.remove(pet);
    }

    public void listAvailablePets() {
        System.out.println("Available Pets:");
        for (Pet pet : availablePets) {
            System.out.println(pet);
        }
    }
}
