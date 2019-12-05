package mano2.studentperformance.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class LessonDTO extends BaseEntityDTO {

    private Date date;
    private String topic;
    @NotBlank
    private long groupId;
    @NotBlank
    private long subgroupId;
    @NotBlank
    private long subjectId;
    @NotBlank
    private long teacherId;
}
