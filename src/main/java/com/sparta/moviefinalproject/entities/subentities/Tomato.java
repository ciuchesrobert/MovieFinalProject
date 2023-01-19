package com.sparta.moviefinalproject.entities.subentities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tomato {
    private String consensus;
    @Embedded
    private Critic critic;
    private LocalDateTime dvd;
    private Integer fresh;
    private LocalDateTime lastUpdated;
    private String production;
    private Integer rotten;
    @Embedded
    private Viewer viewer;
    private String website;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tomato tomato = (Tomato) o;
        return Objects.equals(consensus, tomato.consensus) && Objects.equals(critic, tomato.critic) && Objects.equals(dvd, tomato.dvd) && Objects.equals(fresh, tomato.fresh) && Objects.equals(lastUpdated, tomato.lastUpdated) && Objects.equals(production, tomato.production) && Objects.equals(rotten, tomato.rotten) && Objects.equals(viewer, tomato.viewer) && Objects.equals(website, tomato.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consensus, critic, dvd, fresh, lastUpdated, production, rotten, viewer, website);
    }
}
