package payment.service.razopay.integration.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import payment.service.razopay.integration.data.Note;
import payment.service.razopay.integration.dto.OrderDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private RazorpayClient client;

    @Value("${razorpay.api.key}")
    private String razorpayKey;

    @Value("${razorpay.api.secretKey}")
    private String razorpaySecretKey;

    public OrderDto createOrder(OrderDto orderDto) throws RazorpayException {
        this.client = new RazorpayClient(razorpayKey,razorpaySecretKey);
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount",orderDto.getAmount());
        orderRequest.put("currency",orderDto.getCurrency());
        orderRequest.put("receipt", orderDto.getReceipt());
//        JSONObject notesObject = new JSONObject();
//        for (Note note : orderDto.getNotes()) {
//            notesObject.put(note.getKey(), note.getValue());
//        }
//        orderRequest.put("notes", notesObject);
        Order order = client.orders.create(orderRequest);
        orderDto.setOrderId(order.get("id").toString());
        return orderDto;
    }

    public List<OrderDto> fetchAllOrders() throws RazorpayException {
        this.client = new RazorpayClient(razorpayKey,razorpaySecretKey);
        Optional<List<Order>> orderList = Optional.ofNullable(client.orders.fetchAll());
        List<OrderDto> orderDtos = new ArrayList<>();
        orderList.get().forEach(order -> {
            OrderDto orderDto = new OrderDto();
            orderDto.setOrderId(order.get("id").toString());
            orderDto.setAmount(Double.parseDouble(order.get("amount").toString()));
            orderDto.setReceipt(order.get("receipt").toString());
            orderDto.setCurrency(order.get("currency").toString());
            orderDtos.add(orderDto);
        });
        return orderDtos;
    }

    //ERROR HERE
    public OrderDto fetchOrderById(String id) throws RazorpayException{
        this.client = new RazorpayClient(razorpayKey,razorpaySecretKey);
        Order order = client.orders.fetch(id);
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.get("id").toString());
        orderDto.setAmount(Double.parseDouble(order.get("amount").toString()));
        orderDto.setReceipt(order.get("receipt").toString());
        orderDto.setCurrency(order.get("currency").toString());
        return orderDto;
    }
    //look at here
    public OrderDto updateOrder(OrderDto orderDto) throws RazorpayException{
        this.client = new RazorpayClient(razorpayKey,razorpaySecretKey);
        JSONObject orderRequest = new JSONObject();
        JSONObject notes = new JSONObject();
        notes.put("notes_1",orderDto.getNotes().get(0));
        orderRequest.put("notes",notes);
        Order updatedOrder = client.orders.edit(orderDto.getOrderId().toString(),orderRequest);;

        OrderDto orderDto1 = new OrderDto();
            return orderDto1;
    }
}
