package cst438hotel.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import cst438hotel.domain.Hotel;
import cst438hotel.domain.ReservationRepository;
import cst438hotel.service.HotelService;

@WebMvcTest(HotelRestController.class)
//@RunWith(SpringRunner.class)
public class HotelRestControllerTest {
        @MockBean
        private HotelService hotelService;
        @MockBean
        private ReservationRepository reservationRepository;
        @Autowired
        private MockMvc mvc;
        // This object will be magically initialized by the initFields method below.
        private JacksonTester<List<Hotel>> json;
        @BeforeEach
        public void setup() {
                JacksonTester.initFields(this, new ObjectMapper());
        }
        @Test
        public void testGetHotels() throws Exception {
              String name = "HotelTest";
              String city = "NewYork";
              String address = "123 Test St";
              int stars = 5;
              int price = 100;
              Hotel hotel = new Hotel(1L, name, address, city, stars, price);
              List<Hotel> expected = new ArrayList<Hotel>();
              expected.add(hotel);
                
              // given
              given(hotelService.getHotels(city)).willReturn(expected);

              // when
              MockHttpServletResponse response = 
                    mvc.perform(get("/api/hotels/NewYork").accept(
                          MediaType.APPLICATION_JSON))
                           .andReturn().getResponse();
 
              // then
              assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
              assertThat(response.getContentAsString()).
                    isEqualTo(json.write(expected).getJson());
              
              List<Hotel> hotelResult = json.parseObject(response.getContentAsString());
              
              List<Hotel> expectedResult = new ArrayList<Hotel>();
              expectedResult.add(hotel);
              
              assertThat(hotelResult).isEqualTo(expectedResult);
        }        
}