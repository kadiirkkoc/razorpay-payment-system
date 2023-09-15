package payment.service.razopay.integration.service;

import com.razorpay.Customer;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import payment.service.razopay.integration.dto.CustomerDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Configuration
public class CustomerService {

        private RazorpayClient client;

        @Value("${razorpay.api.key}")
        private String razorpayKey;

        @Value("${razorpay.api.secretKey}")
        private String razorpaySecretKey;

    public CustomerDto createCustomer(CustomerDto customerDto) throws RazorpayException {
        this.client = new RazorpayClient(razorpayKey,razorpaySecretKey);
        JSONObject customerRequest = new JSONObject();
        customerRequest.put("name",customerDto.getName());
        customerRequest.put("contact",customerDto.getContact());
        customerRequest.put("email",customerDto.getEmail());
        customerRequest.put("fail_existing",customerDto.getFail_existing());
        customerRequest.put("gstin",customerDto.getGstin());
        JSONObject notes = new JSONObject();
        notes.put("notes_key_1","Tea, Earl Grey, Hot");
        notes.put("notes_key_2","Tea, Earl Grey… decaf.");
        customerRequest.put("notes",notes);
        Customer customer =  client.customers.create(customerRequest);

        CustomerDto customerDto1 = new CustomerDto();
        System.out.println(customer.toString());
        if (customer!=null){
            customerDto1.setCustomerId(customer.get("id").toString());
            customerDto1.setName(customer.get("name").toString());
            customerDto1.setContact(customer.get("contact").toString());
            customerDto1.setEmail(customer.get("email").toString());
            customerDto1.setGstin(customer.get("gstin").toString());
        }
        return customerDto1;
    }

    public List<CustomerDto> fetchAllCustomers() throws RazorpayException {
        this.client = new RazorpayClient(razorpayKey,razorpaySecretKey);
        Optional<List<Customer>> customers = Optional.ofNullable(client.customers.fetchAll());

        List<CustomerDto> customerDtos = new ArrayList<>();
        customers.get().forEach(customer -> {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setCustomerId(customer.get("id").toString());
            customerDtos.add(customerDto);
        });
        return customerDtos;
    }
}