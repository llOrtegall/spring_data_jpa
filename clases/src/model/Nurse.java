package model;

public class Nurse extends User {
  private String speciality;

  public Nurse(String name, String lastname, String email, String address, String speciality) {
    super(name, lastname, email, address);
    this.speciality = speciality;
  }

  public String getSpeciality() {
    return speciality;
  }

  public void setSpeciality(String speciality) {
    this.speciality = speciality;
  }

}
