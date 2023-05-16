package mg.RESTAPI.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;

    @NotEmpty
    @Size(min =10, message = "Comment body should have at least 10 characters")
    private String body;

    //
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    //name should not be null or empty

    @NotEmpty (message = "Comment name should not be empty")
    private String name;
}
