package com.example.domain.service.simplecrud;

import com.example.domain.model.simplecrud.Users;
import com.example.domain.model.simplecrud.UsersExample;
import com.example.domain.repository.simplecrud.UsersRepository;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    @Inject
    UsersRepository usersRepository;

    @Inject
    MessageSource messageSource;

    @Override
    public Users findOneByPrimaryKey(String uid) {

        return usersRepository.selectByPrimaryKey(uid);
    }

    @Override
    public List<Users> findAllByExample(UsersExample usersExample) {

        return usersRepository.selectByExample(usersExample);
    }

    @Override
    public Users create(Users users) {

        // 重複チェック
        if (usersRepository.selectByPrimaryKey(users.getUid()) != null) {
            throw new BusinessException("User already registered");
        }

        users.setStatus(true);
        users.setPass(users.getPass());

        LocalDateTime now = LocalDateTime.now();
        users.setCreatedAt(now);
        users.setChangedAt(now);

        usersRepository.insert(users);
        return findOne(users.getUid());
    }

    @Override
    public Users update(Users users) {

        users.setChangedAt(LocalDateTime.now());

        usersRepository.updateByPrimaryKey(users);
        return findOne(users.getUid());
    }

    @Override
    public void delete(String uid) {

        findOne(uid);
        usersRepository.deleteByPrimaryKey(uid);

    }

    @Override
    public Users softdelete(String uid) {
        Users users = findOne(uid);
        users.setStatus(false);
        return update(users);

    }

    private Users findOne(String uid) {
        Users users = usersRepository.selectByPrimaryKey(uid);
        if (users == null) {
            throw new BusinessException("User Not Found");
        } else {
            return users;
        }
    }

}
