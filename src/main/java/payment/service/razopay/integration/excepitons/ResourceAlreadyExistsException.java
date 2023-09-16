package payment.service.razopay.integration.excepitons;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResourceAlreadyExistsException extends RuntimeException {
    private String message;
}