package com.example.calculator

import com.example.calculator.mainscreen.domain.Calculator
import com.example.calculator.mainscreen.ui.CalculatorPresenter
import com.example.calculator.mainscreen.ui.CalculatorView
import com.nhaarman.mockito_kotlin.times
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class CalculatorPresenterTest {

    private lateinit var presenter: CalculatorPresenter

    @Mock
    private lateinit var view: CalculatorView

    @Mock
    private lateinit var calculator: Calculator

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = CalculatorPresenter()
        presenter.setView(view)
    }

    @Test
    fun setCalculator_Test() {
        Mockito.`when`(calculator.text).thenReturn("10")
        presenter.setCalculator(calculator)
        verify(view, times(1)).setScreenText("10")
    }

    @Test
    fun onButtonClick_Test() {
        presenter.setCalculator(calculator)
        Mockito.`when`(calculator.text).thenReturn("9")
        presenter.onButtonClick("5")
        presenter.onButtonClick("+")
        presenter.onButtonClick("4")
        presenter.onButtonClick("=")
        verify(view, times(4)).setScreenText("9")
    }

    @Test
    fun ssetExternalText_Test() {
        presenter.setCalculator(calculator)
        Mockito.`when`(calculator.text).thenReturn("7")
        presenter.setExternalText("5+2=")
        verify(view, times(1)).setScreenText("7")
    }

    @Test
    fun showSettings_Test() {
        presenter.showSettings()
        verify(view, times(1)).showSettings()
    }

    @Test(expected = java.lang.NullPointerException::class)
    fun detachView_Test() {
        presenter.setCalculator(calculator)
        presenter.detachView()
        presenter.setExternalText("2+4=")
    }
}