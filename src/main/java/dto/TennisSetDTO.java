package dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import model.TennisGame;
import model.TennisSet;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor             // Creates constructor with all of the fields as arguments
@NoArgsConstructor              // Creates constructor with no arguments (Default)
@FieldDefaults(level = PRIVATE) // Sets the visibility of all fields to PRIVATE
@Builder                        // Builder Pattern (Lab 2)
@ToString                       // ToString method implementation for class
@Getter                         // Getters for all fields of the class
@Setter                         // Setters for all fields of the class
@Data
public class TennisSetDTO {
    int id;
    List<TennisGameDTO> games = new ArrayList<TennisGameDTO>();

    public TennisSetDTO(TennisSet set){
        this.id = set.getId();
        for(TennisGame g : set.getGames()){
            this.games.add(new TennisGameDTO(g));
        }
    }
}
