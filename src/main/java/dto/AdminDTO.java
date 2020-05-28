package dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import model.Admin;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor             // Creates constructor with all of the fields as arguments
@NoArgsConstructor              // Creates constructor with no arguments (Default)
@FieldDefaults(level = PRIVATE) // Sets the visibility of all fields to PRIVATE
@Builder                        // Builder Pattern (Lab 2)
@ToString                       // ToString method implementation for class
@Getter                         // Getters for all fields of the class
@Setter                         // Setters for all fields of the class
@Data
public class AdminDTO {
    int id;

    String mail;

    String password;

    String name;

    public AdminDTO(Admin admin) {
        this.id = admin.getId();
        this.mail = admin.getMail();
        this.password = admin.getPassword();
        this.name = admin.getName();
    }
}
