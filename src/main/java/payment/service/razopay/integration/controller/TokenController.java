package payment.service.razopay.integration.controller;

import com.razorpay.RazorpayException;
import org.springframework.web.bind.annotation.*;
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

//    @GetMapping("/get")
//    public TokenDto getCardDetailsFromToken(@RequestBody TokenDto tokenDto) throws RazorpayException{
//        return tokenService.fetchCardPropertiesFromToken(tokenDto);
//    }
}
