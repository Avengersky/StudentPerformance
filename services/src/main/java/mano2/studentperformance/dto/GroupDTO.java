package mano2.studentperformance.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GroupDTO extends BaseEntityDTO {

    private long groupNumber;
    @NotBlank
    private long facultyId;

}
