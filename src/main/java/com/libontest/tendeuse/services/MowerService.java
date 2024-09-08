package com.libontest.tendeuse.services;

import com.libontest.tendeuse.api.MowerOutputApi;
import com.libontest.tendeuse.api.MowerRequestApi;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MowerService {

  public List<MowerOutputApi> mowGrass(MowerRequestApi requestApi) {

    requestApi.getMowers().forEach(mower -> mower.mow(requestApi.getField()));

    return requestApi.getMowers().stream().map(
        mower -> MowerOutputApi.builder()
            .id(mower.getId())
            .x(mower.getPosition().getX())
            .y(mower.getPosition().getY())
            .orientation(mower.getOrientation())
            .build()
    ).toList();
  }
}
