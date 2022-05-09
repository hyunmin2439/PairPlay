package com.ssafy.domain.document;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.persistence.Id;
import java.util.List;

@Document("region")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Place {
    @Id
    private String id;
    @Field(name = "id")
    private Long placeId;
    private String category;
    private String address;
    private List<String> bizhour;
    private String homepage;
    private List<String> menu;
    private String name;
    private List<String> img;
    private String phone;
    private double longitude;
    private double latitude;
    private String transport;
    private String near;
    private List<String> hashtags;
    private int cnt;
}
