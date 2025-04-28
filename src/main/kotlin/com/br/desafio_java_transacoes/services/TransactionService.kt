package com.br.desafio_java_transacoes.services

import com.br.desafio_java_transacoes.model.dto.TransactionDto
import com.br.desafio_java_transacoes.model.request.TransactionRequest
import com.br.desafio_java_transacoes.model.response.StatisticsResponse
import com.br.desafio_java_transacoes.model.response.TransactionResponse
import com.br.desafio_java_transacoes.repositories.TransactionRepository
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import kotlin.time.Duration.Companion.seconds

@Service
class TransactionService(val transactionRepository : TransactionRepository) {

    fun createTransaction(request: TransactionRequest) : TransactionResponse {
        return transactionRepository.createTransaction(request)
    }

    fun deleteAllTransactions() {
        return transactionRepository.deleteAllTransactions()
    }

    fun getStatistics(seconds : Int, now: ZonedDateTime = ZonedDateTime.now()) : StatisticsResponse {

        val transactionsOccurredInPeriod = transactionRepository.getAllTransactions()
            .filter { it.date.isAfter(now.minusSeconds(seconds.toLong())) }

        if (transactionsOccurredInPeriod.isEmpty()) {
            return StatisticsResponse(0, 0.0, 0.0, 0.0, 0.0)
        }

        transactionsOccurredInPeriod
            .toMutableList()
            .sortWith(compareBy { it.value })

        val min = transactionsOccurredInPeriod.first()
        val max = transactionsOccurredInPeriod.last()
        val count = transactionsOccurredInPeriod.size

        val sum = transactionsOccurredInPeriod.reduceOrNull {acc, dto ->
            acc.copy(value = (acc.value ?: 0.0) + (dto.value ?: 0.0))
        }

        val avg = sum?.value?.div(transactionsOccurredInPeriod.size)

        return StatisticsResponse(count, sum?.value, avg, min = min.value, max = max.value)
    }
}
