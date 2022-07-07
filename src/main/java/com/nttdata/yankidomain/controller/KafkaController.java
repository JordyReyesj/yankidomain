package com.nttdata.yankidomain.controller;

import com.nttdata.yankidomain.producer.KafkaPaymentProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
  private final KafkaPaymentProducer kafkaPaymentProducer;

  @Autowired
  KafkaController(KafkaPaymentProducer kafkaStringProducer) {
    this.kafkaPaymentProducer = kafkaStringProducer;
  }

  @PostMapping(value = "/publish")
  public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
    this.kafkaPaymentProducer.sendMessage(message);
  }
}
