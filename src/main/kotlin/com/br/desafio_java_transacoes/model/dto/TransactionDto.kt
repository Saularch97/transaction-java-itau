package com.br.desafio_java_transacoes.model.dto

import java.time.ZonedDateTime

data class TransactionDto(val value: Double?, val date: ZonedDateTime) {
}