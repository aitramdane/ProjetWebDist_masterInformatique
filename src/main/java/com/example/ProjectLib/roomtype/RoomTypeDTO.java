package com.example.ProjectLib.roomtype;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Room Type Model")
public class RoomTypeDTO {

    public RoomTypeDTO (Integer room_type_id, String room_type) {
        this.room_type=room_type;
        this.room_type_id=room_type_id;
    }

    public RoomTypeDTO () {}


    @ApiModelProperty(value = "Type id" )
    private int room_type_id;

    @ApiModelProperty(value = "Room Type")
    private String room_type;



    public int getRoom_type_id() {
        return room_type_id;
    }

    public void setRoom_type_id(int room_type_id) {
        this.room_type_id = room_type_id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }
}
