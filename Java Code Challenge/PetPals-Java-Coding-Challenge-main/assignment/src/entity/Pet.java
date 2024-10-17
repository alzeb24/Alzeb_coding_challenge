package entity;

public class Pet {
	private int id;
	private String name;
	private int age;
	private String breed;
	private boolean adopted;
	
	public Pet() {
		
	}
	public Pet(String name, int age, String breed) {
		this.name = name;
		this.age = age;
		this.breed = breed;
		this.adopted = false;
	}
	public Pet(int id, String name, int age, String breed) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.breed = breed;
		this.adopted = false; 
	}
	public Pet(int id, String name, int age, String breed, boolean adopted) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.breed = breed;
		this.adopted = adopted;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public boolean isAdopted() {
		return adopted;
	}

	public void setAdopted(boolean adopted) {
		this.adopted = adopted;
	}

	public String toString() {
		return "Pet{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + ", breed='" + breed + '\''+ ", adopted=" + adopted + '}';
	}

}


