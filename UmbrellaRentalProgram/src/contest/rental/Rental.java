package contest.rental;

import contest.reservation.Reservation;

public class Rental extends Reservation {

	private static final int PAYMENT = 3000;//보증금3000원

	private int actualReturnDate;
	private boolean isPaid;
	private int payment;

	public Rental() {

	}

	public Rental(Reservation reservation) {
		setName(reservation.getName());
		setDepartment(reservation.getDepartment());
		setStudentId(reservation.getStudentId());
		setRentalDate(reservation.getRentalDate());
		setReturnDate(reservation.getReturnDate());
		setUmbrellaNumber(reservation.getUmbrellaNumber());
		this.isPaid = false;
	}

	public Rental(String name, String department, String studentId, int rentalDate, int returnDate, int umbrellaNumber,
			boolean isPaid) {
		setName(name);
		setDepartment(department);
		setStudentId(studentId);
		setRentalDate(rentalDate);
		setReturnDate(returnDate);
		setUmbrellaNumber(umbrellaNumber);
		this.isPaid = isPaid;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public int getActualReturnDate() {
		return actualReturnDate;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public void setActualReturnDate(int actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}

	public void setDeposit(boolean payment) {
		isPaid = payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	//대여정보
	public void printfRentalInfo() {
		System.out.print(getName() + "\t" + getDepartment() + "\t" + getStudentId() + "\t" + getRentalDate() + "\t"
				+ getReturnDate() + "\t" + getUmbrellaNumber());
		if (isPaid) {
			System.out.println("\t완납");
		} else {
			System.out.println("\t미납");
		}
	}
	//대여자정보
	public void printfRental() {
		System.out.println("이름: "+getName()+"\t학과: "+ getDepartment()+"\t학번: "+getStudentId() +
				"\t대여할 날짜: "+getRentalDate()+"\t반납할 날짜: "+getReturnDate()+"\t우산번호: "+getUmbrellaNumber()+"\n");
	}
	
	// 보증금 거스름돈 계산
	public int changeCalculation() throws Exception {
		int change = payment - PAYMENT;
		if (change < 0) {
			throw new Exception("지불액이 부족합니다.");
		}

		return change;
	}

}
