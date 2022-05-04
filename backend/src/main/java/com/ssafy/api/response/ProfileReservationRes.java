package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@ApiModel("Profile Place Reservation Response")
public class ProfileReservationRes {
    
    @ApiModelProperty(name = "Reservation Id")
    Long reservationId;
    @ApiModelProperty(name = "Place Id")
    Long placeId;
    @ApiModelProperty(name = "Reservation Date")
    LocalDate date;
    @ApiModelProperty(name = "Reservation Time")
    LocalTime time;
    @ApiModelProperty(name = "Reservation Price")
    int price;

    // MongoDB에는 category로 저장되어 있지만, 우리는 MySQL에 저장할 때, CategoryId를 사용한다
    // 프론트에 전달할 때, 어떤 방식으로 전달해야하는지 알아봐야할 것
    @ApiModelProperty(name = "Sports Category")
    String category;
    @ApiModelProperty(name = "Place Address")
    String address;
    @ApiModelProperty(name = "Place Business Hour")
    List<String> bizhour;
    @ApiModelProperty(name = "Place Homepage")
    String homepage;
    @ApiModelProperty(name = "Place Content Menu")
    List<String> menu;
    @ApiModelProperty(name = "Place Name")
    String name;
    @ApiModelProperty(name = "Place Main Image")
    String img;
    @ApiModelProperty(name = "Place Phone Number")
    String phone;
    @ApiModelProperty(name = "Longitude")
    Long longitude;
    @ApiModelProperty(name = "Latitude")
    Long latitude;
    @ApiModelProperty(name = "Place Transport Information")
    String transport;
    @ApiModelProperty(name = "Place Near By")
    String near;
    @ApiModelProperty(name = "Hashtags")
    List<String> hashtags;

//    public static ProfileReservationRes of () {
//
//    }
    
}
