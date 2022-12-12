package com.harith.springboot.repository;

import com.harith.springboot.domain.Anime;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Repository
public class AnimeRepository {
    private static List<Anime> animes;

    static  {
       animes = new ArrayList<>(List.of(
                new Anime(1,"First"),
                new Anime(2, "Second"),
                new Anime(3,"Third")
        ));
    }
    public List<Anime> listAll() {
        return animes;
    }

    public Anime save(Anime anime) {
        anime.setId(ThreadLocalRandom.current().nextInt(4,100000));
        animes.add(anime);
        return anime;
    }
}
