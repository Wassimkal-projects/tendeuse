package com.libontest.tendeuse.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Field {

  @NotNull
  @Min(value = 1)
  @JsonProperty("max_x")
  private Integer maxX;

  @NotNull
  @Min(value = 1)
  @JsonProperty("max_y")
  private Integer maxY;
}