package dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import exceptions.AdoptionException;
import exceptions.AgeException;
import exceptions.FileHandlingException;
import exceptions.InsufficientFundsException;
import exceptions.NullReferenceException;
import util.DBConnUtil;


public class IAdoptableImpl implements IAdoptable{ 
	static Connection connection;
	public static boolean isLoggedIn = false;
	
	public IAdoptableImpl() {
		this.connection = DBConnUtil.getConnection();
	}
	
	public void displayPetListings() throws NullReferenceException {
		try{
			String query = "SELECT * FROM Pet";
			try (Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(query)) {
				while (resultSet.next()) {
					int pid = resultSet.getInt("id");
					String name = resultSet.getString("name");
					if(name==null)
						handleNullReferenceExceptions();
					int age = resultSet.getInt("age");
					String breed = resultSet.getString("breed");
					String adopted = resultSet.getString("adopted");
					System.out.println("Pet Id: "+pid+" "+name + " - Age: " + age + ", Breed: " + breed+" Adopted: "+adopted);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void retrieveAdoptionEvents() {
		try{
			String query = "SELECT * FROM adoption_events";
			try (Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(query)) {
				while (resultSet.next()) {
					int eventId = resultSet.getInt("event_id");
					String eventName = resultSet.getString("event_name");
					Date eventDate = resultSet.getDate("event_date");
					System.out.println("Event ID: " + eventId + ", Name: " + eventName + ", Date: " + eventDate);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void registerForAdoptionEvent(String participantName, int eventId) {
		try{
			String query = "INSERT INTO participants (participant_name, event_id) VALUES (?, ?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, participantName);
				preparedStatement.setInt(2, eventId);
				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println(participantName + " registered for adoption event with ID: " + eventId);
				} else {
					System.out.println("Registration failed.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void petInsertion(int petid,String name,int age,String breed,int pet_type) throws SQLException 
    {
    	// inserting pet information in pet records
		
    	    String query="INSERT INTO Pet(id,name, age, breed) VALUES (?,?, ?, ?)";
    	    PreparedStatement pst = connection.prepareStatement(query);
    	    pst.setInt(1, petid);
			pst.setString(2, name);
			pst.setInt(3, age);
			pst.setString(4,breed);
			int rowsAffected = pst.executeUpdate();
			if(pet_type==1)
            {
            	PreparedStatement psDog = connection
                        .prepareStatement("INSERT INTO Dog(pet_id, dog_breed) VALUES (?, ?)");
            	psDog.setInt(1,  petid);
                psDog.setString(2, breed);
                int recordsAffectedDog = psDog.executeUpdate();
                if (recordsAffectedDog > 0) 
                {
                	System.out.println("Dog recorded Successfully.\n");
                }
                else
                {
                	 System.out.println("Failed to record dog. Please try again.");
                }
                
            }
			else if(pet_type==2)
			{
				System.out.println("Enter color of cat");
				Scanner scanner=new Scanner(System.in);
				String color=scanner.next();
				PreparedStatement psCat = connection
                        .prepareStatement("INSERT INTO Cat(pet_id, cat_color) VALUES (?, ?)");
            	psCat.setInt(1,  petid);
                psCat.setString(2, color);
                int recordsAffectedCat = psCat.executeUpdate();
                if (recordsAffectedCat > 0) 
                {
                	System.out.println("Cat recorded Successfully.\n");
                }
                else
                {
                	 System.out.println("Failed to record cat. Please try again.");
                }
			}
			if(rowsAffected>0)
			{
				System.out.println("Pet recorded Successfully.\n");
			}
			else
			{
				System.out.println("Failed to record pet. Please try again.");
			}
    }
	
	public void adopt(int id) throws AdoptionException {
	    try{
	        // Simulate the adoption process by updating the pet's adoption status in the database
	    	String query="Select adopted from Pet WHERE id=?";
	    	PreparedStatement pst = connection.prepareStatement(query);
	    	pst.setInt(1, id);
	    	ResultSet rs = pst.executeQuery();
	    	rs.next();
	    	boolean check_adoption=rs.getBoolean(1);
	    	if(!check_adoption)
	    	{
	        String updateQuery = "UPDATE Pet SET adopted = true WHERE id = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
	            preparedStatement.setInt(1, id);
	            int rowsAffected = preparedStatement.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Pet with ID " + id + " adopted successfully.");
	            } else {
	                System.out.println("Adoption failed. Pet with ID " + id + " not found.");
	            }
	           }
	    	}
	    	else
	    	{
	    		handleAdoptionExceptions();
	    	}
	    }catch (SQLException e) {
	        e.printStackTrace();
	    
	    }
	}
	
	
	public void displayParticipantsForEvent(int eventId) {
	    try{
	        String query = "SELECT * FROM participants WHERE event_id = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setInt(1, eventId);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                System.out.println("Participants for Event ID " + eventId + ":");
	                while (resultSet.next()) {
	                    int participantId = resultSet.getInt("participant_id");
	                    String participantName = resultSet.getString("participant_name");
	                    System.out.println("Participant ID: " + participantId + ", Name: " + participantName);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void recordCashDonation(int id,String donorName, double amount) {
	    try{
	        // Simulate recording a cash donation by inserting a new record in the donations table
	        String query = "INSERT INTO Donation(id,donor_name, amount) VALUES (?,?,?)";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        	preparedStatement.setInt(1, id);
	            preparedStatement.setString(2, donorName);
	            preparedStatement.setDouble(3, amount);
	        
	            int rowsAffected = preparedStatement.executeUpdate();
	            PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO CashDonation(donation_id,donation_date) VALUES (?,?)");
	            preparedStatement1.setInt(1,id);
	            LocalDate currentDate = LocalDate.now();
	            java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);
	            preparedStatement1.setDate(2,sqlDate);
	            int rowsAffected1 = preparedStatement1.executeUpdate();
	            if (rowsAffected > 0 && rowsAffected1>0) {
	            	
	                System.out.println("Recording cash donation from " + donorName + " is amount: $" + amount+" on date "+sqlDate);
	            } else {
	                System.out.println("Recording donation failed.");
	            }
	            
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void handleInvalidPetAge(String petName, int petAge, String petBreed) throws AgeException {
		if (petAge <= 0) {
			throw new AgeException("Invalid pet age. Age should be a positive integer.");
		}
		System.out.println("Pet " + petName + " added to the shelter. Age: " + petAge + ", Breed: " + petBreed);
	}
	
	public void handleNullReferenceExceptions() throws NullReferenceException {
		// Simulating null reference exception
		throw new NullReferenceException("Null reference exception encountered when displaying pet listings.");
	}
	
	public void handleInsufficientFunds(double donationAmount) throws InsufficientFundsException {
		if (donationAmount < 10.0) {
			throw new InsufficientFundsException("Insufficient funds. Minimum donation amount is $10.");
		}
		System.out.println("Donation processed successfully. Amount: $" + donationAmount);
	}
	
	public void handleFileHandlingExceptions(String fileName) throws FileHandlingException {
        try {
            // Simulating file handling exception
            throw new FileNotFoundException("File not found: " + fileName);
        } catch (FileNotFoundException e) {
            throw new FileHandlingException(e.getMessage());
        }
    }
	
	public void handleAdoptionExceptions() throws AdoptionException {
		// Simulating adoption-related exception
		throw new AdoptionException("Adoption error: Pet is not available or has missing information.");
	}
}
