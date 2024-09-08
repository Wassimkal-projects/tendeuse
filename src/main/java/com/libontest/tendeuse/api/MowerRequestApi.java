package com.libontest.tendeuse.api;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.libontest.tendeuse.enums.Instruction;
import com.libontest.tendeuse.enums.Orientation;
import java.util.List;
import lombok.Data;

@Data
public class MowerRequestApi {

  private Field field;
  private List<Mower> mowers;

  @Data
  public static class Mower {

    private String id;

    @JsonProperty("start_position")
    private MowerPosition position;

    private Orientation orientation;
    private List<Instruction> instructions;

    public void mow(Field field) {
      instructions.forEach(instruction -> {
        if (Instruction.A.equals(instruction)) {
          position.advanceIfNotOutOfRange(orientation, field);
        } else {
          orientation = orientation.rotate(instruction);
        }
      });
    }
  }

}

