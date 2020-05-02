package com.example.ProjectLib.room;


import com.example.ProjectLib.client.Client;
import com.example.ProjectLib.client.ClientDTO;
import com.example.ProjectLib.reservation.Reservation;
import com.example.ProjectLib.reservation.ReservationDTO;
import com.example.ProjectLib.roomtype.RoomType;
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

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/room/api")
@Api(value = "Room Rest Controller: contains all operations for managing rooms")
public class RoomRestController {

    public static final Logger LOGGER = LoggerFactory.getLogger(RoomRestController.class);


    @Autowired
    private RoomServiceImpl roomService;



    @PostMapping("/addRoom")
    @ApiOperation(value = "Add a new Room in the hotel", response = RoomDTO.class)
    @ApiResponses(value = { @ApiResponse(code = 409, message = "Conflict: the room already exist"),
            @ApiResponse(code = 201, message = "Created: room is successfully inserted"),
            @ApiResponse(code = 304, message = "Not Modified: room is unsuccessfully inserted") })
    public ResponseEntity<RoomDTO> createNewRoom( @RequestBody RoomDTO roomDTORequest, UriComponentsBuilder uriComponentBuilder) {

        Room existingRoom = roomService.findRoomByRoomId(roomDTORequest.getRoomId());
        if (existingRoom != null ) {
            return new ResponseEntity<RoomDTO>(HttpStatus.CONFLICT);
        }
        Room roomRequest = mapRoomDTOToRoom(roomDTORequest);
        Room roomResponse = roomService.saveRoom(roomRequest);
        if (roomResponse != null) {
            RoomDTO roomDTO = mapRoomToRoomDTO(roomResponse);
            return new ResponseEntity<RoomDTO>(roomDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<RoomDTO>(HttpStatus.NOT_MODIFIED);
    }




    @PutMapping("/updateRoom")
    @ApiOperation(value = "Update/Modify an existing Room in the Hotel", response = RoomDTO.class)
    @ApiResponses(value = { @ApiResponse(code = 404, message = "Not Found : the room does not exist"),
            @ApiResponse(code = 200, message = "Ok: the room is successfully updated"),
            @ApiResponse(code = 304, message = "Not Modified: the room is unsuccessfully updated") })
    public ResponseEntity<RoomDTO> updateRoom(@RequestBody RoomDTO roomDTORequest) {

        if (!roomService.checkIfNoExists(roomDTORequest.getRoomId())) {
            return new ResponseEntity<RoomDTO>(HttpStatus.NOT_FOUND);
        }
        Room roomRequest = mapRoomDTOToRoom(roomDTORequest);
        Room room  = roomService.updateRoom(roomRequest);
        if (room != null) {
            RoomDTO roomDTO = mapRoomToRoomDTO(room);
            return new ResponseEntity<RoomDTO>(roomDTO, HttpStatus.OK);
        }
        return new ResponseEntity<RoomDTO>(HttpStatus.NOT_MODIFIED);
    }


    @GetMapping("/searchByNo")
    @ApiOperation(value="Search a Room in the Hotel by its no", response = RoomDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok: successfull research"),
            @ApiResponse(code = 204, message = "No Content: no result founded"),
    })
    public ResponseEntity<RoomDTO> searchRoomByNo(@RequestParam("roomId") Integer no,
                                                    UriComponentsBuilder uriComponentBuilder) {
        Room room = roomService.findRoomByRoomId(no);
        if (room != null) {
            RoomDTO roomDTO = mapRoomToRoomDTO(room);
            return new ResponseEntity<RoomDTO>(roomDTO, HttpStatus.OK);
        }
        return new ResponseEntity<RoomDTO>(HttpStatus.NO_CONTENT);
    }




    @DeleteMapping("/deleteRoom/{roomId}")
    @ApiOperation(value = "Delete a room from the system , if the room does not exist, nothing is done", response = String.class)
    @ApiResponses( value =  {
            @ApiResponse(code = 204, message = "No Content: room successfully deleted")

    } )
    public ResponseEntity<String> deleteRoom(@PathVariable Integer roomId) {
        roomService.deleteRoom(roomId);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }



    @GetMapping("/RoomByType")
    @ApiOperation(value="List of rooms by a type ", response = List.class)
    @ApiResponse(code = 200, message = "Ok: successfully listed")
    public ResponseEntity<List<RoomDTO>> searchAllRoomsByType(@RequestParam("room_type") String roomType ) {
        List<Room> rooms = roomService.getAllRoomsByType(roomType);
        if (rooms != null && !CollectionUtils.isEmpty(rooms)) {
            List<RoomDTO> roomDTOs = rooms.stream().map(room -> {
                return mapRoomToRoomDTO(room);
            }).collect(Collectors.toList());
            return new ResponseEntity<List<RoomDTO>>(roomDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<List<RoomDTO>>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/RoomByStatus")
    @ApiOperation(value="List of rooms by status", response = List.class)
    @ApiResponse(code = 200, message = "Ok: successfully listed")
    public ResponseEntity<List<RoomDTO>> searchAllRoomsByStatus(@RequestParam("room_status") String roomStatus ) {
        List<Room> rooms = roomService.getAllRoomsByStatus(roomStatus);
        if (rooms != null && !CollectionUtils.isEmpty(rooms)) {
            List<RoomDTO> roomDTOs = rooms.stream().map(room -> {
                return mapRoomToRoomDTO(room);
            }).collect(Collectors.toList());
            return new ResponseEntity<List<RoomDTO>>(roomDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<List<RoomDTO>>(HttpStatus.NO_CONTENT);
    }



    private RoomDTO mapRoomToRoomDTO(Room room) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        RoomDTO roomDTO = mapper.map(room, RoomDTO.class);
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return roomDTO;
    }




    private Room mapRoomDTOToRoom(RoomDTO roomDTO) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        Room room = mapper.map(roomDTO, Room.class);
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return room;
    }

}
