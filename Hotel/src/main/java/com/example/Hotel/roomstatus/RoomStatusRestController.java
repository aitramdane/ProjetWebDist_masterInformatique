package com.example.Hotel.roomstatus;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/status/api")
@Api(value = "Room status Rest Controller")

public class RoomStatusRestController {
        @Autowired
        private RoomStatusServiceImpl roomStatusService;





    @GetMapping("/allStatus")
        @ApiOperation(value = "List all room status of the Hotel", response = List.class)
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Ok: successfully listed"),
                @ApiResponse(code = 204, message = "No Content: no result founded"),
        })

        public ResponseEntity<List<RoomStatusDTO>> getAllRoomStatus(){
            List<RoomStatus> allstatus = roomStatusService.getAllRoomStatus();
            if(!CollectionUtils.isEmpty(allstatus)) {
                allstatus.removeAll(Collections.singleton(null));
                List<RoomStatusDTO> roomStatusDTOs = allstatus.stream().map(status -> {
                    return mapRoomStatusToRoomStatusDTO(status);
                }).collect(Collectors.toList());
                return new ResponseEntity<List<RoomStatusDTO>>(roomStatusDTOs, HttpStatus.OK);
            }
            return new ResponseEntity<List<RoomStatusDTO>>(HttpStatus.NO_CONTENT);
        }






        private RoomStatusDTO mapRoomStatusToRoomStatusDTO (RoomStatus roomstatus){
        ModelMapper mapper = new ModelMapper();
        RoomStatusDTO roomStatusDTO = mapper.map(roomstatus, RoomStatusDTO.class);
        return roomStatusDTO;
    }


}



