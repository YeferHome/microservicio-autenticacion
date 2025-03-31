package org.microservicio__plazoleta.infrastructure.exception;

 public class BusinessException extends RuntimeException {
        public BusinessException(String message) {
            super(message);
        }
    }


