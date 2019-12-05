package mano2.studentperformance.interfaces;

import mano2.studentperformance.dto.AccountDTO;
import mano2.studentperformance.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService extends Service<AccountDTO> {

    Page<Account> getAccountPage(Pageable pageable);
}