package com.cedaniel200.practice.console;

import com.cedaniel200.practice.domain.email.EmailDomain;
import com.cedaniel200.practice.domain.user.UserDomain;
import com.cedaniel200.practice.exception.MalformedDataException;
import com.cedaniel200.practice.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ShellCommandsE2ETest {

    @Autowired
    private CalculatorCommands calculatorCommands;

    @Autowired
    private GreetingCommands greetingCommands;

    @Autowired
    private UserCommands userCommands;

    @Autowired
    private EmailCommands emailCommands;

    @Autowired
    private UserDomain userDomain;

    @Autowired
    private EmailDomain emailDomain;

    @BeforeEach
    void setUp() throws Exception {
        var mockUser = new User();
        mockUser.setId(1);
        mockUser.setName("Leanne Graham");
        mockUser.setUsername("Bret");
        mockUser.setEmail("Sincere@april.biz");
        mockUser.setPhone("1-770-736-8031 x56442");
        mockUser.setWebsite("hildegard.org");

        reset(userDomain, emailDomain);
        when(userDomain.list()).thenReturn(List.of(mockUser));
        when(userDomain.findById(anyInt())).thenReturn(mockUser);
        when(emailDomain.sendMail(any())).thenReturn(true);
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        @Primary
        UserDomain userDomain() {
            return mock(UserDomain.class);
        }

        @Bean
        @Primary
        EmailDomain emailDomain() {
            return mock(EmailDomain.class);
        }
    }

    @Test
    void add_shouldReturnSum() {
        assertThat(calculatorCommands.add(5, 3))
                .isEqualTo("the result of the sum is: 8");
    }

    @Test
    void subtract_shouldReturnDifference() {
        assertThat(calculatorCommands.subtract(10, 4))
                .isEqualTo("the result of the subtract is: 6");
    }

    @Test
    void multiply_shouldReturnProduct() {
        assertThat(calculatorCommands.multiply(3, 7))
                .isEqualTo("the result of the multiply is: 21");
    }

    @Test
    void divide_shouldReturnQuotient() {
        assertThat(calculatorCommands.divide(10, 2))
                .isEqualTo("the result of the divide is: 5");
    }

    @Test
    void divideByZero_shouldThrowException() {
        org.junit.jupiter.api.Assertions.assertThrows(
                ArithmeticException.class,
                () -> calculatorCommands.divide(5, 0));
    }

    @Test
    void greetInSpanish_shouldReturnHola() {
        assertThat(greetingCommands.greet("es")).isEqualTo("Hola");
    }

    @Test
    void greetInEnglish_shouldReturnHello() {
        assertThat(greetingCommands.greet("en")).isEqualTo("Hello");
    }

    @Test
    void greetInPortuguese_shouldReturnOla() {
        assertThat(greetingCommands.greet("pt")).isEqualTo("Ola");
    }

    @Test
    void greetUnsupportedLanguage_shouldReturnMessage() {
        assertThat(greetingCommands.greet("fr")).isEqualTo("unsupported language");
    }

    @Test
    void sendEmail_shouldReturnSuccessMessage() {
        assertThat(emailCommands.send("a@b.com", "Hi", "Hello"))
                .isEqualTo("We tried to send your email, we hope you have luck");
    }

    @Test
    void sendEmail_whenFails_shouldReturnFailureMessage() {
        when(emailDomain.sendMail(any())).thenReturn(false);
        assertThat(emailCommands.send("a@b.com", "Hi", "Hello"))
                .isEqualTo("Failed to send email. Check the README for more information");
    }

    @Test
    void listUsers_shouldReturnFormattedList() {
        String result = userCommands.list();
        assertThat(result)
                .contains("User{")
                .contains("Leanne Graham");
    }

    @Test
    void findById_shouldReturnUser() {
        String result = userCommands.findById(1);
        assertThat(result)
                .contains("User{")
                .contains("Leanne Graham");
    }

    @Test
    void findById_negativeId_shouldReturnErrorMessage() throws Exception {
        when(userDomain.findById(-1))
                .thenThrow(new MalformedDataException(
                        "The parameter is malformed: The id can't be less than zero"));
        String result = userCommands.findById(-1);
        assertThat(result).contains("please try again");
    }
}
