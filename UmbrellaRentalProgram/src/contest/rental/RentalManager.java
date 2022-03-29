package contest.rental;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import contest.reservation.Reservation;
import contest.reservation.ReservationManager;
import contest.umbrella.Umbrella;
import contest.umbrella.UmbrellaManager;

public class RentalManager {
	
	private static final int DUE = 500;//일일 추가부과금500원

	private static List<Rental> rentalList = new ArrayList<Rental>();

	public RentalManager() {
		rentalList.add(new Rental("김삼번", "토목공학과", "2017003003", 20200710, 20200712, 2, true));
		rentalList.add(new Rental("정사번", "사회복지과", "2019004004", 20200710, 20200711, 8, true));

	}

	//사전에 예약한 사람이 대여할때
	public void addReservedRental(Rental rental, ReservationManager reservationManager
			, UmbrellaManager umbrellaManager) throws Exception {

		String studentId = rental.getStudentId();
		if (studentId == null || studentId.isEmpty()) {
			throw new Exception("학번을 정확히 입력해주세요.");
		}

		Rental result = null;
		List<Reservation> reservationList = reservationManager.getReservationList();
		Iterator<Reservation> it = reservationList.iterator();
		while (it.hasNext()) {
			Reservation r = it.next();
			if (studentId.equals(r.getStudentId())) {
				result = new Rental(r);
				result.setPaid(true);
				break;
			}
		}

		if (result == null) {
			throw new Exception(studentId + "에 학번에 해당하는 예약자가 없습니다.");
		}
		
		int change = rental.changeCalculation();
		if (change > 0) {
			System.out.println("거스름돈 " + change + "원 입니다.");
		}
		
		rentalList.add(result);
		umbrellaManager.setRentalAvailability(result.getUmbrellaNumber(), false);
		reservationManager.removeReservation(result.getUmbrellaNumber());//예약정보에서 삭제
		System.out.println("대여가 완료되었습니다.");
	}

	//현장대여
	public void addRental(Rental rental, ReservationManager reservationManager, List<Umbrella> umbrellaList,
			UmbrellaManager umbrellaManager) throws Exception {

		// 대여기간 2일초과
		if (rental.getReturnDate() - rental.getRentalDate() > 2) {
			throw new Exception("대여기간은 최대 2일 입니다. ex)200922-200924");
		}

		List<Umbrella> availableUmbrellaList = reservationManager.getAvailableUmbrellaList(rental, umbrellaList);
		if (availableUmbrellaList.size() <= 0) {
			throw new Exception("해당 날짜에 대여가능한 우산이 없습니다. 다른 날짜를 입력해주세요.");
		}

		boolean result = false;
		for (Umbrella u : availableUmbrellaList) {
			if (u.getUmbrellaNumber() == rental.getUmbrellaNumber()) {
				result = true;
				break;
			}
		}

		if (!result) {
			throw new Exception("대여가능한 우산이 아닙니다. <우산정보>에 있는 우산번호를 입력해주세요.");
		}
		
		int change = rental.changeCalculation();
		if (change > 0) {
			System.out.println("거스름돈 " + change + "원 입니다.");
		}
		
		rentalList.add(rental);
		umbrellaManager.setRentalAvailability(rental.getUmbrellaNumber(), false);
		System.out.println("대여가 완료되었습니다.");
	}

	//반납
	public void removeRental(Rental rental, UmbrellaManager umbrellaManager) throws Exception {
		Rental currentRental = null;
		for (Rental r : rentalList) {
			if (rental.getStudentId() == r.getStudentId()) {
				currentRental = r;
				currentRental.setActualReturnDate(rental.getActualReturnDate());
				break;
			}
		}

		if (currentRental == null) {
			throw new Exception(rental.getStudentId() + "에 해당하는 학번 정보가 없습니다.");
		}

		int returnDate = currentRental.getReturnDate();
		int actualReturnDate = currentRental.getActualReturnDate();
		int overduePeriod = actualReturnDate - returnDate;

		if (returnDate >= actualReturnDate) {
			System.out.println(currentRental.getName() + "님, 보증금 환급과 우산 반납이 정상 완료되었습니다.");
		} else {
			System.out.println(currentRental.getName() + "님, " + overduePeriod + " 일 연체되어 부과금 "
					+ duesCalculation(overduePeriod) + "원을 지불해주시기 바랍니다.");
			changeCalculation(5000, overduePeriod);
			System.out.println(currentRental.getName() + "님, 반납이 정상 완료되었습니다.");
		}


		umbrellaManager.setRentalAvailability(currentRental.getUmbrellaNumber(), true);
		rentalList.remove(currentRental);
	}

	public void print() {
		System.out.println("<대여정보>");
		System.out.println("No.\t이름 \t학과\t학번\t\t대여한 날짜\t\t반납할 날짜\t\t우산번호\t보증금 지불 여부");
		for (int i = 0; i < rentalList.size(); i++) {
			System.out.print(i + 1 + ".\t");
			rentalList.get(i).printfRentalInfo();
		}
	}

	// 낼 부과금 계산
	private static int duesCalculation(int overduePeriod) {
		int dues;
		dues = (DUE * overduePeriod);
		return dues;
	}

	// 부과금 거스름돈 계산
	private static void changeCalculation(int payment, int overduePeriod) throws Exception {
		int dues = duesCalculation(overduePeriod);
		int change = payment - dues;
		
		if (change < 0) {
			throw new Exception("지불액이 부족합니다.");
		}
	}

}
