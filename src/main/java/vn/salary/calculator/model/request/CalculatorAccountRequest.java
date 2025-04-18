package vn.salary.calculator.model.request;

import vn.salary.calculator.model.entity.Transaction;
import vn.salary.calculator.model.enumeration.Type;

public record CalculatorAccountRequest(Long money, String content, Type type) {

    public static Transaction createTransaction(CalculatorAccountRequest request) {
        Transaction transaction = new Transaction();
        transaction.setMoney(request.getMoney(request));
        transaction.setContent(request.content);
        transaction.setType(request.type);
        return transaction;
    }

    private Long getMoney(CalculatorAccountRequest request) {
        switch (request.type) {
            case PAID, MINUS_FUND -> {
                return -request.money;
            }
            case RECEIVER, BORROW, PLUS_FUND -> {
                return request.money;
            }
        }
        return 0L;
    }
}
