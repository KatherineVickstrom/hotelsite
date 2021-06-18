package cst438hotel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
//@Table(name = "reservation")
public class Reservation
{
  @Id
  @GeneratedValue
  private Long id; // reservation id different than hotelid (many reservations for one hotel)
  private int hotelId;
  private String roomtype; // TODO make enum???
  private String startdate;
  private String enddate;
  
  @ManyToOne
  @JoinColumn(name = "user")
  private User user;

  public Reservation() {
    
  }
  
  public Reservation(Long id, int hotelId, String roomtype, String startdate, String enddate) {
    super();
    this.id = id;
    this.hotelId = hotelId;
    this.roomtype = roomtype;
    this.startdate = startdate;
    this.enddate = enddate;
  }
  
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public int getHotelId() {
    return hotelId;
  }

  public void setHotelId(int hotelId) {
    this.hotelId = hotelId;
  }

  public String getRoomtype() {
    return roomtype;
  }

  public void setRoomtype(String roomtype) {
    this.roomtype = roomtype;
  }
   public String getStartdate()
   {
      return startdate;
   }

   public void setStartdate(String startdate)
   {
      this.startdate = startdate;
   }

   public String getEnddate()
   {
      return enddate;
   }

   public void setEnddate(String enddate)
   {
      this.enddate = enddate;
   }
   

   public User getUser() {
     return user;
   }

   public void setUser(User user) {
     this.user = user;
   }

   @Override
   public String toString()
   {
      return "Reservation [id=" + id + ", hotelId=" + hotelId + ", roomtype=" + roomtype + ", startdate=" + startdate
            + ", enddate=" + enddate + "]";
   }
   
}
