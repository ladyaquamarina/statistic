package statistics.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    ADMIN("Администратор"),
    SUPPORT("Технический работник"),
    USER("Пользователь");

    private final String value;
}
