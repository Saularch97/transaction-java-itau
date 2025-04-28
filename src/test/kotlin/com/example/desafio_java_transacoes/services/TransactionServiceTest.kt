package com.example.desafio_java_transacoes.services

import com.br.desafio_java_transacoes.model.dto.TransactionDto
import com.br.desafio_java_transacoes.model.request.TransactionRequest
import com.br.desafio_java_transacoes.model.response.TransactionResponse
import com.br.desafio_java_transacoes.repositories.TransactionRepository
import com.br.desafio_java_transacoes.services.TransactionService
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.ZonedDateTime
import kotlin.test.assertEquals

class TransactionServiceTest {
    private val transactionRepository = mockk<TransactionRepository>()
    private val transactionService = TransactionService(transactionRepository)

    @Test
    fun `createTransaction should return a transactionResponse`() {
        val fixedAmount = 10.0
        val fixedTime = ZonedDateTime.parse("2024-01-01T00:00:00Z")
        val expectedResponse = TransactionResponse(fixedAmount, fixedTime)

        every { transactionRepository.createTransaction(any()) } returns expectedResponse

        transactionService.createTransaction(TransactionRequest(fixedAmount))

        verify(exactly = 1) { transactionRepository.createTransaction(any()) }
    }

    @Test
    fun `deleteAllTransactions should call repository`() {
        every { transactionRepository.deleteAllTransactions() } just Runs

        transactionService.deleteAllTransactions()

        verify(exactly = 1) { transactionRepository.deleteAllTransactions() }
    }

    @Test
    fun `getStatistics should return statistics about one or more transactions in a given period`() {
        val now = ZonedDateTime.now()

        every { transactionRepository.getAllTransactions() } returns listOf(
                TransactionDto(1.0, now),
                TransactionDto(2.0, now.plusSeconds(3)),
                TransactionDto(3.0, now.plusSeconds(4)
            )
        )

        val response = transactionService.getStatistics(60, now)

        assertEquals(3.0, response.max)
        assertEquals(1.0, response.min)
        assertEquals(2.0, response.avg)
        assertEquals(3, response.count)
    }

    @Test
    fun `getStatistics should return an StatisticsResponseWith zero values if no transaction occurred in period`() {
        val now = ZonedDateTime.now()

        every { transactionRepository.getAllTransactions() } returns listOf(
            TransactionDto(1.0, now.minusSeconds(2)),
            TransactionDto(2.0, now.minusSeconds(3)),
            TransactionDto(3.0, now.minusSeconds(4)
            )
        )

        val response = transactionService.getStatistics(1, now)

        assertEquals(0.0, response.max)
        assertEquals(0.0, response.min)
        assertEquals(0.0, response.avg)
        assertEquals(0, response.count)
    }

    @Test
    fun `getStatistics should return an StatisticsResponse with values related to transactions made within the seconds limit`() {
        val now = ZonedDateTime.now()

        every { transactionRepository.getAllTransactions() } returns listOf(
            TransactionDto(1.0, now.minusSeconds(2)),
            TransactionDto(2.0, now.minusSeconds(3)),
            TransactionDto(3.0, now.minusSeconds(4)
            )
        )

        val response = transactionService.getStatistics(4, now)

        assertEquals(2, response.count)
        assertEquals(2.0, response.max)
        assertEquals(1.0, response.min)
        assertEquals(1.5, response.avg)
    }
}