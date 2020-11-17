package by.realovka.diploma.dto;

import by.realovka.diploma.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UserRegDTO {

    private String nameRegDTO;
    private String passwordRegDTO;
    private String emailRegDTO;
    private String phoneRegDTO;
    private Role roleRegDTO;

}
