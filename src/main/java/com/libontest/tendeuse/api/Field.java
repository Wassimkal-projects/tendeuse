package com.libontest.tendeuse.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Field {

  @JsonProperty("max_x")
  private Integer maxX;

  @JsonProperty("max_y")
  private Integer maxY;
}