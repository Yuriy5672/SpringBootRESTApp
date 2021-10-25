package com.app.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Suggestion {
    @JsonProperty("_id")
    private long id;
    private String key;
    private String name;
    private String fullName;
    private String type;
    private String country;
    private GeoPosition geoPosition;
    private long locationId;
    private boolean inEurope;
}
