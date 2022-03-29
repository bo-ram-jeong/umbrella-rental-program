package contest;

import java.util.List;

import contest.rental.Rental;
import contest.rental.RentalManager;
import contest.reservation.Reservation;
import contest.reservation.ReservationManager;
import contest.umbrella.Umbrella;
import contest.umbrella.UmbrellaManager;

public class Main {

	private static final UmbrellaManager umbrellaManager = new UmbrellaManager();   //우산리스트를 관리하는 클래스
	private static final RentalManager rentalManager = new RentalManager();   //대여리스트를 관리하는 클래스
	private static final ReservationManager reservationManager = new ReservationManager();   //예약리스트를 관리하는 클래스

	public static void printAll() {
		umbrellaManager.print();
		reservationManager.print();
		rentalManager.print();
	}
	

	public static void main(String[] args) {

		printAll();

		// case1. 예약
		// case1-1. 예약성공(정상)
		System.out.println("\n[예약(성공)]==================================================================================================");
		Reservation reservation1 = new Reservation("김오번", "사회복지과", "2020004004", 20200722, 20200723, 12);
		
		reservation1.printfReservation();//예약자정보
		System.out.println("다음은 예약가능한 우산정보입니다.");
		
		final List<Umbrella> lendableUmbrellaList = umbrellaManager.getUmbrellaList(true);  
		reservationManager.printAvailableUmbrellaList(reservation1, lendableUmbrellaList);

		// 입력받은 우산번호로 예약리스트에 추가할지 말지
		try {
			reservationManager.addReservation(reservation1, lendableUmbrellaList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		reservationManager.print();
		
		
		// case1-2. 예약실패(우산번호 없을 때)
		System.out.println("\n[예약(해당 우산번호 없을때)]=======================================================================================");
		Reservation reservation2 = new Reservation("김육번", "사회복지과", "2020004005", 20200724, 20200725, 15);
		
		reservation2.printfReservation();//예약자정보
		System.out.println("다음은 예약가능한 우산정보입니다.");
		
		List<Umbrella> lendableUmbrellaList2 = umbrellaManager.getUmbrellaList(true);  
		reservationManager.printAvailableUmbrellaList(reservation2, lendableUmbrellaList2);

		// 입력받은 우산번호로 예약리스트에 추가할지 말지
		try {
			reservationManager.addReservation(reservation2, lendableUmbrellaList2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		reservationManager.print();
		
		
		// case1-3. 예약실패(대여기간 초과)
		System.out.println("\n[예약(기간초과)]===============================================================================================");
		Reservation reservation3 = new Reservation("김칠번", "사회복지과", "2020004006", 20200724, 20200727, 12);
		
		reservation3.printfReservation();//예약자정보
		System.out.println("다음은 예약가능한 우산정보입니다.");
		
		List<Umbrella> lendableUmbrellaList3 = umbrellaManager.getUmbrellaList(true);  
		reservationManager.printAvailableUmbrellaList(reservation3, lendableUmbrellaList3);

		// 입력받은 우산번호로 예약리스트에 추가할지 말지
		try {
			reservationManager.addReservation(reservation3, lendableUmbrellaList3);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		reservationManager.print();
		
		
		// case1-4. 예약실패(날짜 중복)
		System.out.println("\n[예약(날짜중복)]================================================================================================");
		Reservation reservation4 = new Reservation("김팔번", "사회복지과", "2020004007", 20200718, 20200719, 1);
		
		reservation4.printfReservation();
		System.out.println("다음은 예약가능한 우산정보입니다.");
		
		List<Umbrella> lendableUmbrellaList4 = umbrellaManager.getUmbrellaList(true);  
		reservationManager.printAvailableUmbrellaList(reservation4, lendableUmbrellaList4);

		// 입력받은 우산번호로 예약리스트에 추가할지 말지
		try {
			reservationManager.addReservation(reservation4, lendableUmbrellaList4);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		reservationManager.print();
		
		
		
		// case2. 사전에 예약한 예약자가 대여하는 경우
		// case2-1. 사전예약자의 대여(성공)
		System.out.println("\n[사전예약자의 대여(성공)]===========================================================================================");
		String studentId = "2020004004";
		System.out.println("학번: "+studentId);
		Rental rental = new Rental();
		rental.setStudentId(studentId);
		System.out.println("지불액: 4000원");
		rental.setPayment(4000);

		try {
			rentalManager.addReservedRental(rental, reservationManager, umbrellaManager);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		printAll();

		// case2-2. 사전예약자의 대여 실패(해당학번없을때)
		System.out.println("\n[사전예약자의 대여 실패(해당학번없을때)]=================================================================================");
		String studentId2 = "20200040041111";
		System.out.println("학번: "+studentId2);
		Rental rental2 = new Rental();
		rental2.setStudentId(studentId2);

		try {
			rentalManager.addReservedRental(rental2, reservationManager, umbrellaManager);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		// case2-3. 사전예약자의 대여 실패(보증금부족할때)
		System.out.println("\n[사전예약자의 대여 실패(보증금부족할때)]=================================================================================");
		String studentId3 = "2015002002";
		System.out.println("학번: "+studentId3);
		Rental rental3 = new Rental();
		rental3.setStudentId(studentId3);
		System.out.println("지불액: 2000원");
		rental3.setPayment(2000);

		try {
			rentalManager.addReservedRental(rental3, reservationManager, umbrellaManager);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	
		
		// case3. 현장대여
		// case3-1. 현장대여(성공)
		System.out.println("\n[현장대여(성공)]=================================================================================================");
		Rental borrower1 = new Rental("이구번", "토목공학과", "2018003003", 20200710, 20200712, 3, true);

		borrower1.printfRental();//대여자정보
		System.out.println("다음은 대여가능한 우산정보입니다.");
		
		List<Umbrella> lendableUmbrellaList5 = umbrellaManager.getUmbrellaList(true);
		reservationManager.printAvailableUmbrellaList(borrower1, lendableUmbrellaList5);

		System.out.println("\n지불액: 5000원");
		borrower1.setPayment(5000);
		
		try {
			rentalManager.addRental(borrower1, reservationManager, lendableUmbrellaList5, umbrellaManager);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		printAll();

		// case3-2. 현장대여(해당우산번호 없을때)
		System.out.println("\n[현장대여(해당우산번호 없을때)]=================================================================================================");
		Rental borrower2 = new Rental("김십번", "토목공학과", "2018003004", 20200710, 20200712, 17, true);

		borrower2.printfRental();//대여자정보
		System.out.println("다음은 대여가능한 우산정보입니다.");
		
		List<Umbrella> lendableUmbrellaList6 = umbrellaManager.getUmbrellaList(true);
		reservationManager.printAvailableUmbrellaList(borrower2, lendableUmbrellaList6);
		
		try {
			rentalManager.addRental(borrower2, reservationManager, lendableUmbrellaList6, umbrellaManager);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		// case3-3. 현장대여(날짜중복)
		System.out.println("\n[현장대여(날짜중복)]=================================================================================================");
		Rental borrower3 = new Rental("박십일", "토목공학과", "2018003005", 20200710, 20200712, 3, true);

		borrower3.printfRental();//대여자정보
		System.out.println("다음은 대여가능한 우산정보입니다.");
		
		List<Umbrella> lendableUmbrellaList7 = umbrellaManager.getUmbrellaList(true);
		reservationManager.printAvailableUmbrellaList(borrower3, lendableUmbrellaList7);
		
		try {
			rentalManager.addRental(borrower3, reservationManager, lendableUmbrellaList7, umbrellaManager);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		// case3-4. 현장대여(보증금부족)
		System.out.println("\n[현장대여(보증금부족)]=================================================================================================");
		Rental borrower4 = new Rental("박십이", "토목공학과", "2018003008", 20200701, 20200702, 6, true);

		borrower4.printfRental();//대여자정보
		System.out.println("다음은 대여가능한 우산정보입니다.");
		
		List<Umbrella> lendableUmbrellaList8 = umbrellaManager.getUmbrellaList(true);
		reservationManager.printAvailableUmbrellaList(borrower4, lendableUmbrellaList8);

		System.out.println("\n지불액: 1000원");
		borrower4.setPayment(1000);
		
		try {
			rentalManager.addRental(borrower4, reservationManager, lendableUmbrellaList8, umbrellaManager);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		rentalManager.print();
		
		// case4. 반납
		// case4-1. 반납(성공)
		System.out.println("\n[반납(성공)]=====================================================================================================");
		System.out.println("** 만약 우산을 잃어버렸거나 훼손된 부분이 있다면 다음 문의번호로 연락주세요^^ **\n[ 02-123-4567 ]\n");

		String returnStudentId = "2017003003";
		System.out.println("학번: "+returnStudentId);
		System.out.println("현재 반납하는 날짜: 20200712");
		int actualReturnDate = 20200712;

		Rental returnRental = new Rental();
		returnRental.setStudentId(returnStudentId);
		returnRental.setActualReturnDate(actualReturnDate);

		try {
			rentalManager.removeRental(returnRental, umbrellaManager);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		printAll();
		
		// case4-2. 반납(연체)
		System.out.println("\n[반납(연체)]=====================================================================================================");
		System.out.println("** 만약 우산을 잃어버렸거나 훼손된 부분이 있다면 다음 문의번호로 연락주세요^^ **\n[ 02-123-4567 ]\n");

		String returnStudentId2 = "2018003003";
		System.out.println("학번: "+returnStudentId2);
		System.out.println("현재 반납하는 날짜: 20200714");
		int actualReturnDate2 = 20200714;

		Rental returnRental2 = new Rental();
		returnRental2.setStudentId(returnStudentId2);
		returnRental2.setActualReturnDate(actualReturnDate2);

		try {
			rentalManager.removeRental(returnRental2, umbrellaManager);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		printAll();
		
		// case4-3. 반납(학번정보 없을 때)
		System.out.println("\n[반납실패(학번정보 없을 때)]==========================================================================================");
		System.out.println("** 만약 우산을 잃어버렸거나 훼손된 부분이 있다면 다음 문의번호로 연락주세요^^ **\n[ 02-123-4567 ]\n");

		String returnStudentId3 = "2015002002";
		System.out.println("학번: "+returnStudentId3);
		System.out.println("현재 반납하는 날짜: 20200714");
		int actualReturnDate3 = 20200714;

		Rental returnRental3 = new Rental();
		returnRental3.setStudentId(returnStudentId3);
		returnRental3.setActualReturnDate(actualReturnDate3);

		try {
			rentalManager.removeRental(returnRental3, umbrellaManager);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		printAll();
	}
}
