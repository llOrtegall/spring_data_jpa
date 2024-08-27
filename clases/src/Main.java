public class Main {
	public static void main(String[] args) {
		System.out.println("Hello, World!");

		Doctor myDoctor = new Doctor("Daniel", "Pediatra");
		myDoctor.showInfo();

		Patient patient_1 = new Patient("Ivan", "IvanOrtega_97@hotmail.com");

		patient_1.setPhoneNumber("12412515125");
		System.out.println(patient_1.getPhoneNumber());
	}
}
