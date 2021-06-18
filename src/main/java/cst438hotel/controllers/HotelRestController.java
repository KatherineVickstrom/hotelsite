package cst438hotel.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import cst438hotel.domain.Hotel;
import cst438hotel.domain.HotelRepository;
import cst438hotel.domain.Reservation;
import cst438hotel.domain.ReservationRepository;
import cst438hotel.service.HotelService;

@RestController
public class HotelRestController
{

  @Autowired
  private HotelService hotelService;
  
  @Autowired 
  private ReservationRepository reservationRepository;
  
  @Autowired
  private HotelRepository hotelRepository;
  
  public HotelRestController() {
    
  }
  
  public HotelRestController(HotelService hotelService) {
    this.hotelService = hotelService;
  }
   
   @GetMapping("/api/hotels/{city}")
   public List<Hotel> getHotels(@PathVariable("city") String cityName) {
     List<Hotel> hotels = hotelService.getHotels(cityName);
     if (hotels.isEmpty()) {
       throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hotels found for city: " + cityName + ".");
     } else {
       return hotels;
     }
   }
   
   @GetMapping("/api/hotel/{hotelid}")
   public Hotel getHotelById(@PathVariable("hotelid") Long hotelid) {
     try {
       Hotel hotel = hotelRepository.getById((Long)hotelid);
       if (hotel == null || hotel.equals(new Hotel()))
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hotel found with id: " + hotelid + ". ");
       else
         return hotel;
     } catch (Exception e) {
       throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hotel found with id: " + hotelid + ". " + e.getMessage());
     }
   }
   
   // For debugging purposes only
   @GetMapping("/api/reservations/{id}")
   public Reservation getReservations(@PathVariable("id") Long reservationid) {
     Optional<Reservation> reservations = reservationRepository.findById(reservationid);
     if (!reservations.isPresent()) {
       throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No reservations found with id: " + reservationid + ".");
     } else {
       return reservations.get();
     }
   }
   
   @PostMapping("/api/reservations")
   public Reservation createReservation(@Valid @RequestBody Reservation reservation) {
       return reservationRepository.save(reservation);
   }

   @PutMapping("/api/reservations/{id}")
   public ResponseEntity<Reservation> updateReservation(@PathVariable(value = "id") Long reservationid,
        @Valid @RequestBody Reservation reservationDetails) {
       Optional<Reservation> containerOfReservation = reservationRepository.findById(reservationid);
       if (!containerOfReservation.isPresent()) {
         // reservation with that id not found.  Send 404 return code.
         return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
       } else {
         Reservation reservation = containerOfReservation.get();
         reservation.setHotelId(reservationDetails.getHotelId());
         reservation.setRoomtype(reservationDetails.getRoomtype());
         reservation.setStartdate(reservationDetails.getStartdate());
         reservation.setEnddate(reservationDetails.getEnddate());
         // won't ever update the user bc the reservation can't be reassigned to another user
         Reservation updatedReservation = reservationRepository.save(reservation);
         return new ResponseEntity<Reservation>(updatedReservation, HttpStatus.OK);
       }
   }
   
   @DeleteMapping("/api/reservations/{reservationid}")
   public ResponseEntity<Reservation> deleteCity(@PathVariable("reservationid") Long id ) {
     // look up city info from database.  Might be multiple cities with same name.
     Optional<Reservation> container = reservationRepository.findById(id);
     if (!container.isPresent()) {
       // reservation with that id not found.  Send 404 return code.
       return new ResponseEntity<Reservation>( HttpStatus.NOT_FOUND);
     } else {
       Reservation reservation = container.get();
       reservationRepository.delete(reservation);
       // return 204,  request successful.  no content returned.
       return new ResponseEntity<Reservation>( HttpStatus.NO_CONTENT);
     }
   }
   
   
}
