package com.sparta.moviefinalproject.entities.subentities;

import com.mongodb.lang.Nullable;
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
