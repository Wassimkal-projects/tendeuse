package com.libontest.tendeuse.enums;

import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Orientation {
  N(0, 1),
  E(1, 0),
  S(0, -1),
  W(-1, 0);

  private final Integer nextX;
  private final Integer nextY;

  public Integer nextX(Integer current, Integer maxX) {
    return current + nextX <= maxX ? current + nextX : current;
  }

  public Integer nextY(Integer current, Integer maxY) {
    return current + nextY <= maxY ? current + nextY : current;
  }

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
}
