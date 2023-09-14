package org.example.v2;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class Currency {
    private static final String DELIMITER = ",";
    private String code;
    private String name;
    private String cashBuying;
    private String telegraphicBuying;
    private String selling;

    public Currency(String code, String cashBuying, String telegraphicBuying, String selling) {
        this.code = code;
        this.name = java.util.Currency.getInstance(code).getDisplayName();
        this.cashBuying = cashBuying;
        this.telegraphicBuying = telegraphicBuying;
        this.selling = selling;
    }

    @Override
    public String toString() {
        return code + DELIMITER + name + DELIMITER + cashBuying + DELIMITER + telegraphicBuying + DELIMITER + selling;
    }

}
