package contest.reservation;

public class Reservation {
	private String name;
	private String department;
	private String studentId;
	private int rentalDate;
	private int returnDate;
	private int umbrellaNumber;
	
	public Reservation() {
		
	}

	public Reservation(String name, String department, String studentId, int rentalDate, int returnDate, int umbrellaNumber) {
		this.name = name;
		this.department = department;
		this.studentId = studentId;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
		this.umbrellaNumber = umbrellaNumber;
	}
	
	public String getName() {
		return name;
	}

	public String getDepartment() {
		return department;
	}

	public String getStudentId() {
		return studentId;
	}

	public int getRentalDate() {
		return rentalDate;
	}

	public int getReturnDate() {
		return returnDate;
	}

	public int getUmbrellaNumber() {
		return umbrellaNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public void setRentalDate(int rentalDate) {
		this.rentalDate = rentalDate;
	}

	public void setReturnDate(int returnDate) {
		this.returnDate = returnDate;
	}

	public void setUmbrellaNumber(int umbrellaNumber) {
		this.umbrellaNumber = umbrellaNumber;
	}
	
	public void printfReservation() {
		System.out.println("이름: "+this.name+"\t학과: "+this.department+"\t학번: "+this.studentId+
				"\t대여할 날짜: "+this.rentalDate+"\t반납할 날짜: "+this.returnDate+"\t우산번호: "+this.umbrellaNumber+"\n");
	}
	
	public void printfReservationInfo() {
		System.out.println(this.name + "\t" + this.department + "\t" + this.studentId + "\t" + this.rentalDate + "\t" + this.returnDate + "\t"
				+ this.umbrellaNumber);
	}
}

