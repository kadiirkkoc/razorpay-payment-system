package payment.service.razopay.integration.controller;

import com.razorpay.RazorpayException;
import org.springframework.web.bind.annotation.*;
import payment.service.razopay.integration.dto.OrderDto;
import payment.service.razopay.integration.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/razorpay/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getAll")
    public List<OrderDto> getAll() throws RazorpayException {
        return orderService.fetchAllOrders();
    }

    @GetMapping("/getById/{id}")
    public OrderDto getById(@PathVariable("id") String id) throws RazorpayException {
        return orderService.fetchOrderById(id);
    }

    @PostMapping("/create")
    public OrderDto create(@RequestBody OrderDto orderDto) throws RazorpayException {
        return orderService.createOrder(orderDto);
    }

    @PutMapping("/update")
    public OrderDto update(@RequestBody  OrderDto orderDto) throws RazorpayException{
        return orderService.updateOrder(orderDto);
    }
}
