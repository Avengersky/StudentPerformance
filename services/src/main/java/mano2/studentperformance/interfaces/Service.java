package mano2.studentperformance.interfaces;

import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.exceptions.ExistingLoginException;

import java.util.List;

public interface Service<T> {

    T getById(long id) throws EntityNotFoundException;

    T add(T entity);

    void delete(long id) throws EntityNotFoundException;

    List<T> getAll();

    T update(T entity) throws EntityNotFoundException;
}
