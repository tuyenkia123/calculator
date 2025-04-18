package vn.salary.calculator.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {

    private Integer year;
    private Integer month;
    private String type;
    private Integer page = 0;
    private Integer size = 10;
}
