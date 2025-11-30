package weather.ExceptionHandling;





import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import java.time.Instant;

/**
 * Global exception handler – Spring Boot idiom.
 * Keeps controllers clean and returns consistent RFC 7807 JSON.
 */

@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {


    /* ---------- 1. Custom typed exceptions -------------------------- */

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String msg) { super(msg); }
    }

    public static class BadRequestException extends RuntimeException {
        public BadRequestException(String msg) { super(msg); }
    }

    /* ---------- 2. Exception -> ProblemDetail mappings ------------- */

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleNotFound(ResourceNotFoundException ex, WebRequest req) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Resource not found");
        problem.setDetail(ex.getMessage());
        problem.setProperty("timestamp", Instant.now());
        problem.setProperty("path", req.getDescription(false));
        return problem;
    }

    @ExceptionHandler(BadRequestException.class)
    public ProblemDetail handleBadRequest(BadRequestException ex, WebRequest req) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Bad request");
        problem.setDetail(ex.getMessage());
        problem.setProperty("timestamp", Instant.now());
        problem.setProperty("path", req.getDescription(false));
        return problem;
    }

    /* ---------- 3. Fallback – any other exception ------------------ */

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleAll(Exception ex, WebRequest req) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problem.setTitle("Internal server error");
        problem.setDetail(ex.getMessage());
        problem.setProperty("timestamp", Instant.now());
        problem.setProperty("path", req.getDescription(false));
        return problem;
    }

}
