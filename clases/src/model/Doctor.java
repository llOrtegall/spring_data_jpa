package model;

public class Doctor extends User {

  private String speciality;
  private String company;

  public Doctor(String name, String lastname, String email, String address, String speciality, String company) {
    super(name, lastname, email, address);
    this.speciality = speciality;
    this.company = company;
  }

  public String getSpeciality() {
    return speciality;
  }

  public void setSpeciality(String speciality) {
    this.speciality = speciality;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  @Override
  public String toString() {
    return super.toString() + "\n" + "Especialidad: " + this.speciality + ", Compañia: " + this.company;
  }

  @Override
  public void showDataUser() {
    System.out.println("Hospital: " + this.company);
    System.out.println("Departamento: Cirugía");
  }

}
