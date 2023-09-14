package payment.service.razopay.integration.controller;


import com.razorpay.Payment;
import com.razorpay.RazorpayException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import payment.service.razopay.integration.dto.OrderDto;
import payment.service.razopay.integration.dto.PaymentDto;
import payment.service.razopay.integration.service.PaymentService;

@RestController
@RequestMapping("/razorpay/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public PaymentDto create(@RequestBody PaymentDto paymentDto) throws RazorpayException {
        return paymentService.createPayment(paymentDto);
    }
}
