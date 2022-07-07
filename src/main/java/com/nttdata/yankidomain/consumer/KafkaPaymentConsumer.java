package com.nttdata.yankidomain.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * KafkaChangeRateConsumer.
 */
@Component
public class KafkaPaymentConsumer {
  /**
   * Kafka logger.
   */
  Logger logger = LoggerFactory.getLogger(KafkaPaymentConsumer.class);

  /**
   * kafkaListener.
   */
  @KafkaListener(topics="TOPIC-DEMO", groupId = "group_id")
  public void consumer(String message){
    logger.info("Consuming Message {}"+ message);

  }
}
