public class Doctor {
  static int id = 0;
  String name;
  String speciality;

  Doctor() {
    System.out.println("Construyendo el Object 'Doctor' ");
  }

  Doctor(String name, String speciality) {
    this.name = name;
    this.speciality = speciality;
    id++;
    System.out.println("El nombre asignado es: " + name);
  }

  public void showInfo() {
    System.out.println("El nombre del doctor es: " + name + " y su especialidad es: " + speciality);
  }
}
