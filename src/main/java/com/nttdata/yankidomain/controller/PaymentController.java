package com.nttdata.yankidomain.controller;

import com.nttdata.yankidomain.exception.ErrorSummary;
import com.nttdata.yankidomain.model.Payment;
import com.nttdata.yankidomain.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Payment Controller.
 */
@RestController
@RequestMapping("/api/yanki/payment")
public class PaymentController {

  private final Logger log = LoggerFactory.getLogger(PaymentController.class);

  private final PaymentService paymentService;

  public PaymentController(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  /**
   * FindAll Payment.
   */
  @GetMapping
  public Mono<ResponseEntity<Flux<Payment>>> findAll() {
    log.info("PaymentController findAll ->");
    return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
            .body(paymentService.findAll()));
  }

  /**
   * Find Payment.
   */
  @GetMapping("/{id}")
  public Mono<ResponseEntity<Payment>> findById(@PathVariable String id) {
    log.info("PaymentController findById ->");
    return paymentService.findById(id)
            .map(ce -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(ce))
            .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  /**
   * create Payment.
   */
  @PostMapping
  public Mono<ResponseEntity<Map<String, Object>>> create(
          @Valid @RequestBody Mono<Payment> paymentMono) {
    log.info("PaymentController create ->");
    Map<String, Object> result = new HashMap<>();
    return paymentMono.flatMap(c -> paymentService.create(c)
                    .map(p -> ResponseEntity.created(URI.create("/api/yanki/payment/".concat(p.getId())))
                            .contentType(MediaType.APPLICATION_JSON).body(result)))
            .onErrorResume(ErrorSummary::errorSummaryException);
  }

  /**
   * Delete Payment.
   */
  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
    log.info("PaymentController delete ->");
    return paymentService.findById(id)
            .flatMap(e -> paymentService.delete(e)
                    .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))))
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}
