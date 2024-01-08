package ie.atu.arsenalproject;


import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@NoArgsConstructor

public class Player {

    @Id
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
