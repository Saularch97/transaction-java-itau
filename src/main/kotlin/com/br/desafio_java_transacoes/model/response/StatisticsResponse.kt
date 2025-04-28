package com.br.desafio_java_transacoes.model.response

data class StatisticsResponse(
    var count: Int? = 0,
    var sum: Double? = 0.0,
    var avg: Double? = 0.0,
    var min: Double? = 0.0,
    var max: Double? = 0.0
) {
}
