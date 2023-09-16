package payment.service.razopay.integration.controller;


import com.razorpay.Payment;
import com.razorpay.RazorpayException;
import org.springframework.web.bind.annotation.*;
import payment.service.razopay.integration.dto.OrderDto;
import payment.service.razopay.integration.dto.PaymentDto;
import payment.service.razopay.integration.service.PaymentService;

import java.util.List;

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

    @GetMapping("/getAll")
    public List<PaymentDto> getAll() throws RazorpayException {
        return paymentService.fetchAllPaymentLinks();
    }

    @GetMapping("/getById/{id}")
    public PaymentDto getById(@PathVariable("id") String id) throws RazorpayException {
        return paymentService.fetchPaymentLinkById(id);
    }
}
