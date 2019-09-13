package br.edu.ifsp.scl.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var concatenaLcd: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Primeira Linha
        seteBt.setOnClickListener(this)
        oitoBt.setOnClickListener(this)
        noveBt.setOnClickListener(this)
        adicaoBt.setOnClickListener(this)

        ceBt.setOnClickListener {
            lcdTv.text = ""
            concatenaLcd = false
        }

        cBt.setOnClickListener {
            lcdTv.text = Calculadora.calcula(0.0f, Operador.RESULTADO).toString()
            lcdTv.text = ""
            concatenaLcd = false
        }

        raizQuadradaBt.setOnClickListener {
            lcdTv.text = Calculadora.calcula(lcdTv.text.toString().toFloat(), Operador.RAIZQUADRADA).toString()
            concatenaLcd = false
        }

        porcentagemBt.setOnClickListener {
            lcdTv.text = Calculadora.calcula(lcdTv.text.toString().toFloat(), Operador.PORCENTAGEM).toString()
            concatenaLcd = false
        }

        umBt.setOnClickListener {
            // Numero
            if (!concatenaLcd) {
                lcdTv.text = ""
            }
            lcdTv.append((it as Button).text.toString())
            concatenaLcd = true
        }

        doisBt.setOnClickListener { p0 ->
            // Numero
            if (!concatenaLcd) {
                lcdTv.text = ""
            }
            lcdTv.append((p0 as Button).text.toString())
            concatenaLcd = true
        }

        // Segunda Linha
        tresBt.setOnClickListener { x: View ->
            // Numero
            if (!concatenaLcd) {
                lcdTv.text = ""
            }
            lcdTv.append((x as Button).text.toString())
            concatenaLcd = true
        }

        multiplicacaoBt.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                // MULT
                lcdTv.text = Calculadora.calcula(lcdTv.text.toString().toFloat(), Operador.MULTIPLICACAO).toString()
                concatenaLcd = false
            }
        })

        zeroBt.setOnClickListener(::onClickZeroPontoResultadoDivisao) // Funcao como parametro
        pontoBt.setOnClickListener(::onClickZeroPontoResultadoDivisao)
        resultadoBt.setOnClickListener(::onClickZeroPontoResultadoDivisao)
        val f: (View) -> Unit = ::onClickZeroPontoResultadoDivisao
        divisaoBt.setOnClickListener(::onClickZeroPontoResultadoDivisao)
    }

    override fun onClick(p0: View?) {
        if (p0 == seteBt || p0 == oitoBt || p0 == noveBt) {
            // Numero
            if (!concatenaLcd) {
                lcdTv.text = ""
            }
            lcdTv.append((p0 as Button).text.toString())
            concatenaLcd = true
        } else {
            if (p0 == adicaoBt) {
                // Adicao
                lcdTv.text = Calculadora.calcula(lcdTv.text.toString().toFloat(), Operador.ADICAO).toString()
                concatenaLcd = false
            }
        }
    }

    fun onClickBtNum(p0: View?) {
        // Numero
        if (!concatenaLcd) {
            lcdTv.text = ""
        }
        lcdTv.append((p0 as Button).text.toString())
        concatenaLcd = true
    }

    fun onClickBtSub(p0: View?) {
        // Subtracao
        lcdTv.text = Calculadora.calcula(lcdTv.text.toString().toFloat(), Operador.SUBTRACAO).toString()
        concatenaLcd = false
    }

    fun onClickZeroPontoResultadoDivisao(view: View?) {
        when (view) {
            zeroBt -> {
                // Limpa LCD se último clicado foi um operador
                if (!concatenaLcd) {
                    lcdTv.text = ""
                }
                lcdTv.append((view as Button).text.toString())
                concatenaLcd = true
            }
            pontoBt -> {
                if (!lcdTv.text.toString().contains(".")) {
                    if (!concatenaLcd) {
                        lcdTv.text = "0"
                    }
                    lcdTv.append(".")
                    concatenaLcd = true
                }
            }
            resultadoBt -> {
                lcdTv.text = Calculadora.calcula(
                    lcdTv.text.toString().toFloat(),
                    Operador.RESULTADO
                ).toString()
                concatenaLcd = false
            }
            divisaoBt -> {
                lcdTv.text = Calculadora.calcula(
                    lcdTv.text.toString().toFloat(),
                    Operador.DIVISAO
                ).toString()
                concatenaLcd = false
            }
        }
    }

}
