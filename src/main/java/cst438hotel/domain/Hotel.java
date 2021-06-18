package cst438hotel.domain;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hotels")
public class Hotel
{
   @Id
   private Long id;
   private String name;
   private String address;
   private String city;
   private int stars;
   private int price;
   final static int CAPACTIY = 2;

   public Hotel() {}
   
   public Hotel(Long iD, String name, String address, String city, int stars, int price)
   {
      super();
      id = iD;
      this.name = name;
      this.address = address;
      this.city = city;
      this.stars = stars;
      this.price = price;
   }

   public Long getID()
   {
      return id;
   }

   public void setID(Long iD)
   {
      id = iD;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getAddress()
   {
      return address;
   }

   public void setAddress(String address)
   {
      this.address = address;
   }

   public String getCity()
   {
      return city;
   }

   public void setCity(String city)
   {
      this.city = city;
   }

   public int getStars()
   {
      return stars;
   }

   public void setStars(int stars)
   {
      this.stars = stars;
   }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
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
      Hotel other = (Hotel) obj;
      if (id != other.id)
         return false;
      if (address == null)
      {
         if (other.address != null)
            return false;
      } else if (!address.equals(other.address))
         return false;
      if (city == null)
      {
         if (other.city != null)
            return false;
      } else if (!city.equals(other.city))
         return false;
      if (name == null)
      {
         if (other.name != null)
            return false;
      } else if (!name.equals(other.name))
         return false;
      if (stars != other.stars)
         return false;
      return true;
   }

  @Override
  public String toString() {
    return "Hotel [id=" + id + ", name=" + name + ", address=" + address + ", city=" + city + 
        ", stars=" + stars + ", price=" + price + "]";
  }
   
}