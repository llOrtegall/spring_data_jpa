package model;

public class Patient extends User {
  private double weight;
  private double height;
  private String birthday;
  private String blood;
  private String phoneNumber;

  public Patient(String name, String lastname, String email, String addreses, double weight, double height,
      String birthday, String blood, String phoneNumber) {
    super(name, email);
    this.weight = weight;
    this.height = height;
    this.birthday = birthday;
    this.blood = blood;
    this.phoneNumber = phoneNumber;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public double getHeight() {
    return height;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getBlood() {
    return blood;
  }

  public void setBlood(String blood) {
    this.blood = blood;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Override
  public void showDataUser() {
    System.out.println("Paciente: " + getName());
    System.out.println("Historial del paciente");
  }

}
