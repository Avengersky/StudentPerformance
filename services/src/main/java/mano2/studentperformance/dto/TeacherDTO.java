package mano2.studentperformance.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TeacherDTO extends BaseEntityDTO {

    @NotBlank
    private UserDTO userDTO;
}
