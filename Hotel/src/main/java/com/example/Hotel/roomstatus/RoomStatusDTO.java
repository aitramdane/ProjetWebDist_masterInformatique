package com.example.Hotel.roomstatus;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Room Status Model")
public class RoomStatusDTO {

    public RoomStatusDTO (Integer room_status_id, String room_status) {
        this.room_status=room_status;
        this.room_status_id=room_status_id;
    }

    public RoomStatusDTO () {}

    @ApiModelProperty(value = "Status id" )
    private int room_status_id;

    @ApiModelProperty(value = "room status" )
    private String room_status;


    public int getRoom_status_id() {
        return room_status_id;
    }

    public void setRoom_status_id(int room_status_id) {
        this.room_status_id = room_status_id;
    }

    public String getRoom_status() {
        return room_status;
    }

    public void setRoom_status(String room_status) {
        this.room_status = room_status;
    }
}
