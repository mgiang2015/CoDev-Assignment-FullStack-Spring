package app.backend;

import app.backend.error.EmptyResponseException;
import app.backend.error.InvalidParameterException;
import app.backend.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(InvalidParameterException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleInvalidParamError(InvalidParameterException ex) {
        return new ErrorResponse(ex.message);
    }

    @ExceptionHandler(EmptyResponseException.class)
    @ResponseBody
    public ErrorResponse handleEmptyResponseError(EmptyResponseException ex) {
        return new ErrorResponse(ex.message);
    }

}
