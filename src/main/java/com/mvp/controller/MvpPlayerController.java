package com.mvp.controller;

import com.mvp.model.response.ResponseBodyEntity;
import com.mvp.service.MvpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players/mvp")
@RequiredArgsConstructor
public class MvpPlayerController {
    private final MvpService mvpService;

    @PostMapping("")
    public ResponseEntity<ResponseBodyEntity> getMostValuablePlayer() {
        String mvp = mvpService.getMostValuablePlayer();
        return ResponseEntity.ok(new ResponseBodyEntity("MVP player of the season is " + mvp));
    }

}
