package ua.service.SeStans.station;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.service.SeStans.reservation.Reservation;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
    public List<Station> findByOpeningTime(LocalTime time);
    public List<Station> findByClosingTime(LocalTime time);
}
