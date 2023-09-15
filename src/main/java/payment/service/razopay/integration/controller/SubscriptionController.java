package payment.service.razopay.integration.controller;

import com.razorpay.RazorpayException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import payment.service.razopay.integration.dto.SubscriptionDto;
import payment.service.razopay.integration.service.SubscriptionService;

@RestController
@RequestMapping("/razorpay/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/create/plan")
    public SubscriptionDto createPlan(@RequestBody SubscriptionDto subscriptionDto) throws RazorpayException {
        return subscriptionService.createPlan(subscriptionDto);
    }

    @PostMapping("/create/subscription")
    public SubscriptionDto createSubscription(@RequestBody SubscriptionDto subscriptionDto) throws RazorpayException {
        return subscriptionService.createSubscription(subscriptionDto);
    }
}
