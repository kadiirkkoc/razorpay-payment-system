package payment.service.razopay.integration.controller;

import com.razorpay.Customer;
import com.razorpay.RazorpayException;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/getById/{id}")
    public CustomerDto getAll(@PathVariable("id") String id) throws RazorpayException {
        return customerService.fetchCustomerById(id);
    }

    @PostMapping("/create")
    public CustomerDto create(@RequestBody  CustomerDto customerDto) throws RazorpayException {
        return customerService.createCustomer(customerDto);
    }

    @PutMapping
    public CustomerDto update(@RequestBody  CustomerDto customerDto) throws RazorpayException {
        return customerService.updateCustomer(customerDto);
    }
}
