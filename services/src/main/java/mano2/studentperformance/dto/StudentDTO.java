package mano2.studentperformance.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class StudentDTO extends BaseEntityDTO {

    @NotBlank
    private long groupId;
    @NotBlank
    private long subGroupId;
    @NotBlank
    private UserDTO userDTO;

}
