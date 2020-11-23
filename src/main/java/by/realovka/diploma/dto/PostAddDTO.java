package by.realovka.diploma.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PostAddDTO {
    @NotBlank
    @NotEmpty
    private String textPostAddDTO;
}
