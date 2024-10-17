CREATE DATABASE PetPlatform;
use PetPlatform;

-- Pet Table
CREATE TABLE Pet (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    breed VARCHAR(255) NOT NULL,
    adopted BOOLEAN DEFAULT false
);




-- Dog Table (Inherits from Pet)
CREATE TABLE Dog (
    pet_id INT PRIMARY KEY,
    dog_breed VARCHAR(255) NOT NULL,
    FOREIGN KEY (pet_id) REFERENCES Pet(id)
);

-- Cat Table (Inherits from Pet)
CREATE TABLE Cat (
    pet_id INT PRIMARY KEY,
    cat_color VARCHAR(255) NOT NULL,
    FOREIGN KEY (pet_id) REFERENCES Pet(id)
);

-- PetShelter Table
CREATE TABLE PetShelter (
    pet_id INT PRIMARY KEY,
    FOREIGN KEY (pet_id) REFERENCES Pet(id)
);

-- Donation Table (Abstract)
CREATE TABLE Donation (
    id INT PRIMARY KEY,
    donor_name VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL
);

-- CashDonation Table (Derived from Donation)
CREATE TABLE CashDonation (
    donation_id INT PRIMARY KEY,
    donation_date DATE NOT NULL,
    FOREIGN KEY (donation_id) REFERENCES Donation(id)
);

-- ItemDonation Table (Derived from Donation)
CREATE TABLE ItemDonation (
    donation_id INT PRIMARY KEY,
    item_type VARCHAR(255) NOT NULL,
    FOREIGN KEY (donation_id) REFERENCES Donation(id)
);
CREATE TABLE adoption_events (
    event_id INT AUTO_INCREMENT PRIMARY KEY,
    event_name VARCHAR(255) NOT NULL,
    event_date DATE NOT NULL
);
CREATE TABLE participants (
    participant_id INT AUTO_INCREMENT PRIMARY KEY,
    participant_name VARCHAR(255) NOT NULL,
    event_id INT,
    FOREIGN KEY (event_id) REFERENCES adoption_events(event_id)
);