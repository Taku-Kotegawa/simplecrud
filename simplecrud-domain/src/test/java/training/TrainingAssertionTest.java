package training;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * AssertJを使ったアサーションの学習
 *
 * 以下のページに目次あり
 * https://docs.google.com/document/d/1vaMmtSMDNC67-2hFRzCpOGy7nIT4_dDYOhFStC_rdbg/edit
 */
@DisplayName("AssertJの使い方を学ぶ")
public class TrainingAssertionTest {

    @Nested
    @DisplayName("Bool値の評価")
    class testBoolean {
        @Test
        @DisplayName("値はTrueでなければならない")
        void valueIsTrue() {
            boolean actual = true;
            assertThat(actual).isTrue();
        }

        @Test
        @DisplayName("値はTrueであってはならない")
        void valueIsFalse() {
            boolean actual = false;
            assertThat(actual).isFalse();
        }

    }

    @Nested
    @DisplayName("nullの評価")
    class testNull {

        @Test
        @DisplayName("Nullでなければならない")
        void valueIsNulL() {
            Object actual = null;
            assertThat(actual).isNull();
        }

        @Test
        @DisplayName("Nullであってはならない")
        void valueIsNotNull() {
            Object actual = new Object();
            assertThat(actual).isNotNull();
        }
    }

    @Nested
    @DisplayName("２つのプリミティブ変数の比較")
    class testPrimitiveEquals {

        // isEqualTo - .equalsメソッドを使って比較
        // isEqualByComparingTo - .compareToメソッドを使って比較

        @Test
        @DisplayName("値は等しくなければならない")
        void primitiveEquals1() {
            int actual = 1;
            final int expected = 1;
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("型の異なる値は等しくなければならない(int - long)")
        void primitiveEquals2() {
            int actual = 1;
            final long expected = 1L;
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("型の異なる値は等しくなければならない(int - String)")
        void primitiveEquals3() {
            int actual = 1;
            final String expected = "1";
            // 自動変換はしてくれなかった
            assertThat(actual).isEqualTo(Integer.parseInt(expected));
        }

        @Test
        @DisplayName("型の異なる値は等しくなければならない(CompareToで比較)")
        void primitiveEquals4() {
            Integer actual = 1;
            final Integer expected = 1;
            assertThat(actual).isEqualByComparingTo(expected);
        }
    }

    @Nested
    @DisplayName("２つのオブジェクトの比較")
    class testObjectEquals {

        @Test
        @DisplayName("オブジェクトは等しくなければならない")
        void objectEquals1() {
            Integer actual = 1;
            final Integer expected = 1;
            assertThat(actual).isEqualTo(expected);
            assertThat(actual).isEqualByComparingTo(expected);
        }

        @Test
        @DisplayName("オブジェクトは等しくてはならない")
        void objectEquals2() {
            Integer actual = 1;
            final Integer expected = 2;
            assertThat(actual).isNotEqualTo(expected);
            assertThat(actual).isNotEqualByComparingTo(expected);
        }

        @Test
        @DisplayName("２つのBeanは等しくなければならない")
        void beanEquals2() {
            Person actual = new Person();
            actual.firstName = "aaa";
            actual.lastName = "bbb";

            Person expected = new Person();
            expected.firstName = "aaa";
            expected.lastName = "bbb";

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("２つのBeanは等しくなければならない")
        void beanEquals1() {
            Person2 actual = new Person2();
            actual.firstName = "aaa";
            actual.lastName = "bbb";

            Person2 expected = new Person2();
            expected.firstName = "aaa";
            expected.lastName = "bbb";

            // Person2はequalsをオーバーライドしていないので同値にならない
            assertThat(actual).isNotEqualTo(expected);
        }

        @Test
        @DisplayName("2つのBeanは同じオブジェクトである。")
        void beanSameAs1(){
            Person2 actual = new Person2();
            Person2 expected = actual;

            assertThat(actual).isSameAs(expected);
        }

        @Test
        @DisplayName("2つのBeanは同じオブジェクトではない")
        void beanSameAs2(){
            Person2 actual = new Person2();
            Person2 expected = new Person2();

            assertThat(actual).isNotSameAs(expected);
        }

        @Test
        @DisplayName("2つのBeanのクラスを比較")
        void beanInstanceOf2(){
            Person2 actual = new Person2();
            Person2 expected = new Person2();

            assertThat(actual).isInstanceOf(expected.getClass());
            assertThat(actual).isInstanceOf(Person2.class);
        }
    }

    @Nested
    @DisplayName("配列の比較")
    class testArrayCompare {

        final int[] ACTUAL = new int[]{5, 2, 7};
        final int[] EXPECTED = new int[]{5, 2, 7};

        @Test
        @DisplayName("Should contain the same integers")
        void shouldContainSameIntegers() {
            assertThat(ACTUAL).isEqualTo(EXPECTED);
        }

        @Test
        @DisplayName("null同士の比較は等しいと判定される")
        void shouldContainSameIntegers2() {
            int[] ACTUAL = null;
            int[] EXPECTED = null;
            assertThat(ACTUAL).isEqualTo(EXPECTED);
        }

    }


}
