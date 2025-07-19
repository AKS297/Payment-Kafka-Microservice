package com.micro.transefer.controller;

import com.micro.transefer.model.TransferModel;
import com.micro.transefer.service.TransferService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private TransferService transferService;

    public PaymentController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/send")
    public boolean transferMoney(@RequestBody TransferModel transferModel){
       return transferService.transfer(transferModel);
    }
}
