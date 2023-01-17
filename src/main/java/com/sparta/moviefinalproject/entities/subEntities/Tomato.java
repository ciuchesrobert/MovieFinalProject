package com.sparta.moviefinalproject.entities.subEntities;

import com.mongodb.lang.Nullable;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tomato {
    @Nullable
    private String consensus;
    @Nullable
    @Embedded
    private Critic critic;
    @Nullable
    private LocalDateTime dvd;
    @Nullable
    private Integer fresh;
    @Nullable
    private LocalDateTime lastUpdated;
    @Nullable
    private String production;
    @Nullable
    private Integer rotten;
    @Embedded
    @Nullable
    private Viewer viewer;
    @Nullable
    private String website;
}
