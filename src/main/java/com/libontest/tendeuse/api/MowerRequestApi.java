package com.libontest.tendeuse.api;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.libontest.tendeuse.enums.Instruction;
import com.libontest.tendeuse.enums.Orientation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Valid
@Builder
public class MowerRequestApi {

  @Valid
  @NotNull(message = "field should not be null")
  private Field field;
  @NotEmpty(message = "No mower provided")
  private List<@Valid Mower> mowers;

  @Data
  @Builder
  public static class Mower {

    private String id;

    @NotNull
    @Valid
    @JsonProperty("start_position")
    private MowerPosition position;

    @NotNull
    private Orientation orientation;

    @NotEmpty
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

