package contest.rental;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import contest.reservation.Reservation;
import contest.reservation.ReservationManager;
import contest.umbrella.Umbrella;
import contest.umbrella.UmbrellaManager;

public class RentalManager {
	
	private static final int DUE = 500;//���� �߰��ΰ���500��

	private static List<Rental> rentalList = new ArrayList<Rental>();

	public RentalManager() {
		rentalList.add(new Rental("����", "�����а�", "2017003003", 20200710, 20200712, 2, true));
		rentalList.add(new Rental("�����", "��ȸ������", "2019004004", 20200710, 20200711, 8, true));

	}

	//������ ������ ����� �뿩�Ҷ�
	public void addReservedRental(Rental rental, ReservationManager reservationManager
			, UmbrellaManager umbrellaManager) throws Exception {

		String studentId = rental.getStudentId();
		if (studentId == null || studentId.isEmpty()) {
			throw new Exception("�й��� ��Ȯ�� �Է����ּ���.");
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
			throw new Exception(studentId + "�� �й��� �ش��ϴ� �����ڰ� �����ϴ�.");
		}
		
		int change = rental.changeCalculation();
		if (change > 0) {
			System.out.println("�Ž����� " + change + "�� �Դϴ�.");
		}
		
		rentalList.add(result);
		umbrellaManager.setRentalAvailability(result.getUmbrellaNumber(), false);
		reservationManager.removeReservation(result.getUmbrellaNumber());//������������ ����
		System.out.println("�뿩�� �Ϸ�Ǿ����ϴ�.");
	}

	//����뿩
	public void addRental(Rental rental, ReservationManager reservationManager, List<Umbrella> umbrellaList,
			UmbrellaManager umbrellaManager) throws Exception {

		// �뿩�Ⱓ 2���ʰ�
		if (rental.getReturnDate() - rental.getRentalDate() > 2) {
			throw new Exception("�뿩�Ⱓ�� �ִ� 2�� �Դϴ�. ex)200922-200924");
		}

		List<Umbrella> availableUmbrellaList = reservationManager.getAvailableUmbrellaList(rental, umbrellaList);
		if (availableUmbrellaList.size() <= 0) {
			throw new Exception("�ش� ��¥�� �뿩������ ����� �����ϴ�. �ٸ� ��¥�� �Է����ּ���.");
		}

		boolean result = false;
		for (Umbrella u : availableUmbrellaList) {
			if (u.getUmbrellaNumber() == rental.getUmbrellaNumber()) {
				result = true;
				break;
			}
		}

		if (!result) {
			throw new Exception("�뿩������ ����� �ƴմϴ�. <�������>�� �ִ� ����ȣ�� �Է����ּ���.");
		}
		
		int change = rental.changeCalculation();
		if (change > 0) {
			System.out.println("�Ž����� " + change + "�� �Դϴ�.");
		}
		
		rentalList.add(rental);
		umbrellaManager.setRentalAvailability(rental.getUmbrellaNumber(), false);
		System.out.println("�뿩�� �Ϸ�Ǿ����ϴ�.");
	}

	//�ݳ�
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
			throw new Exception(rental.getStudentId() + "�� �ش��ϴ� �й� ������ �����ϴ�.");
		}

		int returnDate = currentRental.getReturnDate();
		int actualReturnDate = currentRental.getActualReturnDate();
		int overduePeriod = actualReturnDate - returnDate;

		if (returnDate >= actualReturnDate) {
			System.out.println(currentRental.getName() + "��, ������ ȯ�ް� ��� �ݳ��� ���� �Ϸ�Ǿ����ϴ�.");
		} else {
			System.out.println(currentRental.getName() + "��, " + overduePeriod + " �� ��ü�Ǿ� �ΰ��� "
					+ duesCalculation(overduePeriod) + "���� �������ֽñ� �ٶ��ϴ�.");
			changeCalculation(5000, overduePeriod);
			System.out.println(currentRental.getName() + "��, �ݳ��� ���� �Ϸ�Ǿ����ϴ�.");
		}


		umbrellaManager.setRentalAvailability(currentRental.getUmbrellaNumber(), true);
		rentalList.remove(currentRental);
	}

	public void print() {
		System.out.println("<�뿩����>");
		System.out.println("No.\t�̸� \t�а�\t�й�\t\t�뿩�� ��¥\t\t�ݳ��� ��¥\t\t����ȣ\t������ ���� ����");
		for (int i = 0; i < rentalList.size(); i++) {
			System.out.print(i + 1 + ".\t");
			rentalList.get(i).printfRentalInfo();
		}
	}

	// �� �ΰ��� ���
	private static int duesCalculation(int overduePeriod) {
		int dues;
		dues = (DUE * overduePeriod);
		return dues;
	}

	// �ΰ��� �Ž����� ���
	private static void changeCalculation(int payment, int overduePeriod) throws Exception {
		int dues = duesCalculation(overduePeriod);
		int change = payment - dues;
		
		if (change < 0) {
			throw new Exception("���Ҿ��� �����մϴ�.");
		}
	}

}
