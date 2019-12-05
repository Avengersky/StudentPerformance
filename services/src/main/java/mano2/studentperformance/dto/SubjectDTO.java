package mano2.studentperformance.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class SubjectDTO extends BaseEntityDTO {

    @Size(min = 3, max = 20, message = "Subject name should be from 3 to 20")
    private String name;

}
