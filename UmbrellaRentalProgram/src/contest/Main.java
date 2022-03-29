package contest;

import java.util.List;

import contest.rental.Rental;
import contest.rental.RentalManager;
import contest.reservation.Reservation;
import contest.reservation.ReservationManager;
import contest.umbrella.Umbrella;
import contest.umbrella.UmbrellaManager;

public class Main {

	private static final UmbrellaManager umbrellaManager = new UmbrellaManager();   //��긮��Ʈ�� �����ϴ� Ŭ����
	private static final RentalManager rentalManager = new RentalManager();   //�뿩����Ʈ�� �����ϴ� Ŭ����
	private static final ReservationManager reservationManager = new ReservationManager();   //���ฮ��Ʈ�� �����ϴ� Ŭ����

	public static void printAll() {
		umbrellaManager.print();
		reservationManager.print();
		rentalManager.print();
	}
	

	public static void main(String[] args) {

		printAll();

		// case1. ����
		// case1-1. ���༺��(����)
		System.out.println("\n[����(����)]==================================================================================================");
		Reservation reservation1 = new Reservation("�����", "��ȸ������", "2020004004", 20200722, 20200723, 12);
		
		reservation1.printfReservation();//����������
		System.out.println("������ ���డ���� ��������Դϴ�.");
		
		final List<Umbrella> lendableUmbrellaList = umbrellaManager.getUmbrellaList(true);  
		reservationManager.printAvailableUmbrellaList(reservation1, lendableUmbrellaList);

		// �Է¹��� ����ȣ�� ���ฮ��Ʈ�� �߰����� ����
		try {
			reservationManager.addReservation(reservation1, lendableUmbrellaList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		reservationManager.print();
		
		
		// case1-2. �������(����ȣ ���� ��)
		System.out.println("\n[����(�ش� ����ȣ ������)]=======================================================================================");
		Reservation reservation2 = new Reservation("������", "��ȸ������", "2020004005", 20200724, 20200725, 15);
		
		reservation2.printfReservation();//����������
		System.out.println("������ ���డ���� ��������Դϴ�.");
		
		List<Umbrella> lendableUmbrellaList2 = umbrellaManager.getUmbrellaList(true);  
		reservationManager.printAvailableUmbrellaList(reservation2, lendableUmbrellaList2);

		// �Է¹��� ����ȣ�� ���ฮ��Ʈ�� �߰����� ����
		try {
			reservationManager.addReservation(reservation2, lendableUmbrellaList2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		reservationManager.print();
		
		
		// case1-3. �������(�뿩�Ⱓ �ʰ�)
		System.out.println("\n[����(�Ⱓ�ʰ�)]===============================================================================================");
		Reservation reservation3 = new Reservation("��ĥ��", "��ȸ������", "2020004006", 20200724, 20200727, 12);
		
		reservation3.printfReservation();//����������
		System.out.println("������ ���డ���� ��������Դϴ�.");
		
		List<Umbrella> lendableUmbrellaList3 = umbrellaManager.getUmbrellaList(true);  
		reservationManager.printAvailableUmbrellaList(reservation3, lendableUmbrellaList3);

		// �Է¹��� ����ȣ�� ���ฮ��Ʈ�� �߰����� ����
		try {
			reservationManager.addReservation(reservation3, lendableUmbrellaList3);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		reservationManager.print();
		
		
		// case1-4. �������(��¥ �ߺ�)
		System.out.println("\n[����(��¥�ߺ�)]================================================================================================");
		Reservation reservation4 = new Reservation("���ȹ�", "��ȸ������", "2020004007", 20200718, 20200719, 1);
		
		reservation4.printfReservation();
		System.out.println("������ ���డ���� ��������Դϴ�.");
		
		List<Umbrella> lendableUmbrellaList4 = umbrellaManager.getUmbrellaList(true);  
		reservationManager.printAvailableUmbrellaList(reservation4, lendableUmbrellaList4);

		// �Է¹��� ����ȣ�� ���ฮ��Ʈ�� �߰����� ����
		try {
			reservationManager.addReservation(reservation4, lendableUmbrellaList4);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		reservationManager.print();
		
		
		
		// case2. ������ ������ �����ڰ� �뿩�ϴ� ���
		// case2-1. ������������ �뿩(����)
		System.out.println("\n[������������ �뿩(����)]===========================================================================================");
		String studentId = "2020004004";
		System.out.println("�й�: "+studentId);
		Rental rental = new Rental();
		rental.setStudentId(studentId);
		System.out.println("���Ҿ�: 4000��");
		rental.setPayment(4000);

		try {
			rentalManager.addReservedRental(rental, reservationManager, umbrellaManager);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		printAll();

		// case2-2. ������������ �뿩 ����(�ش��й�������)
		System.out.println("\n[������������ �뿩 ����(�ش��й�������)]=================================================================================");
		String studentId2 = "20200040041111";
		System.out.println("�й�: "+studentId2);
		Rental rental2 = new Rental();
		rental2.setStudentId(studentId2);

		try {
			rentalManager.addReservedRental(rental2, reservationManager, umbrellaManager);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		// case2-3. ������������ �뿩 ����(�����ݺ����Ҷ�)
		System.out.println("\n[������������ �뿩 ����(�����ݺ����Ҷ�)]=================================================================================");
		String studentId3 = "2015002002";
		System.out.println("�й�: "+studentId3);
		Rental rental3 = new Rental();
		rental3.setStudentId(studentId3);
		System.out.println("���Ҿ�: 2000��");
		rental3.setPayment(2000);

		try {
			rentalManager.addReservedRental(rental3, reservationManager, umbrellaManager);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	
		
		// case3. ����뿩
		// case3-1. ����뿩(����)
		System.out.println("\n[����뿩(����)]=================================================================================================");
		Rental borrower1 = new Rental("�̱���", "�����а�", "2018003003", 20200710, 20200712, 3, true);

		borrower1.printfRental();//�뿩������
		System.out.println("������ �뿩������ ��������Դϴ�.");
		
		List<Umbrella> lendableUmbrellaList5 = umbrellaManager.getUmbrellaList(true);
		reservationManager.printAvailableUmbrellaList(borrower1, lendableUmbrellaList5);

		System.out.println("\n���Ҿ�: 5000��");
		borrower1.setPayment(5000);
		
		try {
			rentalManager.addRental(borrower1, reservationManager, lendableUmbrellaList5, umbrellaManager);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		printAll();

		// case3-2. ����뿩(�ش����ȣ ������)
		System.out.println("\n[����뿩(�ش����ȣ ������)]=================================================================================================");
		Rental borrower2 = new Rental("��ʹ�", "�����а�", "2018003004", 20200710, 20200712, 17, true);

		borrower2.printfRental();//�뿩������
		System.out.println("������ �뿩������ ��������Դϴ�.");
		
		List<Umbrella> lendableUmbrellaList6 = umbrellaManager.getUmbrellaList(true);
		reservationManager.printAvailableUmbrellaList(borrower2, lendableUmbrellaList6);
		
		try {
			rentalManager.addRental(borrower2, reservationManager, lendableUmbrellaList6, umbrellaManager);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		// case3-3. ����뿩(��¥�ߺ�)
		System.out.println("\n[����뿩(��¥�ߺ�)]=================================================================================================");
		Rental borrower3 = new Rental("�ڽ���", "�����а�", "2018003005", 20200710, 20200712, 3, true);

		borrower3.printfRental();//�뿩������
		System.out.println("������ �뿩������ ��������Դϴ�.");
		
		List<Umbrella> lendableUmbrellaList7 = umbrellaManager.getUmbrellaList(true);
		reservationManager.printAvailableUmbrellaList(borrower3, lendableUmbrellaList7);
		
		try {
			rentalManager.addRental(borrower3, reservationManager, lendableUmbrellaList7, umbrellaManager);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		// case3-4. ����뿩(�����ݺ���)
		System.out.println("\n[����뿩(�����ݺ���)]=================================================================================================");
		Rental borrower4 = new Rental("�ڽ���", "�����а�", "2018003008", 20200701, 20200702, 6, true);

		borrower4.printfRental();//�뿩������
		System.out.println("������ �뿩������ ��������Դϴ�.");
		
		List<Umbrella> lendableUmbrellaList8 = umbrellaManager.getUmbrellaList(true);
		reservationManager.printAvailableUmbrellaList(borrower4, lendableUmbrellaList8);

		System.out.println("\n���Ҿ�: 1000��");
		borrower4.setPayment(1000);
		
		try {
			rentalManager.addRental(borrower4, reservationManager, lendableUmbrellaList8, umbrellaManager);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		rentalManager.print();
		
		// case4. �ݳ�
		// case4-1. �ݳ�(����)
		System.out.println("\n[�ݳ�(����)]=====================================================================================================");
		System.out.println("** ���� ����� �Ҿ���Ȱų� �Ѽյ� �κ��� �ִٸ� ���� ���ǹ�ȣ�� �����ּ���^^ **\n[ 02-123-4567 ]\n");

		String returnStudentId = "2017003003";
		System.out.println("�й�: "+returnStudentId);
		System.out.println("���� �ݳ��ϴ� ��¥: 20200712");
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
		
		// case4-2. �ݳ�(��ü)
		System.out.println("\n[�ݳ�(��ü)]=====================================================================================================");
		System.out.println("** ���� ����� �Ҿ���Ȱų� �Ѽյ� �κ��� �ִٸ� ���� ���ǹ�ȣ�� �����ּ���^^ **\n[ 02-123-4567 ]\n");

		String returnStudentId2 = "2018003003";
		System.out.println("�й�: "+returnStudentId2);
		System.out.println("���� �ݳ��ϴ� ��¥: 20200714");
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
		
		// case4-3. �ݳ�(�й����� ���� ��)
		System.out.println("\n[�ݳ�����(�й����� ���� ��)]==========================================================================================");
		System.out.println("** ���� ����� �Ҿ���Ȱų� �Ѽյ� �κ��� �ִٸ� ���� ���ǹ�ȣ�� �����ּ���^^ **\n[ 02-123-4567 ]\n");

		String returnStudentId3 = "2015002002";
		System.out.println("�й�: "+returnStudentId3);
		System.out.println("���� �ݳ��ϴ� ��¥: 20200714");
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
