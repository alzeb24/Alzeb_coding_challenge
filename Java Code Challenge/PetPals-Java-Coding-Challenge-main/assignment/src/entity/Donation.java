package entity;

public abstract class Donation {
   private String donorName;
   private double amount;
   
   public Donation() {
   	
   }
   
   public double getAmount() {
	return amount;
}

public void setAmount(double amount) {
	this.amount = amount;
}

public Donation(String donorName, double amount) {
       this.donorName = donorName;
       this.amount = amount;
   }
   
   public abstract void recordDonation();

}
