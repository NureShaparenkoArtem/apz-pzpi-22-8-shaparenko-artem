package ua.service.SeStans.reservation;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> getReservations() {
        return reservationService.getReservations();
    }

    @PostMapping
    public Reservation createReservation(@Parameter(schema = @Schema(type = "string", format = "date-time"))
                                             @RequestParam LocalDateTime reservation_time,
                                         @Parameter(schema = @Schema(type = "string", format = "date-time"))
                                             @RequestParam LocalDateTime reservation_end_time,
                                         @RequestParam String status,
                                         @RequestParam Boolean mechanic_need,
                                         @RequestParam Integer station_id,
                                         @RequestParam Integer vehicle_id,
                                         @RequestParam Integer mechanic_id) {
        return reservationService.createReservation(reservation_time, reservation_end_time, status,
                mechanic_need, station_id, vehicle_id, mechanic_id);
    }

    @PutMapping(path = "/{reservationId}")
    public Reservation updateReservation(@PathVariable Integer reservationId,
                                         @Parameter(schema = @Schema(type = "string", format = "date-time"))
                                             @RequestParam LocalDateTime reservation_time,
                                         @Parameter(schema = @Schema(type = "string", format = "date-time"))
                                             @RequestParam LocalDateTime reservation_end_time,
                                         @RequestParam String status,
                                         @RequestParam Boolean mechanic_need,
                                         @RequestParam Integer station_id,
                                         @RequestParam Integer vehicle_id,
                                         @RequestParam Integer mechanic_id) {
        return reservationService.updateReservation(reservationId, reservation_time, reservation_end_time,
                status, mechanic_need, station_id, vehicle_id, mechanic_id);
    }

    @PutMapping
    public void updateReservationPrice(@RequestParam Integer reservationId, @RequestParam Float mechanicPrice) {
        reservationService.updateReservationPrice(reservationId, mechanicPrice);
    }

    @DeleteMapping(path = "/{reservationId}")
    public void deleteReservation(@PathVariable Integer reservationId) {
        reservationService.deleteReservation(reservationId);
    }
}
