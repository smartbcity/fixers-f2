package f2.spring.exception

import org.springframework.http.HttpStatus

class ForbiddenAccessException(
    action: String
): F2Exception(
    status = HttpStatus.FORBIDDEN,
    message = "You are not authorized to $action",
    cause = null
)
