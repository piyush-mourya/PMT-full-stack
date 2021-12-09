package io.piyush.pmt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProjectIdException extends RuntimeException {

	public ProjectIdException(String message) {
		super(message);
	}

}