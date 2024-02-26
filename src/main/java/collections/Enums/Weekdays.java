package collections.Enums;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public enum Weekdays {

    MONDAY("monday","1"),
    TUESDAY("tuesday","2");

    @Getter
    private String key;

    @Getter
    private String value;

    Weekdays(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public void show() {
        System.out.println("you are inside show method of enum weekdays");
    }
}
