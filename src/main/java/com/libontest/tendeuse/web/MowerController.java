package com.libontest.tendeuse.web;

import com.libontest.tendeuse.api.MowerOutputApi;
import com.libontest.tendeuse.api.MowerRequestApi;
import com.libontest.tendeuse.services.MowerService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("mower")
public class MowerController {

  private final MowerService mowerService;

  @PostMapping
  public ResponseEntity<List<MowerOutputApi>> handleRequestApi(
      @RequestBody @Valid MowerRequestApi requestApi
  ) {
    var outputApi = mowerService.mowGrass(requestApi);
    return ResponseEntity.ok().body(outputApi);
  }
}
