package com.diferent.springsandbox.domain.errors;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ServiceException extends RuntimeException {

    private final String code;
    private final HttpStatus httpStatus;
    private final Map<String, String> params;

    private ServiceException(final String code, final HttpStatus httpStatus, final String message, final Throwable cause, final Map<String, String> params) {
        super(message, cause);
        this.code = code;
        this.httpStatus = httpStatus;
        this.params = params;
    }

    public static Builder builder(final String code) {
        return new Builder(code);
    }

    public String getCode() {
        return this.code;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public Map<String, String> getParams() {
        return this.params;
    }

    public static class Builder {
        private final String code;
        private String message;
        private HttpStatus httpStatus;
        private Throwable cause;
        private Map<String, String> params;

        public Builder(final String code) {
            this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            this.code = code;
        }

        public Builder withMessage(final String message) {
            this.message = message;
            return this;
        }

        public Builder withHttpStatus(final HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public Builder withCause(final Throwable cause) {
            this.cause = cause;
            return this;
        }

        public Builder withParams(final Map<String, String> params) {
            this.params = params;
            return this;
        }

        public Builder addParam(final String key, final String value) {
            if (this.params == null) {
                this.params = new HashMap();
            }

            this.params.put(key, value);
            return this;
        }

        public ServiceException build() {
            return new ServiceException(this.code, this.httpStatus, this.message, this.cause, this.params);
        }
    }
}
