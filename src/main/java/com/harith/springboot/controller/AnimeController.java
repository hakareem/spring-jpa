package com.harith.springboot.controller;


import com.harith.springboot.domain.Anime;
import com.harith.springboot.repository.AnimeRepository;
import com.harith.springboot.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable int id) {
       Anime anime = (Anime) animeRepository.listAll()
               .stream()
               .filter(x -> x.getId() == id)
               .findFirst()
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime Not Found"));

       return ResponseEntity.ok(anime);
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody Anime anime) {
       return ResponseEntity.ok(animeRepository.save(anime));
    }
}
