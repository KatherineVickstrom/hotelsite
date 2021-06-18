package cst438hotel.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cst438hotel.domain.CancelObject;
import cst438hotel.domain.Hotel;
import cst438hotel.domain.Reservation;
import cst438hotel.domain.ReservationRepository;
import cst438hotel.domain.User;
import cst438hotel.domain.UserRepository;
import cst438hotel.service.HotelService;

@Controller
public class HotelController
{

   @Autowired
//   HotelRepository hotelRepository;
   HotelService hotelService;
   @Autowired
   ReservationRepository reservationRepository;
   @Autowired
   UserRepository userRepository;

  // For testing purposes only
  @GetMapping("/")  // URL like  localhost:8080/
  public String hello() {
    return "index";
  }
  
  @GetMapping("/hotelsearch")
  public String hotelSearchPage(Model model) {
    Hotel hotel = new Hotel();
    model.addAttribute("hotel", hotel);
    return "search";
  }
  
  @GetMapping("/hotelsearch/{city}")
  public String getCityInfo(@PathVariable("city") String cityName, 
                               Model model) {
    Iterable<Hotel> hotels = hotelService.getHotels(cityName);
    model.addAttribute("hotels", hotels);
    return "results";
  }
  
  @PostMapping("/hotelsearch")
  public String hotelResultsPage(@Valid Hotel hotel, BindingResult result, 
        Model model) {
     Iterable<Hotel> hotels = hotelService.getHotels(hotel.getCity());
     model.addAttribute("hotels", hotels);
     return "results";
  }
  
  @GetMapping("/book-hotel")
  public String hotelOptionsPage(@RequestParam(value = "hotelid") int hotelid,
      @RequestParam(value = "hotelname") String hotel, @RequestParam(value = "hotelcity") String city,
      Model model) {
    Reservation reservation = new Reservation();
    reservation.setHotelId(hotelid);
    model.addAttribute("reservation", reservation);
    model.addAttribute("hotel", hotel);
    model.addAttribute("city", city);
    //System.out.println("hotel selected was " + hotelid);
    return "makebookingform";
  }
  
  @PostMapping("/book-hotel")
  public String bookRoomResultPage(@Valid Reservation reservation, BindingResult result, 
        Model model, HttpSession session) {
    // TODO write the reservation to the database
    //model.addAttribute("reservation", reservation);
    User user = new User();
    model.addAttribute("user", user);
    //reservation.setUser(user);
    session.setAttribute("reservation", reservation);
    return "signin";
  }
  
  @GetMapping("/signin")
  public String directSignIn(Model model, HttpSession session) {
    User user = new User();
    model.addAttribute("user", user);
    session.setAttribute("reservation", null);
    return "signin";
  }
  
  @PostMapping("/signin")
  public String signIn(@Valid User user, BindingResult result, 
        Model model, HttpSession session) {
        //if(userRepository.findByEmail(user.getEmail())) {
           //create reservation under that user
           // look up city info from database.  Might be multiple cities with same name.
           List<User> users = userRepository.findByEmail(user.getEmail());
           Reservation reservation = (Reservation) session.getAttribute("reservation");
           
           if (users.size() == 0) {
              //create user
              //create reservation under that user
              userRepository.save(user);
              reservation.setUser(user);
              model.addAttribute("reservation", reservation);
              reservationRepository.save(reservation);
              return "confirmation";
           }
           else {
              // get the first in the list incase multiple found
              User foundUser = users.get(0);
              if(foundUser.getPassword().equals(user.getPassword())) {
                model.addAttribute("reservation", reservation);
                reservation.setUser(foundUser);
                reservationRepository.save(reservation);
                return "confirmation";
              }
              else
                 return "signin";
           }
     }
  
  @GetMapping("/account")
  public String viewAccount(Model model, HttpSession session) {
    return "viewaccount";
  }
  
  @GetMapping("/cancel")
  public String cancelPage(Model model, HttpSession session) {
//    User user = new User();
//    model.addAttribute("user", user);
//    model.addAttribute("reservation", new Reservation());
//    Reservation reservation = new Reservation();
//    session.setAttribute("reservation", reservation);
    CancelObject cancelation = new CancelObject();
    model.addAttribute("cancelation", cancelation);
    return "cancel";
  }
  
  @PostMapping("/cancel")
  public String cancelBooking(@Valid CancelObject cancelation, BindingResult result, 
      Model model, HttpSession session) {
     List<User> users = userRepository.findByEmail(cancelation.getEmail());
//     Reservation reservation = (Reservation) session.getAttribute("reservation");
//     Long id = reservation.getId();
     Long id = cancelation.getId();
     if(users.size() == 0) {
        //user does not exist
        model.addAttribute("error", "Invalid email address.");
        System.out.println("No reservation with given email address " + cancelation.getEmail());
        model.addAttribute("cancelation", cancelation);
        return "cancel";
     }
     else {
        User foundUser = users.get(0);
        if(foundUser.getPassword().equals(cancelation.getPassword())) {
           if(reservationRepository.findById(id).isPresent()) {
              //remove the reservation from the database
             reservationRepository.deleteById(id);
             // returns a success page
             System.out.println("Deleted reservation and showing success page");
             return "cancelsuccess";
           } else {
             // no such reservation exists
             model.addAttribute("error", "No reservation exists with id " + id + " for " + cancelation.getEmail());
             System.out.println("No reservation exists with id given: " + id);
             model.addAttribute("cancelation", cancelation);
             return "cancel";
           }
         } else {
           // wrong password, show error
           model.addAttribute("error", "Invalid password.");
           System.out.println("Invalid Password.");
           model.addAttribute("cancelation", cancelation);
           return "cancel";
         }
     }
  }
    
  }
  
