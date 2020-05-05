package com.example.Hotel.room;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Room Model")
public class RoomDTO implements Comparable<RoomDTO> {

    @ApiModelProperty(value = "Room id")
    private Integer roomId;

    @ApiModelProperty(value = "floor no")
    private int floor_no;

    @ApiModelProperty(value = "Room type id")
    private Integer room_type_id;

    @ApiModelProperty(value = "Room status id")
    private Integer room_status_id;


    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public int getFloor_no() {
        return floor_no;
    }

    public void setFloor_no(int floor_no) {
        this.floor_no = floor_no;
    }



    public Integer getRoom_type_id() {
        return room_type_id;
    }

    public void setRoom_type_id(Integer room_type_id) {
        this.room_type_id = room_type_id;
    }

    public Integer getRoom_status_id() {
        return room_status_id;
    }

    public void setRoom_status_id(Integer room_status_id) {
        this.room_status_id = room_status_id;
    }

    @Override
    public int compareTo(RoomDTO o) {
        return Integer.toString(this.roomId).compareToIgnoreCase(Integer.toString(o.getRoomId())); }

}
