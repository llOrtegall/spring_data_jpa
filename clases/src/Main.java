import model.Doctor;
import model.Patient;
import model.User;

public class Main {
	public static void main(String[] args) {

		User user = new Doctor("Ivan", "Ortega", "Example@gmail.com", "Cra $ N° 4 - 57 ", "Neuro Cirujano",
				"SOS PAC");

		user.showDataUser();

		User patient = new Patient("Sebastian", "Perez", "Example@gmail.com", "Cra $ N° 4 - 57 ", 100, 100, "01/01/2000",
				"A", "12345678");

		patient.showDataUser();

	}
}
