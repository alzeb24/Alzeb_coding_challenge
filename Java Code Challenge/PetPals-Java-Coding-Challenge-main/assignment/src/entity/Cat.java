package entity;

public class Cat extends Pet {
	private String catColor;
	
	public Cat(String name, int age, String breed,String catColor) {
		super(name, age, breed);
		this.catColor=catColor;
	}

	public String getCatColor() {
		return catColor;
	}

	public void setCatColor(String catColor) {
		this.catColor = catColor;
	}
	public String toString() {
		return "Cat [catColor="+catColor+"]";
	}

}
