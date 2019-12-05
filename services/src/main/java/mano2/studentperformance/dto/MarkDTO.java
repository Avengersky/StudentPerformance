package mano2.studentperformance.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class MarkDTO extends BaseEntityDTO {

    @Min(1)
    @Max(10)
    private Integer markValue;
    private String note;
    @NotNull
    private long lessonId;
    @NotNull
    private long studentId;


}
