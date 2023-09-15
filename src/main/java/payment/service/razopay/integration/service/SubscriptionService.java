package payment.service.razopay.integration.service;

import com.razorpay.Plan;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Subscription;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import payment.service.razopay.integration.dto.SubscriptionDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionService {

    private RazorpayClient client;

    @Value("${razorpay.api.key}")
    private String razorpayKey;

    @Value("${razorpay.api.secretKey}")
    private String razorpaySecretKey;

    public SubscriptionDto createPlan(SubscriptionDto subscriptionDto) throws RazorpayException {
        this.client = new RazorpayClient(razorpayKey,razorpaySecretKey);
        JSONObject planRequest = new JSONObject();
        planRequest.put("period",subscriptionDto.getPeriod());
        planRequest.put("interval",subscriptionDto.getInterval());
        JSONObject item = new JSONObject();
        item.put("name",subscriptionDto.getPlanName());
        item.put("amount",subscriptionDto.getAmount());
        item.put("currency","INR");
        item.put("description",subscriptionDto.getDescription());
        planRequest.put("item",item);

        Plan plan = client.plans.create(planRequest);
        subscriptionDto.setPlan_id(plan.get("id").toString());
        return subscriptionDto;
    }

    public SubscriptionDto createSubscription(SubscriptionDto subscriptionDto) throws RazorpayException{
        this.client = new RazorpayClient(razorpayKey,razorpaySecretKey);
        JSONObject subscriptionRequest = new JSONObject();
        subscriptionRequest.put("plan_id", subscriptionDto.getPlan_id());
        subscriptionRequest.put("total_count",subscriptionDto.getTotal_count());
        List<Object> addons = new ArrayList<>();
        JSONObject linesItem = new JSONObject();
        JSONObject item = new JSONObject();
        item.put("name",subscriptionDto.getPlanName());
        item.put("amount",subscriptionDto.getAmount());
        item.put("currency","INR");
        item.put("description",subscriptionDto.getDescription());
        linesItem.put("item",item);
        addons.add(linesItem);
        subscriptionRequest.put("addons",addons);
        Subscription order = client.subscriptions.create(subscriptionRequest);
        subscriptionDto.setSubscription_id(order.get("id").toString());
        return subscriptionDto;
    }
}
