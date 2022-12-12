package com.harith.springboot.controller;


import com.harith.springboot.domain.Anime;
import com.harith.springboot.repository.AnimeRepository;
import com.harith.springboot.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("animes")
@Slf4j
@RequiredArgsConstructor
public class AnimeController {

    private final DateUtil dateUtil;
    private final AnimeRepository animeRepository;

    @GetMapping()
    public ResponseEntity<List<Anime>> ListAll() {
        log.info("Formatted Date {}", dateUtil.formattedLocalDateTime(LocalDateTime.now()));
        return new ResponseEntity<>(animeRepository.listAll(), HttpStatus.OK);
    }
}
