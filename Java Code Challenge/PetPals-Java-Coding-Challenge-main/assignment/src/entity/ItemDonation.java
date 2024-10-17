package entity;

public class ItemDonation extends Donation {
  private String itemType;
  
  public ItemDonation(String donorName, double amount, String itemType) {
      super(donorName, amount);
      this.itemType = itemType;
  }
  public ItemDonation() {
  	
  }
 
  public String getItemType() {
	return itemType;
}
public void setItemType(String itemType) {
	this.itemType = itemType;
}
public void recordDonation() {
      System.out.println("Item Donation --- Type: " + itemType + ", Amount: " + getAmount());
  }
}
