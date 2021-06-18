package cst438hotel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User
{

  @Id
  @GeneratedValue
  @Column(name="ID")
   private Long id;
   private String email;
   private String password;
   
   public User() {}
   
   public User(Long id, String email, String password)
   {
      super();
      this.id = id;
      this.email = email;
      this.password = password;
   }

   public Long getID()
   {
      return this.id;
   }

   public void setID(Long id)
   {
      this.id = id;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      User other = (User) obj;
      if (id != other.id)
         return false;
      if (email == null)
      {
         if (other.email != null)
            return false;
      } else if (!email.equals(other.email))
         return false;
      if (password == null)
      {
         if (other.password != null)
            return false;
      } else if (!password.equals(other.password))
         return false;
      return true;
   }

}
