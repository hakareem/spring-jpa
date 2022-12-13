package com.harith.springboot.util;

import com.harith.springboot.domain.Anime;
import com.harith.springboot.exception.ResourceNotFoundException;
import com.harith.springboot.repository.AnimeRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Utils {
    public String formattedLocalDateTime (LocalDateTime localDateTime){
    return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
    }

    public Anime findAnimeOrThrowNotFound(int id, AnimeRepository animeRepository) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Anime Not Found"));
    }
}
