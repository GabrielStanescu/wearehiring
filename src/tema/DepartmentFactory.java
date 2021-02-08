package tema;

public class DepartmentFactory {
	public static Department factory(String type) {
		if (type.equals("IT"))
			return new IT();
		if (type.equals("Management"))
			return new Management();
		if (type.equals("Marketing"))
			return new Marketing();
		if (type.equals("Finance"))
			return new Finance();
		return null;
	}
}
