package com.harith.springboot.repository;

import com.harith.springboot.domain.Anime;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnimeRepository {

    public List<Anime> listAll() {
        return List.of(
                new Anime(1,"First"),
                new Anime(2, "Second"),
                new Anime(3,"Third")
        );
    }
        }
