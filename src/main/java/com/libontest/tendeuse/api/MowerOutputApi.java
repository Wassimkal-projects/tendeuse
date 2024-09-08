package com.libontest.tendeuse.api;

import com.libontest.tendeuse.enums.Orientation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MowerOutputApi {

  private String id;
  private Integer x;
  private Integer y;
  private Orientation orientation;
}
