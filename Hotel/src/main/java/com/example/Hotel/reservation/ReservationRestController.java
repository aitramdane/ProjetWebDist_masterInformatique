package com.example.Hotel.reservation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/reservation/api")
@Api(value = "Reservation Rest Controller: contains all operations for managing reservations")
public class ReservationRestController {

    public static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);

    @Autowired
    private ReservationServiceImpl reservationService;



    /**
     * Ajoute un nouveau prêt dans la base de données H2.
     * @param reservationDTORequest
     * @param uriComponentBuilder
     * @return
     */
    @PostMapping("/addReservation")
    @ApiOperation(value = "Add a new reservation into the database H2", response = ReservationDTO.class)
    @ApiResponses(value = { @ApiResponse(code = 409, message = "Conflict: the reservation already exist"),
            @ApiResponse(code = 201, message = "Created: the reservation is successfully inserted"),
            @ApiResponse(code = 304, message = "Not Modified: the reservation is unsuccessfully inserted") })
    public ResponseEntity<ReservationDTO> createNewReservation(@RequestBody ReservationDTO reservationDTORequest, UriComponentsBuilder uriComponentBuilder) {

        Reservation existingReservation = reservationService.findReservationByreservationId(reservationDTORequest.getReservationId());
        if (existingReservation != null ) {
            return new ResponseEntity<ReservationDTO>(HttpStatus.CONFLICT);
        }
        Reservation reservationRequest = mapReservationDTOToReservation(reservationDTORequest);
        Reservation reservationResponse = reservationService.saveReservation(reservationRequest);
        if (reservationResponse != null) {
            ReservationDTO reservationDTO = mapReservationToReservationDTO(reservationResponse);
            return new ResponseEntity<ReservationDTO>(reservationDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<ReservationDTO>(HttpStatus.NOT_MODIFIED);
    }



    @PutMapping("/updateReservation")
    //@ApiOperation(value = "Update/Modify an existing reservation in the hotel system ", response = ClientDTO.class)
    @ApiResponses(value = { @ApiResponse(code = 404, message = "Not Found : the reservation does not exist"),
            @ApiResponse(code = 200, message = "Ok: the reservation is successfully updated"),
            @ApiResponse(code = 304, message = "Not Modified: the reservation is unsuccessfully updated") })
    public ResponseEntity<ReservationDTO> updateReservation(@RequestBody ReservationDTO reservationDTORequest) {

        if (!reservationService.checkIfReservationExists(reservationDTORequest.getReservationId())) {
            return new ResponseEntity<ReservationDTO>(HttpStatus.NOT_FOUND);
        }
        Reservation reservationRequest = mapReservationDTOToReservation(reservationDTORequest);
        Reservation reservationResponse = reservationService.updateReservation(reservationRequest);
        if (reservationResponse != null) {
            ReservationDTO reservationDTO = mapReservationToReservationDTO(reservationResponse);
            return new ResponseEntity<ReservationDTO>(reservationDTO, HttpStatus.OK);
        }
        return new ResponseEntity<ReservationDTO>(HttpStatus.NOT_MODIFIED);
    }


    @DeleteMapping("/deleteReservation/{reservationId}")
    @ApiOperation(value = "Delete a reservation from the system , if the reservation does not exist, nothing is done", response = String.class)
    @ApiResponses( value =  {
            @ApiResponse(code = 204, message = "No Content: reservation successfully deleted")

    } )
    public ResponseEntity<String> deleteReservation(@PathVariable Integer reservationId) {
        reservationService.deleteReservation(reservationId);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/searchReservationById")
    @ApiOperation(value="Search a reservation in the system by its Id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok: successfull research"),
            @ApiResponse(code = 204, message = "No Content: no result founded"),
    })
    public ResponseEntity <ReservationDTO> searchReservationById(@RequestParam("reservationId") Integer reservationId) {
            Reservation reservation = reservationService.findReservationByreservationId(reservationId);
            if (reservation != null) {
                ReservationDTO reservationDTO = mapReservationToReservationDTO(reservation);
                return new ResponseEntity<ReservationDTO>(reservationDTO, HttpStatus.OK);
            }
            return new ResponseEntity<ReservationDTO>(HttpStatus.NO_CONTENT);
        }



    @GetMapping("/searchByReservationDate")
    @ApiOperation(value="Search a reservation according to reservation date", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok: successfully research"),
            @ApiResponse(code = 204, message = "No Content: no result founded"),
    })
    public ResponseEntity<List<ReservationDTO>> searchReservationByReservationDate(@RequestParam("reservationDate") String reservationDate ) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Reservation> reservations = reservationService.findReservationByreservationDate(LocalDate.parse(reservationDate, format));
        if (reservations != null && !CollectionUtils.isEmpty(reservations)) {
            List<ReservationDTO> reservationDTOs = reservations.stream().map(reservation -> {
                return mapReservationToReservationDTO(reservation);
            }).collect(Collectors.toList());
            return new ResponseEntity<List<ReservationDTO>>(reservationDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<List<ReservationDTO>>(HttpStatus.NO_CONTENT);
    }



   /* @GetMapping("/clientReservation")
    @ApiOperation(value="List of all  reservations of a client ", response = List.class)
    @ApiResponse(code = 200, message = "Ok: successfully listed")
    public ResponseEntity<List<ReservationDTO>> searchAllReservationOfThisClient(@RequestParam("email") String email ) {
        List<Reservation> reservations = reservationService.getAllReservationOfThisClient(email);
        if (reservations != null && !CollectionUtils.isEmpty(reservations)) {
            List<ReservationDTO> reservationDTOs = reservations.stream().map(reservation -> {
                return mapReservationToReservationDTO(reservation);
            }).collect(Collectors.toList());
            return new ResponseEntity<List<ReservationDTO>>(reservationDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<List<ReservationDTO>>(HttpStatus.NO_CONTENT);
    }*/


    private ReservationDTO mapReservationToReservationDTO(Reservation reservation) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        ReservationDTO reservationDTO = mapper.map(reservation, ReservationDTO.class);

        mapper.getConfiguration().setAmbiguityIgnored(true);
        return reservationDTO;
    }


    private Reservation mapReservationDTOToReservation(ReservationDTO reservationDTO) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Reservation reservation = mapper.map(reservationDTO, Reservation.class);
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return reservation;
    }











}
