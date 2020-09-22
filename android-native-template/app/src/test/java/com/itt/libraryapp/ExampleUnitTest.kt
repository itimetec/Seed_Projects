package com.itt.libraryapp

import com.itt.libraryapp.model.RegistrationPageResult
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun dataClass_test() {
        val responseString = "{\n" +
                "    \"id\": \"665701\",\n" +
                "    \"email\": \"shr@gmail.com\",\n" +
                "    \"password\": \"12345678\"\n" +
                "}"
        val registrationPageResult = RegistrationPageResult(responseString)
        assertEquals("665701",registrationPageResult.id)
        assertEquals("shr@gmail.com",registrationPageResult.email)
        assertEquals("12345678",registrationPageResult.password)
    }
}
