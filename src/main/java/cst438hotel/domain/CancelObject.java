package cst438hotel.domain;

public class CancelObject {

  private String email;
  private String password;
  private Long id;
  
  public CancelObject() {
    
  }
  
  public CancelObject(String email, String password, Long id) {
    super();
    this.email = email;
    this.password = password;
    this.id = id;
  }
  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }
  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }
  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }
  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }
  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }
  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }
  
  
}
