package com.libontest.tendeuse.enums;

import com.libontest.tendeuse.api.Field;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Orientation {
  N(0, 1),
  E(1, 0),
  S(0, -1),
  W(-1, 0);

  private final Integer nextX;
  private final Integer nextY;

  public Orientation rotate(Instruction instruction) {
    if (Instruction.A.equals(instruction)) {
      return this;
    }

    var rotateMap = Map.of(
        N, Instruction.D.equals(instruction) ? E : W,
        E, Instruction.D.equals(instruction) ? S : N,
        S, Instruction.D.equals(instruction) ? W : E,
        W, Instruction.D.equals(instruction) ? N : S
    );

    return rotateMap.get(this);
  }

  public boolean willGoOutOfRangeIfAdvance(Integer x, Integer y, Field field) {
    return x + this.nextX > field.getMaxX() || y + this.nextY > field.getMaxY();
  }
}
