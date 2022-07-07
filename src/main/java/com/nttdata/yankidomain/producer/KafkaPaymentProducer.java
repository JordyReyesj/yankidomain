package com.nttdata.yankidomain.producer;

import com.nttdata.yankidomain.consumer.KafkaPaymentConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * KafkaChangeRateProducer.
 */
@Component
public class KafkaPaymentProducer {
  private static final Logger LOGGER= LoggerFactory.getLogger(KafkaPaymentConsumer.class);

  private final KafkaTemplate<String, String> kafkaTemplate;

  /**
   * KafkaChangeRateProducer.
   */
  public KafkaPaymentProducer(@Qualifier("KafkaProducerTemplate") KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate= kafkaTemplate;
  }

  /**
   * sendMessage.
   */
  public void sendMessage(String message){
    LOGGER.info("Producing message {}",message);
    this.kafkaTemplate.send("TOPIC-DEMO",message);
  }
}
