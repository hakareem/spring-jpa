package com.harith.springboot.controller;


import com.harith.springboot.domain.Anime;
import com.harith.springboot.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("anime")
@Slf4j
public class AnimeController {

    @Autowired
    private DateUtil dateUtil;
    //    public AnimeController(DateUtil dateUtil) {
    //        this.dateUtil = dateUtil;
    //    }


    @GetMapping(path = "/list")
    public List<Anime> ListAll() {
        log.info("Formatted Date {}", dateUtil.formattedLocalDateTime(LocalDateTime.now()));
        return Arrays.asList(new Anime("AB"),new Anime("BB"));
    }
}
