package vn.salary.calculator.model.response;

public record TransactionResponse(Long money,
                                  String content,
                                  String type,
                                  String createdAt) {
}
