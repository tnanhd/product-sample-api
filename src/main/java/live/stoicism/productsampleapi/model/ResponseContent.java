package live.stoicism.productsampleapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ResponseContent<T> {
	public static final String STATUS_SUCCESS = "success";
	public static final String STATUS_ERROR = "error";
	public static final String STATUS_FAIL = "fail";

	private String status;
	private T data;
	private String message;

	public ResponseContent(String status, T data) {
		this(status, data, null);
	}

	public ResponseContent(String status, String message) {
		this(status, null, message);
	}

	public ResponseContent(String status, T data, String message) {
		this.status = status;
		this.data = data;
		this.message = message;
	}

	private static ResponseContentBuilder status(String status) {
		return new ResponseContentBuilder(status);
	}

	public static ResponseContentBuilder success() {
		return status(STATUS_SUCCESS);
	}

	public static ResponseContentBuilder fail() {
		return status(STATUS_FAIL);
	}

	public static ResponseContentBuilder error() {
		return status(STATUS_ERROR);
	}

	public static class ResponseContentBuilder {
		private final String status;
		private String message;

		public ResponseContentBuilder(String status) {
			this.status = status;
		}

		public ResponseContentBuilder message(String message) {
			this.message = message;
			return this;
		}

		public <T> ResponseContent<T> build() {
			return data(null);
		}

		public <T> ResponseContent<T> data(T data) {
			return new ResponseContent<>(this.status, data, message);
		}
	}
}
