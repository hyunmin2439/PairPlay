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
    String id;
    @Field(name = "id")
    Long attrId;
    String category;
    String address;
    List<String> bizhour;
    String homepage;
    List<String> menu;
    String name;
    List<String> img;
    String phone;
    double longitude;
    double latitude;
    String transport;
    String near;
    List<String> hashtags;
    int cnt;
}
