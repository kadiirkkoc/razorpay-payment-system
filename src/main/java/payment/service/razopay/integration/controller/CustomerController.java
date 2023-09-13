package payment.service.razopay.integration.controller;

import com.razorpay.Customer;
import com.razorpay.RazorpayException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import payment.service.razopay.integration.dto.CustomerDto;
import payment.service.razopay.integration.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/razorpay/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getAll")
    public List<CustomerDto> getAll() throws RazorpayException {
        return customerService.fetchAllCustomers();
    }

    @PostMapping("/create")
    public CustomerDto create(CustomerDto customerDto) throws RazorpayException {
        return customerService.createCustomer(customerDto);
    }
}
