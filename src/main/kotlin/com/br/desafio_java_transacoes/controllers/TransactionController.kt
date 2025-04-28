package com.br.desafio_java_transacoes.controllers

import com.br.desafio_java_transacoes.model.request.TransactionRequest
import com.br.desafio_java_transacoes.model.response.StatisticsResponse
import com.br.desafio_java_transacoes.services.TransactionService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class TransactionController(val transactionService : TransactionService) {

    private val logger: Logger = LoggerFactory.getLogger(TransactionController::class.java)

    @PostMapping("/transaction")
    fun createTransaction(@RequestBody transactionRequest : TransactionRequest) : ResponseEntity<Unit> {
        logger.info("Creating transaction with value ${transactionRequest.value}")

        transactionService.createTransaction(transactionRequest)

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @DeleteMapping("/transaction")
    fun deleteAllTransactions() : ResponseEntity<Unit> {
        logger.info("Deleting all transactions")
        transactionService.deleteAllTransactions()
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @GetMapping("/statistics/{seconds}")
    fun getStatistics(@PathVariable seconds : Int) : ResponseEntity<StatisticsResponse> {
        logger.info("Getting statistics all transactions from the last $seconds seconds")
        return ResponseEntity.ok(transactionService.getStatistics(seconds))
    }

}