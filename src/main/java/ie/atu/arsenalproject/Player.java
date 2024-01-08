package ie.atu.arsenalproject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Email(message = "Invalid email address")
    private String email;

    @Min(value = 16, message = "Player must be at least 16 years old")
    @Max(value = 40, message = "Player must be no more than 40 years old")
    private int age;

    @NotBlank(message = "Nationality cannot be blank")
    private String nationality;

    @NotBlank(message = "Position cannot be blank")
    private String position;
}
