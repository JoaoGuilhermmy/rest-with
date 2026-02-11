package com.joaoguilhermmy.exception;

import java.util.Date;

public record ExceptionResponse(Date timeStemp, String message, String details) {

}
