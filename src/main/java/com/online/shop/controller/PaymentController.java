package com.online.shop.controller;

import com.online.shop.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class PaymentController {

    @Autowired
    private Payment payment;

    @RequestMapping("/getPrice")
    @ResponseBody
    public Double getPrice() {
        return payment.getPrice();
    }
}
