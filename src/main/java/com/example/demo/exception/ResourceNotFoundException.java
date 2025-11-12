package com.example.demo.exception;

/**
 * 리소스(데이터베이스 항목, 파일 등)를 찾을 수 없을 때 발생하는
 * 사용자 정의 런타임 예외 클래스입니다.
 * 이 예외는 보통 HTTP 404 Not Found 응답에 매핑됩니다.
 */
public class ResourceNotFoundException extends RuntimeException {

    // 💡 필수: String 메시지를 인자로 받는 생성자
    public ResourceNotFoundException(String message) {
        super(message); 
    }

    // 옵션: 메시지와 함께 예외의 원인(cause)을 받는 생성자
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
