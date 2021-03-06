package ro.pss.spring.rooms.web.dto;

import lombok.NoArgsConstructor;
import ro.pss.spring.rooms.model.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor
public class ReservationDto {
    public Long id;
    public Long roomId;
    public LocalDate date;
    public LocalTime from;
    public LocalTime to;
    public List<ParticipantDto> participantList = new ArrayList<>();

    public ReservationDto(Reservation res) {
        this.id = res.getId();
        this.roomId = res.getRoomId();
        this.date = res.getDate();
        this.from = res.getFrom();
        this.to = res.getTo();
        this.participantList = res.getParticipantList().stream().map(ParticipantDto::new).collect(toList());
    }
}
