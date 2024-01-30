package com.kings.study

import android.widget.Toast

class MensagemToast(val mensagem: String, val tempo: Int) {

    companion object {

        val TEMPO_CURTO: Int = 0
        val TEMPO_LONGO: Int = 1

        fun ConstruirMensagem(mensagem: String, tempo: Int): MensagemToast {
            return MensagemToast(mensagem,tempo)
        }

    }

    fun exibir() = println("Mensagem: $mensagem, Tempo: $tempo")
}

fun main(){

    MensagemToast.ConstruirMensagem(
        "Teste",
        MensagemToast.TEMPO_CURTO
    ).exibir()

    // UTILIZADO NO ANDROID STUDIO:
    // Toast.makeText(this, "Mensagem", Toast.LENGTH_SHORT).show()
}