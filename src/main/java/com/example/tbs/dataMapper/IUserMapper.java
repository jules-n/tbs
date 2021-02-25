package com.example.tbs.dataMapper;
import com.example.tbs.models.User;

public interface IUserMapper<T extends User> {

    boolean saveEntity(T entity);
    T getEntity(String login, String password);
    T getEntityById(int id);

}
