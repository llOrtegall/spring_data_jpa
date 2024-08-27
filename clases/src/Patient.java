public class Patient {
  // estos datos ahora solo son accesibles desde la Clase (Encapsulamiento)
  // execto: id
  private String name;
  private double weight;
  private double height;
  private String email;
  private String address;
  private String birthday;
  private String blood;
  private String phoneNumber;
  int id;

  Patient(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getWeight() {
    return this.weight + " - Kg.";
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public String getHeight() {
    return this.height + " - m.";
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getBlood() {
    return this.blood;
  }

  public void setBlood(String blood) {
    this.blood = blood;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public void setPhoneNumber(String phone) {
    if (phone.length() < 8) {
      throw new IllegalArgumentException("El número de telefono debe ser máximo de 8 dígitos");
    }
    this.phoneNumber = phone;
  }
}
