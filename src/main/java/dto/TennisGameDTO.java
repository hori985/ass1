package dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import model.TennisGame;
import model.TennisSet;

import javax.persistence.*;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor             // Creates constructor with all of the fields as arguments
@NoArgsConstructor              // Creates constructor with no arguments (Default)
@FieldDefaults(level = PRIVATE) // Sets the visibility of all fields to PRIVATE
@Builder                        // Builder Pattern (Lab 2)
@ToString                       // ToString method implementation for class
@Getter                         // Getters for all fields of the class
@Setter                         // Setters for all fields of the class
@Data
public class TennisGameDTO {


    int id;
    String p1Score;
    String p2Score;

    public TennisGameDTO(TennisGame game) {
        this.id = game.getId();
        this.p1Score = game.getP1Score();
        this.p2Score = game.getP2Score();
    }

}
