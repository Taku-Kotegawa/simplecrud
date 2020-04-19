package com.example.domain.repository.simplecrud;

import com.example.domain.model.simplecrud.Users;
import com.example.domain.model.simplecrud.UsersExample;
import org.apache.ibatis.session.RowBounds;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
@Transactional
class UsersRepositoryTest {

    @Inject
    UsersRepository target;

    /**
     * テスト用のUsersエンティティの生成
     *
     * @param uid ユーザID
     * @return Usersエンティティ
     */
    private Users generateTestUsers(String uid) {

        Users users = new Users();
        users.setUid(uid);
        users.setName("dummy name");
        users.setPass("dummy pass");
        users.setMail("dummy mail");
        users.setStatus(false);
        users.setCreatedAt(LocalDateTime.now());
        users.setChangedAt(LocalDateTime.now());
        users.setComment("dummy commnet");

        return users;
    }

    /**
     * Usersの主キー以外の項目の値を変更する
     * @param users 値を変更したいエンティテイｌ
     */
    private void changeValueTestUsers(Users users) {
        users.setName("changed name");
        users.setPass("changed pass");
        users.setMail("changed mail");
        users.setStatus(!users.getStatus());
        users.setCreatedAt(LocalDateTime.now());
        users.setChangedAt(LocalDateTime.now());
        users.setComment("changed comment");
    }

    @Nested
    @ContextConfiguration(locations = {"classpath:test-context.xml"})
    @Transactional
    class countByExample {

        @Test
        @DisplayName("0件")
        void test001() {
            long count = target.countByExample(new UsersExample());
            assertThat(count).isEqualTo(0L);
        }

        @Test
        @DisplayName("1件")
        void test002() {
            target.insert(generateTestUsers("1"));
            long count = target.countByExample(new UsersExample());
            assertThat(count).isEqualTo(1L);
        }

        @Test
        @DisplayName("100件")
        void test003() {

            final int MAX_NUMBER = 100;
            for (int i = 1; i <= MAX_NUMBER; i++) {
                target.insert(generateTestUsers(Integer.valueOf(i).toString()));
            }
            long count = target.countByExample(new UsersExample());
            assertThat(count).isEqualTo(MAX_NUMBER);
        }
    }

    @Nested
    @ContextConfiguration(locations = {"classpath:test-context.xml"})
    @Transactional
    class insert {

        @Test
        @DisplayName("成功したら戻り値は1でなければならない")
        void test001() {
            int count = target.insert(generateTestUsers("999"));
            assertThat(count).isEqualTo(1);
        }

        @Test
        @DisplayName("挿入した値がテーブルに格納されていること")
        void test002() {
            Users input = generateTestUsers("999");
            target.insert(input);
            Users result = target.selectByPrimaryKey(input.getUid());

            // 挿入した値の同値確認
            assertThat(result).isEqualTo(input);
        }
    }

    @Nested
    @ContextConfiguration(locations = {"classpath:test-context.xml"})
    @Transactional
    class insertSelective {
        @Test
        @DisplayName("成功したら戻り値は1でなければならない")
        void test001() {
            int count = target.insertSelective(generateTestUsers("999"));
            assertThat(count).isEqualTo(1);
        }

        @Test
        @DisplayName("挿入した値がテーブルに格納されていること")
        void test002() {
            Users input = generateTestUsers("999");
            target.insertSelective(input);
            Users result = target.selectByPrimaryKey(input.getUid());

            // 挿入した値の同値確認
            assertThat(result).isEqualTo(input);
        }
    }

    @Nested
    @ContextConfiguration(locations = {"classpath:test-context.xml"})
    @Transactional
    class updateByPrimaryKey {

        @Test
        @DisplayName("成功したら戻り値は1でなければならない")
        void test001() {
            Users input = generateTestUsers("999");
            target.insert(input);
            int count = target.updateByPrimaryKey(input);
            assertThat(count).isEqualTo(1);
        }

        @Test
        @DisplayName("失敗したら戻り値は0でなければならない")
        void test002() {
            Users input = generateTestUsers("999");
            target.insert(input);
            input.setUid("1");
            int count = target.updateByPrimaryKey(input);
            assertThat(count).isEqualTo(0);
        }

        @Test
        @DisplayName("更新した値がテーブルに格納されていること")
        void test003() {
            Users input = generateTestUsers("999");
            target.insert(input);

            changeValueTestUsers(input);

            target.updateByPrimaryKey(input);
            Users result = target.selectByPrimaryKey(input.getUid());

            // 作成日時は更新されないので一致しない
            assertThat(result).isNotEqualTo(input);

            // CreateAtを比較対象から除外すると一致する
            input.setCreatedAt(null);
            result.setCreatedAt(null);
            assertThat(result).isEqualTo(input);
        }
    }

    @Nested
    @ContextConfiguration(locations = {"classpath:test-context.xml"})
    @Transactional
    class updateByPrimaryKeySelective {

        @Test
        @DisplayName("成功したら戻り値は1でなければならない")
        void test001() {
            Users input = generateTestUsers("999");
            target.insert(input);
            int count = target.updateByPrimaryKeySelective(input);
            assertThat(count).isEqualTo(1);
        }

        @Test
        @DisplayName("失敗したら戻り値は0でなければならない")
        void test002() {
            Users input = generateTestUsers("999");
            target.insert(input);
            input.setUid("1");
            int count = target.updateByPrimaryKeySelective(input);
            assertThat(count).isEqualTo(0);
        }

        @Test
        @DisplayName("更新した値がテーブルに格納されていること")
        void test003() {
            Users input = generateTestUsers("999");
            target.insert(input);

            changeValueTestUsers(input);

            target.updateByPrimaryKeySelective(input);
            Users result = target.selectByPrimaryKey(input.getUid());

            // 作成日時は更新されないので一致しない
            assertThat(result).isNotEqualTo(input);

            // CreateAtを比較対象から除外すると一致する
            input.setCreatedAt(null);
            result.setCreatedAt(null);
            assertThat(result).isEqualTo(input);
        }
    }

//    class updateByExample {
//
//    }
//
//
//    class updateByExampleSelective {
//
//    }

    @Nested
    @ContextConfiguration(locations = {"classpath:test-context.xml"})
    @Transactional
    class deleteByPrimaryKey {

        @Test
        @DisplayName("成功したら戻り値は1でなければならない")
        void test001() {
            Users input = generateTestUsers("999");
            target.insert(input);
            int count = target.deleteByPrimaryKey(input.getUid());
            assertThat(count).isEqualTo(1);
        }

        @Test
        @DisplayName("失敗したら戻り値は0でなければならない")
        void test002() {
            Users input = generateTestUsers("999");
            target.insert(input);
            int count = target.deleteByPrimaryKey("1");
            assertThat(count).isEqualTo(0);
        }

        @Test
        @DisplayName("テーブルからデータが削除されていること")
        void test003() {
            Users input = generateTestUsers("999");
            target.insert(input);
            assertThat(target.selectByPrimaryKey(input.getUid())).isNotNull();

            target.deleteByPrimaryKey(input.getUid());
            assertThat(target.selectByPrimaryKey(input.getUid())).isNull();
        }
    }

    @Nested
    @ContextConfiguration(locations = {"classpath:test-context.xml"})
    @Transactional
    class deleteByExample {

        @Test
        @DisplayName("成功したら戻り値は削除した件数でなければならない")
        void test001() {
            final int MAX_NUMBER = 10;
            for (int i = 1; i <= MAX_NUMBER; i++) {
                target.insert(generateTestUsers(Integer.valueOf(i).toString()));
            }

            int count = target.deleteByExample(new UsersExample());
            assertThat(count).isEqualTo(MAX_NUMBER);
        }

        @Test
        @DisplayName("失敗したら戻り値は0でなければならない")
        void test002() {
            final int MAX_NUMBER = 10;
            for (int i = 1; i <= MAX_NUMBER; i++) {
                target.insert(generateTestUsers(Integer.valueOf(i).toString()));
            }

            // 存在したデータの条件で削除
            UsersExample example = new UsersExample();
            example.or().andUidEqualTo("NotExist");
            int count = target.deleteByExample(example);

            assertThat(count).isEqualTo(0);
        }

        @Test
        @DisplayName("テーブルからデータが削除されていること")
        void test003() {
            final int MAX_NUMBER = 10;
            for (int i = 1; i <= MAX_NUMBER; i++) {
                target.insert(generateTestUsers(Integer.valueOf(i).toString()));
            }

            UsersExample example = new UsersExample();
            example.or().andUidBetween("3", "5");
            int count = target.deleteByExample(example);

            // 削除件数の妥当性
            assertThat(count).isEqualTo(3);

            // 削除したデータが削除されているか確認
            for (int i = 3; i <= 5; i++) {
                assertThat(target.selectByPrimaryKey(Integer.valueOf(i).toString())).isNull();
            }

            // 残ったデータの件数
            assertThat(target.countByExample(new UsersExample())).isEqualTo(7);
        }
    }

    @Nested
    @ContextConfiguration(locations = {"classpath:test-context.xml"})
    @Transactional
    class selectByPrimaryKey {

        @Test
        @DisplayName("挿入したデータと同じデータが取得できる")
        void test001() {
            Users input = generateTestUsers("1");
            target.insert(input);
            assertThat(target.selectByPrimaryKey(input.getUid())).isEqualTo(input);
        }

        @Test
        @DisplayName("指定したキーが存在しない場合、戻り値はnullでなければならない")
        void test002() {
            assertThat(target.selectByPrimaryKey("NotExist")).isNull();
        }
    }


    @Nested
    @ContextConfiguration(locations = {"classpath:test-context.xml"})
    @Transactional
    class selectByExample {

        @Test
        @DisplayName("複数のデータをテーブルから取り出せること")
        void test001() {
            List<Users> inputs = new ArrayList<>();
            for (int i = 1; i < 10; i++) {
                inputs.add(generateTestUsers(Integer.valueOf(i).toString()));
            }

            for (Users input : inputs) {
                target.insert(input);
            }

            List<Users> results = target.selectByExample(new UsersExample());
            assertThat(results).isEqualTo(inputs);
        }
    }


    @Nested
    @ContextConfiguration(locations = {"classpath:test-context.xml"})
    @Transactional
    class selectByExampleWithRowbounds {
        @Test
        @DisplayName("複数のデータをテーブルから取り出せること")
        void test001() {
            List<Users> inputs = new ArrayList<>();
            for (int i = 1; i < 10; i++) {
                inputs.add(generateTestUsers(Integer.valueOf(i).toString()));
            }

            for (Users input : inputs) {
                target.insert(input);
            }

            RowBounds rowBounds = new RowBounds(2, 3);

            List<Users> results = target.selectByExampleWithRowbounds(new UsersExample(), rowBounds);
            assertThat(results).hasSize(3);

            List<Users> resultsAll = target.selectByExampleWithRowbounds(new UsersExample(), new RowBounds());
            assertThat(resultsAll).isEqualTo(inputs);
        }
    }
}
