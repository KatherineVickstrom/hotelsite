package cst438hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cst438hotel.domain.Hotel;
import cst438hotel.domain.HotelRepository;
import cst438hotel.domain.ReservationRepository;
import cst438hotel.domain.UserRepository;

@Service
public class HotelService
{
   @Autowired
   private HotelRepository hotelRepository;
   
   public HotelService() {
     
   }
   
   public HotelService(HotelRepository hotelRepository) {
     this.hotelRepository = hotelRepository;
   }
   
   public List<Hotel> getHotels(String cityName) {
     return hotelRepository.findByCity(cityName);
   }
}
