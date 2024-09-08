package com.libontest.tendeuse.api;

import com.libontest.tendeuse.enums.Orientation;
import lombok.Data;

@Data
public class MowerPosition {

  private Integer x;
  private Integer y;

  public void advanceIfNotOutOfRange(Orientation orientation, Field field) {
    x = orientation.nextX(x, field.getMaxX());
    y = orientation.nextY(y, field.getMaxY());
  }
}
