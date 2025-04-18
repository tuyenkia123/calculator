package vn.salary.calculator.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.salary.calculator.model.enumeration.Type;
import vn.salary.calculator.model.request.CalculatorAccountRequest;
import vn.salary.calculator.model.request.TransactionRequest;
import vn.salary.calculator.model.response.TransactionResponse;
import vn.salary.calculator.repository.TransactionRepository;
import vn.salary.calculator.service.TransactionService;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void saveAll(List<CalculatorAccountRequest> requests) {
        var transactions = requests
                .stream()
                .map(CalculatorAccountRequest::createTransaction)
                .toList();
        transactionRepository.saveAll(transactions);
    }

    @Override
    public Page<TransactionResponse> queryTransactionAccount(TransactionRequest request) {
        var type = StringUtils.hasText(request.getType()) ?
                List.of(Type.valueOf(request.getType())) :
                Arrays.stream(Type.account()).toList();
        var pageable = PageRequest.of(request.getPage(), request.getSize());
        var page = transactionRepository.findByCreatedAtOrOptionalType(request.getYear(),
                request.getMonth(),
                type, pageable);
        var result = page.getContent()
                .stream()
                .map(t -> {
                    var money = t.getMoney() < 0 ? -t.getMoney() : t.getMoney();
                    return new TransactionResponse(money, t.getContent(),
                            t.getType().getValue(), formatter.format(t.getCreatedAt()));
                })
                .toList();
        return new PageImpl<>(result, pageable, page.getTotalElements());
    }

    @Override
    public Long sumTransactionAccount(TransactionRequest request, List<Type> type) {
        var sum = transactionRepository.sumByCreatedAtAndType(request.getYear(),
                request.getMonth(),
                type);
        if (sum == null) {
            return 0L;
        }
        return sum;
    }

    @Override
    public Page<TransactionResponse> queryTransactionFund(TransactionRequest request) {
        var type = StringUtils.hasText(request.getType()) ?
                List.of(Type.valueOf(request.getType())) :
                Arrays.stream(Type.fund()).toList();
        var pageable = PageRequest.of(request.getPage(), request.getSize());
        var page = transactionRepository.findByCreatedAtOrOptionalListType(type, pageable);
        var result = page.getContent()
                .stream()
                .map(t -> {
                    var money = t.getMoney() < 0 ? -t.getMoney() : t.getMoney();
                    return new TransactionResponse(money, t.getContent(),
                            t.getType().getValue(), formatter.format(t.getCreatedAt()));
                })
                .toList();
        return new PageImpl<>(result, pageable, page.getTotalElements());
    }
}
