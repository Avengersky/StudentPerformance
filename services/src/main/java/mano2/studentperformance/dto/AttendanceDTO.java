package mano2.studentperformance.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AttendanceDTO extends BaseEntityDTO {

    private Boolean visit;
    private String note;
    private String reason;
    @NotBlank
    private long lessonId;
    @NotBlank
    private long studentId;

}
