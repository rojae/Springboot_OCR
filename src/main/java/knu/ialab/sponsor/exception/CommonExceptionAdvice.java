/*
package knu.ialab.sponsor.exception;

import knu.ialab.sponsor.common.ApiMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionAdvice {
	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);

	@ExceptionHandler(Exception.class)
	public Object common(Exception e) {
		return ApiMsg.builder().code(500).msg("오류가 발생했습니다").msg("입력 값을 확인해주세요").build();
	}
}*/
