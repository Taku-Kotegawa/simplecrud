package com.example.domain.repository.simplecrud;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.domain.model.simplecrud.Users;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static org.apache.commons.lang3.RandomStringUtils.*;

@Component
public class UsersDataGenerator {

    @Inject
    UsersRepository usersRepository;

    /**
     * Usersエンティティを生成する。(uid指定)
     * @param uid uid
     * @return Usersエンティティ
     */
    private Users generateUsersOne(String uid) {

        Users users = generateUsersOne();
        users.setUid(uid);
        return users;
    }

    /**
     * Usersエンティティを生成する。(uidランダム)
     *
     * @return Usersエンティティ
     */
    private Users generateUsersOne() {

        Users users = new Users();
        // uidがユニークになることは保証していない
        users.setUid(randomAlphanumeric(60));
        users.setName(randomAlphanumeric(60));
        users.setPass(randomAlphanumeric(255));
        users.setMail(randomAlphanumeric(254));
        users.setStatus(false);
        users.setCreatedAt(LocalDateTime.now());
        users.setChangedAt(LocalDateTime.now());
        users.setComment(randomAlphanumeric(2000));

        return users;
    }

    /**
     * リストで生成する。(uidは1からの連番)
     *
     * @param n 件数
     * @return リスト
     */
    public List<Users> generateUsersList(int n) {
        List<Users> usersList = new ArrayList<>();
        for (int i=1; i <= n; i++) {
            usersList.add(generateUsersOne(Integer.valueOf(i).toString()));
        }
        return usersList;
    }

    /**
     * 生成したUsersをテーブルに登録する。(uidは連番)
     *
     * @param n 件数
     * return リスト
     */
    public List<Users> insertUsers(int n) {
        List<Users> list = generateUsersList(n);
        for(Users users : list) {
            usersRepository.insert(users);
        }

        return list;
    }

}
