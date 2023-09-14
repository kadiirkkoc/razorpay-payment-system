package payment.service.razopay.integration.controller;

import com.razorpay.RazorpayException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import payment.service.razopay.integration.dto.PaymentDto;
import payment.service.razopay.integration.dto.TokenDto;
import payment.service.razopay.integration.service.TokenService;

@RestController
@RequestMapping("/razorpay/token")
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/create")
    public TokenDto create(@RequestBody TokenDto tokenDto) throws RazorpayException {
        return tokenService.createToken(tokenDto);
    }
}
