package contest.umbrella;

import java.util.ArrayList;
import java.util.List;

public class UmbrellaManager {
	private String umbrellaType1 = "장 우산";
	private String umbrellaType2 = "3단 우산";
	private String umbrellaColor1 = "빨강";
	private String umbrellaColor2 = "노랑";
	private String umbrellaColor3 = "파랑";

	private List<Umbrella> umbrellaList = new ArrayList<Umbrella>();

	public UmbrellaManager() {
		umbrellaList.add(new Umbrella(1, umbrellaType1, umbrellaColor1, true));
		umbrellaList.add(new Umbrella(2, umbrellaType1, umbrellaColor1, false));
		umbrellaList.add(new Umbrella(3, umbrellaType2, umbrellaColor1, true));
		umbrellaList.add(new Umbrella(4, umbrellaType2, umbrellaColor1, true));
		umbrellaList.add(new Umbrella(5, umbrellaType1, umbrellaColor2, true));
		umbrellaList.add(new Umbrella(6, umbrellaType1, umbrellaColor2, true));
		umbrellaList.add(new Umbrella(7, umbrellaType2, umbrellaColor2, true));
		umbrellaList.add(new Umbrella(8, umbrellaType2, umbrellaColor2, false));
		umbrellaList.add(new Umbrella(9, umbrellaType1, umbrellaColor3, true));
		umbrellaList.add(new Umbrella(10, umbrellaType1, umbrellaColor3, true));
		umbrellaList.add(new Umbrella(11, umbrellaType2, umbrellaColor3, true));
		umbrellaList.add(new Umbrella(12, umbrellaType2, umbrellaColor3, true));
	}

	public List<Umbrella> getUmbrellaList() {
		return this.umbrellaList;
	}

	public List<Umbrella> getUmbrellaList(boolean isRentalAvailability) {
		List<Umbrella> list = new ArrayList<>();
		for (Umbrella umbrella : umbrellaList) {
			if (umbrella.isRentalAvailability() == isRentalAvailability) {
				list.add(umbrella);
			}
		}
		return list;
	}

	public void rentalUmbrella(int umbrellaNumber) {
		for (Umbrella umbrella : umbrellaList) {
			if (umbrellaNumber == umbrella.getUmbrellaNumber()) {
				umbrella.setRentalAvailability(false);
			}
		}
	}

	public void returnUmbrella(int umbrellaNumber) {
		for (Umbrella umbrella : umbrellaList) {
			if (umbrellaNumber == umbrella.getUmbrellaNumber()) {
				umbrella.setRentalAvailability(true);
			}
		}
	}

	public void setRentalAvailability(int umbrellaNumber, boolean availability) {
		for (Umbrella u : umbrellaList) {
			if (umbrellaNumber == u.getUmbrellaNumber()) {
				u.setRentalAvailability(availability);
				break;
			}
		}
	}

	public void print() {
		System.out.println("<우산정보>");
		System.out.println("No.\t우산번호 \t우산종류\t우산색");
		int numberIncrease = 0;
		for (Umbrella umbrella : umbrellaList) {
			if (umbrella.isRentalAvailability()) {
				System.out.print(++numberIncrease + ".\t");
				umbrella.printfUmbrellaInfo();
			}
		}
	}
}
