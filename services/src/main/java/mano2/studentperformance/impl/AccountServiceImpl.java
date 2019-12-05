package mano2.studentperformance.impl;

import mano2.studentperformance.converters.AccountConverter;
import mano2.studentperformance.dto.AccountDTO;
import mano2.studentperformance.entity.Account;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.interfaces.AccountService;
import mano2.studentperformance.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountConverter converter;

    @Autowired
    private AccountRepository repository;

    @Override
    public AccountDTO getById(long id) throws EntityNotFoundException {
        Account account = repository.findById(id).orElse(null);
        if (account != null){
            return converter.toDTO(account);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public AccountDTO add(AccountDTO entity) {
        Account savedAccount = repository.save(converter.toEntity(entity));
        return converter.toDTO(savedAccount);
    }


    @Override
    public void delete(long id) throws EntityNotFoundException {
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<AccountDTO> getAll() {
        List<AccountDTO> accountDTOS = new ArrayList<>();
        StreamSupport.stream(repository.findAll().spliterator(), false)
                .forEach(faculty ->accountDTOS.add(converter.toDTO(faculty)));
        return accountDTOS;
    }

    @Override
    public AccountDTO update(AccountDTO entity) throws EntityNotFoundException {
        return null;
    }


    @Override
    public Page<Account> getAccountPage(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
