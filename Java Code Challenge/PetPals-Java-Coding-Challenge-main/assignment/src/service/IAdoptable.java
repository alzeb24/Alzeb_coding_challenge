package service;
import java.sql.Date;
import java.sql.SQLException;

import exceptions.AdoptionException;
import exceptions.AgeException;
import exceptions.FileHandlingException;
import exceptions.InsufficientFundsException;
import exceptions.NullReferenceException;

public interface IAdoptable {
	
	// Adoption process
    void adopt(int id) throws AdoptionException;

    // Display pet listings from the "pets" table
    void displayPetListings() throws NullReferenceException;
    
    void displayParticipantsForEvent(int eventId);

    // Record cash donations into the "donations" table
    void recordCashDonation(int id,String donorName, double amount);

    // Retrieve information about upcoming adoption events from the "adoption_events" table
    void retrieveAdoptionEvents();
    
    void petInsertion(int id,String name,int age,String breed,int pet_type) throws SQLException;

    // Register for an adoption event by adding details to the "participants" table
    void registerForAdoptionEvent(String participantName, int eventId);

    // Handle invalid pet age when adding a new pet to a shelter
    void handleInvalidPetAge(String petName, int petAge, String petBreed) throws AgeException;

    // Handle null reference exceptions when displaying the list of available pets
    void handleNullReferenceExceptions() throws NullReferenceException;

    // Handle insufficient funds exceptions when making cash donations
    void handleInsufficientFunds(double donationAmount) throws InsufficientFundsException;

    // Handle file-related exceptions when reading data from a file
    void handleFileHandlingExceptions(String fileName) throws FileHandlingException;

    // Handle custom adoption-related errors using the AdoptionException
    void handleAdoptionExceptions() throws AdoptionException;
	

}
