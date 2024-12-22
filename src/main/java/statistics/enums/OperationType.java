package statistics.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OperationType {
    BETWEEN_THIS_USER_BANK_ACCOUNT("Денежный перевод между своими счетами"),
    BETWEEN_INDIVIDUALS("Денежный перевод между физлицами"),
    SALARY("Начисление зарплаты"),
    STIPEND("Начисление пособия или стипендии"),
    BUYING("Покупка"),
    ANOTHER("Другой тип операции");

    private final String value;
}
