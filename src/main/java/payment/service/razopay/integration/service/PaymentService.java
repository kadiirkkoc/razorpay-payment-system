package payment.service.razopay.integration.service;

import com.razorpay.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import payment.service.razopay.integration.dto.OrderDto;
import payment.service.razopay.integration.dto.PaymentDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    private RazorpayClient client;

    @Value("${razorpay.api.key}")
    private String razorpayKey;

    @Value("${razorpay.api.secretKey}")
    private String razorpaySecretKey;

    public PaymentDto createPayment(PaymentDto paymentDto) throws RazorpayException {
        this.client = new RazorpayClient(razorpayKey,razorpaySecretKey);

        Customer customer1 = client.customers.fetch(paymentDto.getCustomer_id());
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",1000);
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("description","Payment for Archi's academy");

        JSONObject customer = new JSONObject();
        customer.put("name",customer1.get("name").toString());
        customer.put("contact",customer1.get("contact").toString());
        customer.put("email",customer1.get("email").toString());

        paymentLinkRequest.put("customer",customer);

        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);

        JSONObject notes = new JSONObject();
        notes.put("policy_name","Jeevan Bima");
        paymentLinkRequest.put("notes",notes);

        paymentLinkRequest.put("callback_url","https://example-callback-url.com/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink paymentLink = client.paymentLink.create(paymentLinkRequest);
        paymentDto.setPayment_id(paymentLink.get("id").toString());
        paymentDto.setCustomer_id(customer1.get("id").toString());
        return paymentDto;

    }

    public List<PaymentDto> fetchAllPaymentLinks() throws RazorpayException {
        this.client = new RazorpayClient(razorpayKey,razorpaySecretKey);
        Optional<List<PaymentLink>> paymentLinks = Optional.ofNullable(client.paymentLink.fetchAll());
        List<PaymentDto> paymentDtos = new ArrayList<>();
        paymentLinks.get().forEach(paymentLink -> {
            PaymentDto paymentDto = new PaymentDto();
            paymentDto.setPayment_id(paymentLink.get("id"));
            paymentDto.setOrder_id(paymentLink.get("order_id"));
            paymentDtos.add(paymentDto);

        });
        return paymentDtos;
    }

    public PaymentDto fetchPaymentLinkById(String id) throws RazorpayException {
        this.client = new RazorpayClient(razorpayKey,razorpaySecretKey);
        PaymentLink paymentLink = client.paymentLink.fetch(id);
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPayment_id(paymentLink.get("id").toString());
        return paymentDto;
    }
}
