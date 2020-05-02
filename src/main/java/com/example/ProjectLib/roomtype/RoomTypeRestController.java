package com.example.ProjectLib.roomtype;


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
@RequestMapping("/rest/type/api")
@Api(value = "Room type Rest Controller")

public class RoomTypeRestController {
    @Autowired
    private RoomTypeServiceImpl roomTypeService;

    @GetMapping("/allTypes")
    @ApiOperation(value="List all room types of the Hotel", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok: successfully listed"),
            @ApiResponse(code = 204, message = "No Content: no result founded"),
    })
    public ResponseEntity<List<RoomTypeDTO>> getAllRoomTypes(){
        List<RoomType> types = roomTypeService.getAllRoomTypes();
        if(!CollectionUtils.isEmpty(types)) {
            types.removeAll(Collections.singleton(null));
            List<RoomTypeDTO> roomTypeDTOs = types.stream().map(type -> {
                return mapRoomTypeToRoomTypeDTO(type);
            }).collect(Collectors.toList());
            return new ResponseEntity<List<RoomTypeDTO>>(roomTypeDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<List<RoomTypeDTO>>(HttpStatus.NO_CONTENT);
    }



    /**
     * Transforme RoomType to RoomTypeDTO
     *
     * @param roomtype
     * @return
     */


    private RoomTypeDTO mapRoomTypeToRoomTypeDTO(RoomType roomtype) {
        ModelMapper mapper = new ModelMapper();
        RoomTypeDTO roomTypeDTO = mapper.map(roomtype, RoomTypeDTO.class);
        return roomTypeDTO;
    }








}
