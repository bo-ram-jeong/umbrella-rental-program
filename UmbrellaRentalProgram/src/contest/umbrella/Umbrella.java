package contest.umbrella;

public class Umbrella {
	private int umbrellaNumber;
	private String type;
	private String color;
	private boolean isRentalAvailability;

	public Umbrella(int umbrellaNumber) {
		this.umbrellaNumber = umbrellaNumber;
	}

	public Umbrella(int umbrellaNumber, String type, String color, boolean isRentalAvailability) {
		this.umbrellaNumber = umbrellaNumber;
		this.type = type;
		this.color = color;
		this.isRentalAvailability = isRentalAvailability;
	}


	public int getUmbrellaNumber() {
		return umbrellaNumber;
	}

	public String getType() {
		return type;
	}

	public String getColor() {
		return color;
	}

	public boolean isRentalAvailability() {
		return isRentalAvailability;
	}
	
	public void setUmbrellaNumber(int umbrellaNumber) {
		this.umbrellaNumber = umbrellaNumber;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public void setRentalAvailability(boolean isRentalAvailability) {
		this.isRentalAvailability = isRentalAvailability;
	}
	
	public void setUmbrella(Umbrella umbrella) {
		this.umbrellaNumber = umbrella.getUmbrellaNumber();
		this.type = umbrella.getType();
		this.color = umbrella.getColor();
		this.isRentalAvailability = umbrella.isRentalAvailability();
	}
	

	public void printfUmbrellaInfo() {
		System.out.println(umbrellaNumber + "\t" + type + "\t" + color);
	}


}
