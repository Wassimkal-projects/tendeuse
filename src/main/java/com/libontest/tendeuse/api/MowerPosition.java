package com.libontest.tendeuse.api;

import com.libontest.tendeuse.enums.Orientation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MowerPosition {

  @NotNull
  @PositiveOrZero
  private Integer x;

  @NotNull
  @PositiveOrZero
  private Integer y;

  public void advanceIfNotOutOfRange(Orientation orientation, Field field) {
    if (!orientation.willGoOutOfRangeIfAdvance(x, y, field)) {
      x = x + orientation.getNextX();
      y = y + orientation.getNextY();
    }
  }
}
