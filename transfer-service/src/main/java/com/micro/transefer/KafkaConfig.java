package com.micro.transefer;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Value("withdraw-money-topic")
    private String withdrawTopicName;

    @Value("deposit-money-topic")
    private String depositTopicName;

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.producer.key-serializer}")
    private String keySerializer;

    @Value("${spring.kafka.producer.value-serializer}")
    private String valueSerializer;

    @Value("${spring.kafka.producer.acks}")
    private String acks;

    @Value("${spring.kafka.producer.properties.delivery.timeout.ms}")
    private String deliveryTimeout;

    @Value("${spring.kafka.producer.properties.linger.ms}")
    private String linger;

    @Value("${spring.kafka.producer.properties.request.timeout.ms}")
    private String requestTimeout;

    @Value("${spring.kafka.producer.properties.enable.idempotence}")
    private boolean idempotence;

    @Value("${spring.kafka.producer.properties.max.in.flight.requests.per.connection}")
    private int inflightRequests;

    public Map<String,Object> producerConfig(){
       Map<String,Object> props = new HashMap<>();
         props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
         props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,keySerializer);
         props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,valueSerializer);
         props.put(ProducerConfig.ACKS_CONFIG,acks);

         return props;
    }

    @Bean
    public ProducerFactory<String,Object> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String,Object> kafkaTemplate(){
        return new KafkaTemplate<String,Object>(producerFactory());
    }
    @Bean
    NewTopic createWithDrawTopic(){
        return TopicBuilder.name(withdrawTopicName)
                .partitions(3)
                .replicas(3)
                .build();
    }
    @Bean
    NewTopic createDepositTopic(){
        return TopicBuilder.name(depositTopicName)
                .partitions(3)
                .replicas(3)
                .build();
    }

}
