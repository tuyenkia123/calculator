package vn.salary.calculator.service;

import org.springframework.data.domain.Page;
import vn.salary.calculator.model.enumeration.Type;
import vn.salary.calculator.model.request.CalculatorAccountRequest;
import vn.salary.calculator.model.request.TransactionRequest;
import vn.salary.calculator.model.response.TransactionResponse;

import java.util.List;

public interface TransactionService {

    void saveAll(List<CalculatorAccountRequest> requests);

    Page<TransactionResponse> queryTransactionAccount(TransactionRequest request);

    Long sumTransactionAccount(TransactionRequest request, List<Type> type);

    Page<TransactionResponse> queryTransactionFund(TransactionRequest request);
}
