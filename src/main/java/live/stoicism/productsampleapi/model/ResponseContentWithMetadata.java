package live.stoicism.productsampleapi.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseContentWithMetadata<T> extends ResponseContent<T> {

    @AllArgsConstructor
    @Getter
    public static class Metadata {
        private int pageNumber;
        private int pageSize;
        private long total;
    }

    private Metadata metadata;

    public ResponseContentWithMetadata(String status, T data, String message, Metadata metadata) {
        super(status, data, message);
        this.metadata = metadata;
    }
}
