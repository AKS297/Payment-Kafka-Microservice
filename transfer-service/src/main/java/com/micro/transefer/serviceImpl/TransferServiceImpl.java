package com.micro.transefer.serviceImpl;

import com.micro.core.events.DepositRequestedEvent;
import com.micro.core.events.WithdrawalRequestEvent;
import com.micro.transefer.model.TransferModel;
import com.micro.transefer.service.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransferServiceImpl implements TransferService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private KafkaTemplate<String,Object> kafkaTemplate;
    private RestTemplate restTemplate;
    private Environment environment;

    public TransferServiceImpl(KafkaTemplate<String, Object> kafkaTemplate, RestTemplate restTemplate, Environment environment) {
        this.kafkaTemplate = kafkaTemplate;
        this.restTemplate = restTemplate;
        this.environment = environment;
    }

    @Override
    public boolean transfer(TransferModel productPaymentModel) {
        WithdrawalRequestEvent withdrawalRequestEvent = new WithdrawalRequestEvent(productPaymentModel.getSenderId()
        , productPaymentModel.getRecepeintId(), productPaymentModel.getAmount());

        DepositRequestedEvent depositRequestedEvent = new DepositRequestedEvent(productPaymentModel.getSenderId(),
                productPaymentModel.getRecepeintId(), productPaymentModel.getAmount());

        try{
            kafkaTemplate.send(environment.getProperty("withdraw-money-topic","withdraw-money-topic"),withdrawalRequestEvent);
            logger.info("Witdrawal request is sent to kafka");

            kafkaTemplate.send(environment.getProperty("deposity-money-topic","depositMoney-topic"),depositRequestedEvent);
            logger.info("Deposit request is sent to kafka");
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new RuntimeException(e);
        }
        return true;
    }
}
