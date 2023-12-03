package com.example.calc;

import com.example.calc.entity.Post;
import com.example.calc.repository.CalculatorRepository;
import com.example.calc.service.CalculatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)//확장 라이브러리 사용 할 떄 사용
public class CalculatorServiceTest {

    @Mock//가짜 객체를 만들고 싶은게 있을때 사용 //껍데기 객체
    CalculatorRepository calculatorRepository;

    @InjectMocks// 각자 객체를 만든것들을 주입 받을때 사용
    CalculatorService calculatorService;

    @Nested// 폴더형식처럼 관리하여 확인할 수 있다.
    @DisplayName("덧셈")
    class AddFonction {
        @Test
        @DisplayName("덧셈 성공")
        void addSuccess() {
            // given
            int num1 = 10;
            int num2 = 5;
            int save = 15;

            Post post = new Post();

            //stubbing 방식
            //객체를 이용한 주소값이 달라 오류가 생길때.. 우회 any()
            given(calculatorRepository.addResult(any()))
                    .willReturn(post);

            // when
            int result = calculatorService.add(num1, num2);

            // then
            assertThat(result).isEqualTo(15);
        }
    }

    @Nested
    @DisplayName("뺄셈")
    class SubtractClass {

        @Test
        @DisplayName("뺄셈 성공")
        void subtractSuccessCase1() {
            int num1 = 20;
            int num2 = 5;

            int result = calculatorService.subtract(num1, num2);

            assertThat(result).isEqualTo(15);
        }

        @Test
        @DisplayName("뺄셈 성공 : 음수는 취급 안함")
        void subtractSuccessCase2() {
            int num1 = 5;
            int num2 = 10;

            int result = calculatorService.subtract(num1, num2);

            assertThat(result).isEqualTo(5);
        }
    }

    @Nested
    @DisplayName("나눗셈")
    class DivideFunction {

        @Test
        @DisplayName("나눗셈 성공")
        void divideSuccessCase1() {
            double num1 = 10;
            double num2 = 5;

            double result = calculatorService.divide(num1, num2);

            assertThat(result).isEqualTo(2);
        }

        @Test
        @DisplayName("나눗셈 성공: 소숫점 2째자리")
        void divideSuccessCase2() {
            double num1 = 5;
            double num2 = 3;

            double result = calculatorService.divide(num1, num2);

            assertThat(result).isEqualTo(1.67);
        }

        @Test
        @DisplayName("나눗셈 실패: 분모에 0이 있으면 안됨")
        void divideFailCase1() {
            double num1 = 5;
            double num2 = 0;

            assertThatThrownBy(() ->
                    calculatorService.divide(num1, num2)
            ).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("분모에 0이 있으면 안됩니다.");
        }
    }
}
