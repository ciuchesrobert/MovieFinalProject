package com.sparta.moviefinalproject.entities.subentities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Award {
    private Integer wins;
    private Integer nominations;
    private String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Award award = (Award) o;
        return wins.equals(award.wins) && nominations.equals(award.nominations) && text.equals(award.text);
    }
    public boolean awardEquals(Award o) {
        if (this.getNominations() == o.getNominations() &&
                this.getText() == o.getText() &&
                this.getWins() == o.getWins())
        {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wins, nominations, text);
    }
}
