package mano2.studentperformance.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class SubgroupDTO extends BaseEntityDTO {

    @Min(0)
    private Integer number;

}
