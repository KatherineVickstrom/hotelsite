package cst438hotel.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
   @Query("select h from Hotel h where h.city =:city order by price desc")
   List<Hotel> findByCity(@Param("city") String city);
}
