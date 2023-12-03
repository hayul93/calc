package com.example.calc;

import com.example.calc.repository.CalculatorRepository;
import com.example.calc.service.CalculatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)//확장 라이브러리 사용 할 떄 사용
public class CalculatorServiceTest {

    @Mock//가짜 객체를 만들고 싶은게 있을때 사용 //껍데기 객체
    CalculatorRepository calculatorRepository;

    @Nested// 폴더형식처럼 관리하여 확인할 수 있다.
    @DisplayName("덧셈")
    class AddFonction {
        @Test
        @DisplayName("덧셈 성공")
        void addSuccess() {
            // given
            int num1 = 220000000;
            int num2 = 5;

            // when
            CalculatorService calculatorService = new CalculatorService(calculatorRepository);
            int result = calculatorService.add(num1, num2);

            // then
            assertThat(result).isEqualTo(220000005);
            // 결과로 어떤값이 올건지 예측하는것.
            // 제대로 동작 안하는것 확인
            // 성공 되는것 확인
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

            CalculatorService calculatorService = new CalculatorService(calculatorRepository);
            int result = calculatorService.subtract(num1, num2);

            assertThat(result).isEqualTo(15);
        }

        @Test
        @DisplayName("뺄셈 성공 : 음수는 취급 안함")
        void subtractSuccessCase2() {
            int num1 = 5;
            int num2 = 10;

            // 5 - 10 : 5

            CalculatorService calculatorService = new CalculatorService(calculatorRepository);
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


            CalculatorService calculatorService = new CalculatorService(calculatorRepository);
            double result = calculatorService.divide(num1, num2);

            assertThat(result).isEqualTo(2);
        }

        @Test
        @DisplayName("나눗셈 성공: 소숫점 2째자리")
        void divideSuccessCase2() {
            double num1 = 5;
            double num2 = 3;

            CalculatorService calculatorService = new CalculatorService(calculatorRepository);
            double result = calculatorService.divide(num1, num2);

            assertThat(result).isEqualTo(1.67);
        }

        @Test
        @DisplayName("나눗셈 실패: 분모에 0이 있으면 안됨")
        void divideFailCase1() {
            double num1 = 5;
            double num2 = 0;

            CalculatorService calculatorService = new CalculatorService(calculatorRepository);

            assertThatThrownBy(() ->
                    calculatorService.divide(num1, num2)
            ).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("분모에 0이 있으면 안됩니다.");
        }

    }

}
