package com.br.desafio_java_transacoes.controllers

import com.br.desafio_java_transacoes.model.request.TransactionRequest
import com.br.desafio_java_transacoes.model.response.StatisticsResponse
import com.br.desafio_java_transacoes.services.TransactionService
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

    @PostMapping("/transaction")
    fun createTransaction(@RequestBody transactionRequest : TransactionRequest) : ResponseEntity<Unit> {
        if (transactionRequest.value == null) return ResponseEntity.status(HttpStatusCode.valueOf(422)).build()
        if (transactionRequest.value <= 0.0) return ResponseEntity.status(HttpStatusCode.valueOf(422)).build()
        transactionService.createTransaction(transactionRequest)

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @DeleteMapping("/transaction")
    fun deleteAllTransactions() : ResponseEntity<Unit>{
        transactionService.deleteAllTransactions()
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @GetMapping("/statistics/{seconds}")
    fun getStatistics(@PathVariable seconds : Int) : ResponseEntity<StatisticsResponse>{
        return ResponseEntity.ok(transactionService.getStatistics(seconds))
    }

}