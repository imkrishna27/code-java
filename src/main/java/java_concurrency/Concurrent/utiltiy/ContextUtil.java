package java_concurrency.Concurrent.utiltiy;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ContextUtil {
    private final String name;
    private final Integer id;

    public ContextUtil(String name, Integer id) {
        this.name = name;
        this.id = id;
    }
}
