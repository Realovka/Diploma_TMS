package by.realovka.diploma.dto;

import by.realovka.diploma.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UserRegDTO {
    @NotBlank
    @NotEmpty
    private String nameRegDTO;
    @NotBlank
    @NotEmpty
    private String passwordRegDTO;
    @NotBlank
    @NotEmpty
    private String emailRegDTO;
    @NotBlank
    @NotEmpty
    private String phoneRegDTO;
    private Role roleRegDTO;
    private boolean deleted;

}
