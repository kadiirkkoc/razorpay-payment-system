package payment.service.razopay.integration.config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${razorpay.api.key}")
    private String RAZORPAY_KEY;

    @Value("${razorpay.api.secretKey}")
    private String RAZORPAY_SECRET_KEY;

    @Bean
    @Qualifier("RazorpayClient")
    public RazorpayClient getRazorpayClient() throws RazorpayException {
        return new RazorpayClient(RAZORPAY_KEY,RAZORPAY_SECRET_KEY);
    }
}
