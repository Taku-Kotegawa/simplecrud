package com.example.domain.service.simplecrud;

import com.example.domain.model.simplecrud.Users;
import com.example.domain.model.simplecrud.UsersExample;

import java.util.List;

public interface UsersService {


    Users findOneByPrimaryKey(String uid);

    List<Users> findAllByExample(UsersExample usersExample);

    Users create(Users users);

    Users update(Users users);

    void delete(String uid);

    Users softdelete(String uid);

}
