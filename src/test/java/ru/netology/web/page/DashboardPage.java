package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private SelenideElement heading = $("[data-test-id=dashboard]");

    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public String getCardBalance(int index) {
        return $$("ul li[class=list__item] div").get(index).getOwnText();
    }

    public void addMoneyToCard(int index) {
        $$("ul li[class=list__item] div button").get(index).click();
    }

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public List<String> getCards() {
        ElementsCollection cardElements = $$("ul li[class=list__item] div");
        List<String> cards = new ArrayList<>();

        for (SelenideElement card : cardElements) {
            cards.add(card.getAttribute("data-test-id"));
        }

        return cards;
    }
}
