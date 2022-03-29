package contest.reservation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import contest.umbrella.Umbrella;

public class ReservationManager {

	private static List<Reservation> reservationList = new ArrayList<Reservation>();

	public ReservationManager() {
		reservationList.add(new Reservation("김일번", "문예창작과", "2018001001", 20200717, 20200719, 1));
		reservationList.add(new Reservation("박이번", "세무회계과", "2015002002", 20200720, 20200721, 7));
	}

	public void printAvailableUmbrellaList(Reservation reservation, List<Umbrella> umbrellaList) {
		System.out.println("<우산정보>");
		System.out.println("No.\t우산번호 \t우산종류\t우산색");
		int numberIncrease = 0;
		List<Umbrella> availableUmbrellaList = getAvailableUmbrellaList(reservation, umbrellaList);
		for (Umbrella u : availableUmbrellaList) {
			System.out.print(++numberIncrease + ".\t");
			u.printfUmbrellaInfo();
		}
	}

	private void validate(Reservation reservation, List<Umbrella> umbrellaList) throws Exception {

		// 대여기간 2일초과
		if (reservation.getReturnDate() - reservation.getRentalDate() > 2) {
			throw new Exception("대여기간은 최대 2일 입니다. ex)200922-200924");
		}

		List<Umbrella> availableUmbrellaList = getAvailableUmbrellaList(reservation, umbrellaList);
		if (availableUmbrellaList.size() <= 0) {
			throw new Exception("해당 날짜에 예약가능한 우산이 없습니다. 다른 날짜를 입력해주세요.");
		}

		boolean result = false;
		for (Umbrella u : availableUmbrellaList) {
			if (u.getUmbrellaNumber() == reservation.getUmbrellaNumber()) {
				result = true;
				break;
			}
		}

		if (!result) {
			throw new Exception("예약가능한 우산이 아닙니다. <우산정보>에 있는 우산번호를 입력해주세요.");
		}

	}

	public void addReservation(Reservation r, List<Umbrella> umbrellaList) throws Exception {
		validate(r, umbrellaList);
		reservationList.add(r);
		System.out.println("예약이 완료되었습니다.");
	}

	public List<Reservation> getReservationList() {
		return reservationList;
	}

	public void print() {
		System.out.println("\n<예약정보>");
		System.out.println("No.\t이름 \t학과\t학번\t\t대여할 날짜\t\t반납할 날짜\t\t우산번호");
		for (int i = 0; i < reservationList.size(); i++) {
			System.out.print(i + 1 + ".\t");
			reservationList.get(i).printfReservationInfo();
		}
	}

	//빌릴 수 있는 우산 리스트
	public List<Umbrella> getAvailableUmbrellaList(Reservation reservation, List<Umbrella> availableUmbrellas) {

		for (int i = 0; i < availableUmbrellas.size(); i++) {
			for (int j = 0; j < reservationList.size(); j++) {
				// 예약리스트에 있는 우산을 빌릴때 && 날짜 겹치는지 확인
				boolean isSame = availableUmbrellas.get(i).getUmbrellaNumber() == reservationList.get(j)
						.getUmbrellaNumber();
				if (isSame && !(reservation.getRentalDate() > reservationList.get(j).getReturnDate()
						|| reservation.getReturnDate() < reservationList.get(j).getRentalDate())) {
					availableUmbrellas.remove(i);//날짜가 겹치면 빌릴수있는 우산리스트에서 삭제
				}
			}
		}

		return availableUmbrellas;
	}

	public void removeReservation(int umbrellaNumber) {
		 Iterator<Reservation> it = reservationList.iterator();
		 
		 while (it.hasNext()) {
			 Reservation r = it.next();
			 if (r.getUmbrellaNumber() == umbrellaNumber) {
					reservationList.remove(r);
					break;
			 }
		 }
	}
}
