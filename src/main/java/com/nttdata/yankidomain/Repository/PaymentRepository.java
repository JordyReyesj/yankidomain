package com.nttdata.yankidomain.Repository;

import com.nttdata.yankidomain.model.Payment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * PaymentRepository.
 */
public interface PaymentRepository extends ReactiveMongoRepository<Payment, String> {
}
