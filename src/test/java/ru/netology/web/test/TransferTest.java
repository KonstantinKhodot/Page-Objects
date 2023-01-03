package ru.netology.web.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class TransferTest {

    @Test
    void shouldTransferMoneyOnTheFirstCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        int amount = 5000;
        int currentBalanceFirstCard = 0;
        int expected = currentBalanceFirstCard + amount;
        int actual = Integer.parseInt(dashboardPage.getCardBalance(0));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTransferMoneyOnTheSecondCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        int amount = 5000;
        int currentBalanceFirstCard = 10000;
        int currentBalanceSecondCard = 0;
        int expected = currentBalanceSecondCard - amount;
        int actual = Integer.parseInt(dashboardPage.getCardBalance(0));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTransferMoneyMoreThanLimit() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        int amount = 20000;
        int currentBalanceFirstCard = 0;
        int expected = currentBalanceFirstCard + amount;
        int actual = Integer.parseInt(dashboardPage.getCardBalance(0));
        Assertions.assertEquals(expected, actual);
    }
}

