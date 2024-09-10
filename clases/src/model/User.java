package model;

public abstract class User {
  private String name;
  private String lastName;
  private String email;
  private String address;

  public User() {
  }

  public User(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public User(String name, String lastName, String email, String address) {
    this.name = name;
    this.lastName = lastName;
    this.email = email;
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
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

  @Override
  public String toString() {
    return "User: " + this.name + ", Email:" + this.email + "\n" +
        "Address: " + this.address + ", Phone: ";
  }

  public abstract void showDataUser();
}
