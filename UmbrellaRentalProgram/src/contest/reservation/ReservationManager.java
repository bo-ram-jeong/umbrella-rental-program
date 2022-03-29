package contest.reservation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import contest.umbrella.Umbrella;

public class ReservationManager {

	private static List<Reservation> reservationList = new ArrayList<Reservation>();

	public ReservationManager() {
		reservationList.add(new Reservation("���Ϲ�", "����â�۰�", "2018001001", 20200717, 20200719, 1));
		reservationList.add(new Reservation("���̹�", "����ȸ���", "2015002002", 20200720, 20200721, 7));
	}

	public void printAvailableUmbrellaList(Reservation reservation, List<Umbrella> umbrellaList) {
		System.out.println("<�������>");
		System.out.println("No.\t����ȣ \t�������\t����");
		int numberIncrease = 0;
		List<Umbrella> availableUmbrellaList = getAvailableUmbrellaList(reservation, umbrellaList);
		for (Umbrella u : availableUmbrellaList) {
			System.out.print(++numberIncrease + ".\t");
			u.printfUmbrellaInfo();
		}
	}

	private void validate(Reservation reservation, List<Umbrella> umbrellaList) throws Exception {

		// �뿩�Ⱓ 2���ʰ�
		if (reservation.getReturnDate() - reservation.getRentalDate() > 2) {
			throw new Exception("�뿩�Ⱓ�� �ִ� 2�� �Դϴ�. ex)200922-200924");
		}

		List<Umbrella> availableUmbrellaList = getAvailableUmbrellaList(reservation, umbrellaList);
		if (availableUmbrellaList.size() <= 0) {
			throw new Exception("�ش� ��¥�� ���డ���� ����� �����ϴ�. �ٸ� ��¥�� �Է����ּ���.");
		}

		boolean result = false;
		for (Umbrella u : availableUmbrellaList) {
			if (u.getUmbrellaNumber() == reservation.getUmbrellaNumber()) {
				result = true;
				break;
			}
		}

		if (!result) {
			throw new Exception("���డ���� ����� �ƴմϴ�. <�������>�� �ִ� ����ȣ�� �Է����ּ���.");
		}

	}

	public void addReservation(Reservation r, List<Umbrella> umbrellaList) throws Exception {
		validate(r, umbrellaList);
		reservationList.add(r);
		System.out.println("������ �Ϸ�Ǿ����ϴ�.");
	}

	public List<Reservation> getReservationList() {
		return reservationList;
	}

	public void print() {
		System.out.println("\n<��������>");
		System.out.println("No.\t�̸� \t�а�\t�й�\t\t�뿩�� ��¥\t\t�ݳ��� ��¥\t\t����ȣ");
		for (int i = 0; i < reservationList.size(); i++) {
			System.out.print(i + 1 + ".\t");
			reservationList.get(i).printfReservationInfo();
		}
	}

	//���� �� �ִ� ��� ����Ʈ
	public List<Umbrella> getAvailableUmbrellaList(Reservation reservation, List<Umbrella> availableUmbrellas) {

		for (int i = 0; i < availableUmbrellas.size(); i++) {
			for (int j = 0; j < reservationList.size(); j++) {
				// ���ฮ��Ʈ�� �ִ� ����� ������ && ��¥ ��ġ���� Ȯ��
				boolean isSame = availableUmbrellas.get(i).getUmbrellaNumber() == reservationList.get(j)
						.getUmbrellaNumber();
				if (isSame && !(reservation.getRentalDate() > reservationList.get(j).getReturnDate()
						|| reservation.getReturnDate() < reservationList.get(j).getRentalDate())) {
					availableUmbrellas.remove(i);//��¥�� ��ġ�� �������ִ� ��긮��Ʈ���� ����
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
