package mano2.studentperformance.dto;

import lombok.Data;

@Data
public class AccountDTO extends BaseEntityDTO{

    private String name;
    private String surname;
    private String patronymic;
}
