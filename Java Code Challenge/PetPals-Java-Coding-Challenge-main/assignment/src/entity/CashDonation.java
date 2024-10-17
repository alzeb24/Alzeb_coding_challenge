package entity;

import java.time.LocalDate;

public class CashDonation extends Donation{
  private LocalDate donationDate;
  
  public CashDonation(String donorName, double amount, LocalDate donationDate) {
      super(donorName, amount);
      this.donationDate = donationDate;
  }
  
  public void recordDonation() {
      System.out.println("Cash Donation recorded on " + donationDate + ": " + getAmount());
  }
}
