package model;

public class Nurse extends User {
  private String speciality;
  private String company;

  public Nurse(String name, String lastname, String email, String address, String speciality, String company) {
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

  @Override
  public void showDataUser() {
    System.out.println("Hospital: " + this.company);
    System.out.println("Departamento: Emergencias");
  }

}
