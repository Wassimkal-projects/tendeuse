package com.libontest.tendeuse;

import static com.libontest.tendeuse.enums.Instruction.*;
import static com.libontest.tendeuse.enums.Orientation.*;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libontest.tendeuse.api.Field;
import com.libontest.tendeuse.api.MowerOutputApi;
import com.libontest.tendeuse.api.MowerPosition;
import com.libontest.tendeuse.api.MowerRequestApi;
import com.libontest.tendeuse.api.MowerRequestApi.Mower;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TendeuseApplicationTests {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  public void setup() {

  }

  @ParameterizedTest
  @MethodSource("requestApi")
  void shouldReturnPositionAndOrientation(MowerRequestApi requestApi,
      List<MowerOutputApi> expectedOutput) {
    // When
    var response = restTemplate.postForEntity("/mower", requestApi, List.class);

    // Then
    var actualOutputApi = objectMapper.convertValue(response.getBody(),
        new TypeReference<List<MowerOutputApi>>() {
        });

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertThat(actualOutputApi).isNotNull();
    assertThat(actualOutputApi).isEqualTo(expectedOutput);
  }


  private static Stream<Arguments> requestApi() {
    return Stream.of(
        Arguments.of(
            requestWithSingleMower(),
            List.of(MowerOutputApi.builder().x(1).y(3).orientation(N).build())
        ),
        Arguments.of(
            requestWithMowerOutOfRange(),
            List.of(MowerOutputApi.builder().x(2).y(2).orientation(E).build())
        )
    );
  }

  private static MowerRequestApi requestWithSingleMower() {
    return MowerRequestApi.builder()
        .field(Field.builder().maxX(5).maxY(5).build())
        .mowers(
            List.of(
                Mower.builder()
                    .position(MowerPosition.builder().x(1).y(2).build())
                    .orientation(N)
                    .instructions(List.of(G, A, G, A, G, A, G, A, A))
                    .build()
            )).build();
  }

  private static MowerRequestApi requestWithMowerOutOfRange() {
    return MowerRequestApi.builder()
        .field(Field.builder().maxX(2).maxY(2).build())
        .mowers(
            List.of(
                Mower.builder()
                    .position(MowerPosition.builder().x(1).y(2).build())
                    .orientation(N)
                    .instructions(List.of(D, A, A, A, A, A))
                    .build()
            )).build();
  }

}
