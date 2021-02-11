package  main.newer.util;

import lombok.extern.slf4j.Slf4j;
import main.newer.config.ResultJSON;

import org.apache.shiro.ShiroException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理全局异常
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	/**
	 * 捕捉shiro的异常
	 *
	 * @param e
	 * @return
	 */
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = ShiroException.class)
	public ResultJSON handler(ShiroException e) {
		logger.error("ShiroException：", e);
		return ResultJSON.error( e.getMessage());
	}

	/**
	 * 捕捉UnauthorizedException
	 *
	 * @return
	 */
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnauthorizedException.class)
	public ResultJSON handle401(UnauthorizedException e) {
		logger.error("UnauthorizedException：", e);
		return ResultJSON.error( e.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResultJSON handler(MethodArgumentNotValidException e) {
		logger.error("MethodArgumentNotValidException：", e);
		BindingResult bindingResult = e.getBindingResult();
		ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
		return  ResultJSON.error(objectError.getDefaultMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResultJSON handler(IllegalArgumentException e) {
		logger.error("IllegalArgumentException：", e);
		return  ResultJSON.error(e.getMessage());
	}

	/**
	 * 捕捉其他所有异常
	 *
	 * @param request
	 * @param e
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(RuntimeException.class)
	public ResultJSON globalException(HttpServletRequest request, RuntimeException e) {
		logger.error("运行时异常：", e);
		HttpStatus httpStatus;
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		httpStatus = HttpStatus.valueOf(statusCode);
		logger.info(httpStatus.toString());
		return ResultJSON.error( e.getMessage());
	}
}