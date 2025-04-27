package com.br.desafio_java_transacoes

import io.mockk.mockk
import org.junit.jupiter.api.Test

class TesteMockkApplicationTests {


	@Test
	fun contextLoads() {
		mockk<String>("hahahaha coringa!")
	}
}
