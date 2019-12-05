package mano2.studentperformance.converters;

import mano2.studentperformance.dto.AccountDTO;
import mano2.studentperformance.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {

    public Account toEntity(AccountDTO accountDTO){
        Account account = new Account();
        account.setId(accountDTO.getId());
        account.setName(accountDTO.getName());
        account.setSurname(accountDTO.getSurname());
        account.setPatronymic(accountDTO.getPatronymic());
        return account;
    }

    public AccountDTO toDTO(Account account){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setName(account.getName());
        accountDTO.setSurname(account.getSurname());
        accountDTO.setPatronymic(account.getPatronymic());
        return accountDTO;
    }
}
