package com.example.calculator

import com.example.calculator.mainscreen.domain.CalculatorImpl
import org.junit.Assert.*
import org.junit.Test

class CalculatorImplTest {

    @Test
    fun calc_5plus3_ReturnsEquals() {
        val c = CalculatorImpl()
        c.addText("5+3=")
        assertEquals(c.text, "8")
    }

    @Test
    fun calc_5minus3_ReturnsNotEquals() {
        val c = CalculatorImpl()
        c.addText("5-3=")
        assertNotEquals(c.text, "25")
    }

    @Test
    fun calc_wrongText_ReturnsFalse() {
        val c = CalculatorImpl()
        assertFalse(c.addText("5u3="))
    }

    @Test
    fun calc_ArrayEquals_ReturnsArrayEquals() {
        val c = CalculatorImpl()
        assertArrayEquals(arrayOf("1", "2"), c.arrayFrom(arrayListOf("1", "2")))
    }

    @Test
    fun calc_arrayFromNull_ReturnsNull() {
        val c = CalculatorImpl()
        assertNull(c.arrayFrom(null))
    }

    @Test
    fun calc_arrayFromNull_ReturnsNotNull() {
        val c = CalculatorImpl()
        assertNotNull(c.arrayFrom(arrayListOf("1", "2")))
    }

    @Test
    fun calc_arrayFrom_ReturnsNotSame() {
        val c = CalculatorImpl()
        c.addText("5-3=")
        assertNotSame(c.text, "2")
    }

}
