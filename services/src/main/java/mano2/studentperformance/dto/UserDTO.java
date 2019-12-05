package mano2.studentperformance.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class UserDTO extends BaseEntityDTO {

    @NotBlank
    @Size(min = 8, max = 24)
    private String login;
    @NotBlank
    @Size(min = 8)
    private String password;
    @NotBlank
    private RoleDTO role;
    @NotBlank
    private AccountDTO accountDTO;

}
