package payment.service.razopay.integration.service;

import com.razorpay.Customer;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Token;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import payment.service.razopay.integration.dto.CustomerDto;
import payment.service.razopay.integration.dto.TokenDto;

@Service
public class TokenService {

    private RazorpayClient client;

    @Value("${razorpay.api.key}")
    private String razorpayKey;

    @Value("${razorpay.api.secretKey}")
    private String razorpaySecretKey;

    public TokenDto createToken(TokenDto tokenDto) throws RazorpayException {
        this.client = new RazorpayClient(razorpayKey,razorpaySecretKey);
        JSONObject tokenRequest = new JSONObject();

        tokenRequest.put("customer_id",tokenDto.getCustomer_id());
        tokenRequest.put("method","card");

        JSONObject card = new JSONObject();
        card.put("number",tokenDto.getNumber());
        card.put("cvv",tokenDto.getCvv());
        card.put("expiry_month",tokenDto.getExpiry_month());
        card.put("expiry_year",tokenDto.getExpiry_year());
        card.put("name",tokenDto.getCardholder());

        tokenRequest.put("card",card);

        Token token = client.token.create(tokenRequest);
        tokenDto.setToken_id(token.get("id").toString());

        return tokenDto;
    }

//    public TokenDto fetchCardPropertiesFromToken(TokenDto tokenDto) throws RazorpayException {
//        JSONObject productRequest = new JSONObject();
//        productRequest.put("id",tokenDto.getToken_id());
//        Token token = client.token.fetch(productRequest);
//        tokenDto.setToken_id(token.get("id"));
//        return token;
//    }
}
