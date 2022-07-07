package com.nttdata.yankidomain.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;
/**
* KafkaConsumerConfig.
*/
@Configuration
public class KafkaPaymentConfig {
  /**
   * KafkaChangeRateConfig.
   */
  @SuppressWarnings("unchecked")
  public ProducerFactory<String, String> paymentFactory() {
    Map<String, Object> config = new HashMap<>();
    config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
    config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    return new DefaultKafkaProducerFactory<>(config);
  }

  /**
   * kafkaTemplate.
   */
  @Bean(name = "KafkaProducerTemplate")
  public KafkaTemplate<String, String> kafkaTemplate() {
    return new KafkaTemplate<>(paymentFactory());
  }
}
