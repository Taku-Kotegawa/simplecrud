package com.example.domain.service.simplecrud;

import com.example.domain.model.simplecrud.Users;
import com.example.domain.model.simplecrud.UsersExample;
import com.example.domain.repository.simplecrud.UsersDataGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
@Transactional
class UsersServiceImplTest {

    @Inject
    UsersService target;

    @Inject
    UsersDataGenerator generator;

    @Nested
    @ContextConfiguration(locations = {"classpath:test-context.xml"})
    @Transactional
    class findOneByPrimaryKey {

        @Test
        @DisplayName("登録されているユーザをuidを指定して取得できる")
        void test001() {
            List<Users> inputs = generator.insertUsers(1);
            Users result = target.findOneByPrimaryKey("1");
            assertThat(result).isEqualTo(inputs.get(0));
        }

        @Test
        @DisplayName("指定したユーザが存在しない場合、例外がスローされる")
        void test002() {
            assertThatThrownBy(() -> {
                target.findOneByPrimaryKey("1");

            }).isInstanceOf(BusinessException.class)
                    .hasMessageContaining("User Not Found");
        }

    }

    @Nested
    @ContextConfiguration(locations = {"classpath:test-context.xml"})
    @Transactional
    class findAllByExample {

        @Test
        @DisplayName("条件に合致したユーザの一覧が取得できる")
        void test001() {
            //データ準備
            List<Users> inputs = generator.insertUsers(9);

            // 検索条件を指定
            UsersExample example = new UsersExample();
            example.or().andUidBetween("3", "5")
                    .andStatusIn(new ArrayList<Boolean>() {{
                        add(true);
                        add(false);
                    }});

            List<Users> results = target.findAllByExample(example);
            assertThat(results)
                    .as("配列のサイズを確認")
                    .hasSize(3);

            results = target.findAllByExample(null);
            assertThat(results).as("リストどうしを値比較").isEqualTo(inputs);
        }

    }

    @Nested
    @ContextConfiguration(locations = {"classpath:test-context.xml"})
    @Transactional
    class create {

    }

    @Nested
    @ContextConfiguration(locations = {"classpath:test-context.xml"})
    @Transactional
    class update {

    }

    @Nested
    @ContextConfiguration(locations = {"classpath:test-context.xml"})
    @Transactional
    class delete {

    }

}