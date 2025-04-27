package com.br.desafio_java_transacoes.repositories

import com.br.desafio_java_transacoes.model.dto.TransactionDto
import com.br.desafio_java_transacoes.model.entities.TransactionEntity
import com.br.desafio_java_transacoes.model.request.TransactionRequest
import com.br.desafio_java_transacoes.model.response.TransactionResponse
import org.springframework.stereotype.Repository
import java.time.ZoneId
import java.time.ZonedDateTime

@Repository
class TransactionRepository(private val transactions : MutableList<TransactionEntity> = mutableListOf()) {

    fun createTransaction(request: TransactionRequest): TransactionResponse {
        val newTransaction = TransactionEntity(value = request.value, date = ZonedDateTime.now(ZoneId.of(SAO_PAULO_TIME_ZONE)))
        transactions.add(newTransaction)

        return TransactionResponse(value = request.value, newTransaction.date)
    }

    fun deleteAllTransactions() {
        transactions.clear()
    }

    fun getAllTransactions() : List<TransactionDto>{
        return transactions.map { transactionEntity ->
            TransactionDto(value = transactionEntity.value, date = transactionEntity.date)
        }.toList()
    }

    companion object {
        private const val SAO_PAULO_TIME_ZONE = "America/Sao_Paulo"
    }
}
