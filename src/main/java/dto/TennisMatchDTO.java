package dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import model.TennisMatch;
import model.TennisSet;

import java.util.ArrayList;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor             // Creates constructor with all of the fields as arguments
@NoArgsConstructor              // Creates constructor with no arguments (Default)
@FieldDefaults(level = PRIVATE) // Sets the visibility of all fields to PRIVATE
@Builder                        // Builder Pattern (Lab 2)
@ToString                       // ToString method implementation for class
@Getter                         // Getters for all fields of the class
@Setter                         // Setters for all fields of the class
@Data
public class TennisMatchDTO {
    int id;
    String player1;
    String player2;
    ArrayList<TennisSetDTO> sets = new ArrayList<TennisSetDTO>();

    public TennisMatchDTO(TennisMatch t) {
        this.id = t.getId();
        this.player1 = t.getPlayer1().getName();
        this.player2 = t.getPlayer2().getName();
        for (TennisSet set: t.getSets()) {
            this.sets.add(new TennisSetDTO(set));
        }
    }

}
