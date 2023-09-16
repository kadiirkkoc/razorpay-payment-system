package payment.service.razopay.integration.controller;


import com.razorpay.RazorpayException;
import org.springframework.web.bind.annotation.*;
import payment.service.razopay.integration.dto.PaymentLinkDto;
import payment.service.razopay.integration.service.PaymentLinkService;

import java.util.List;

@RestController
@RequestMapping("/razorpay/payment")
public class PaymentController {

    private final PaymentLinkService paymentLinkService;

    public PaymentController(PaymentLinkService paymentLinkService) {
        this.paymentLinkService = paymentLinkService;
    }

    @PostMapping("/create")
    public PaymentLinkDto create(@RequestBody PaymentLinkDto paymentLinkDto) throws RazorpayException {
        return paymentLinkService.createPayment(paymentLinkDto);
    }

    @GetMapping("/getAll")
    public List<PaymentLinkDto> getAll() throws RazorpayException {
        return paymentLinkService.fetchAllPaymentLinks();
    }

    @GetMapping("/getById/{id}")
    public PaymentLinkDto getById(@PathVariable("id") String id) throws RazorpayException {
        return paymentLinkService.fetchPaymentLinkById(id);
    }
}
