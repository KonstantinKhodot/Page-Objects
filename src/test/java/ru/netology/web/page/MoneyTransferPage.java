package ru.netology.web.page;

import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferPage {

    public DashboardPage transferMoney(DataHelper.TransferCard info, String amount) {
        $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']").setValue(info.getCardNumber());
        $("[data-test-id='action-deposit']").setValue(amount);
        return new DashboardPage();
    }
}
