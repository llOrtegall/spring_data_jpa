package model;

public abstract class User {
  private int id;
  private String name;
  private String lastname;
  private String email;
  private String address;

  public User(String name, String lastname, String email, String address) {
    this.name = name;
    this.lastname = lastname;
    this.email = email;
    this.address = address;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    System.out.println(this.name);
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastname() {
    System.out.println(this.lastname);
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getEmail() {
    System.out.println(this.email);
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
