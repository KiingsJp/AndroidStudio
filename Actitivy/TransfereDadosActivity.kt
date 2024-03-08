
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// EM Project > Gradle Scripts > build.gradle.kts (Module :app) = ADICIONAR O PLUGIN KOTLIN-PARCELIZE
/*

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize") // <-------
}

*/

// OBJETO PARA SER TRANSFERIDO - TRANSFORMAR EM PARCELABLE

// TAMBÉM PODE SER TRANSFORMADO EM SERIALIZABLE -- assim não precisa do plugin kotlin-parcelize

@Parcelize
data class Filme(
    val nome: String,
    val genero: String,
    val idadeClass: Int
) : Parcelable

// ENVIAR DADOS E ABRIR NOVA ACTIVITY
/*

fun main(){

    val intent: Intent = Intent(this, DiarioActivity::class.java);

    val filme = Filme("Vingadores", "Ação",12)
    intent.putExtra("Filme", filme)

    startActivity(intent);

}

*/

// RECEBER DADOS
/*

fun novaActivity(){
    val extras = intent.extras

    // VERIFICA A VERSÃO DO ANDROID PARA USAR O MÉTODO RECOMENDADO
    val filme: Filme =
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            extras?.getParcelable("Filme", Filme::class.java)
        else
            extras?.getParcelable("Filme")

    // getSerializable
    val pokemon = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getSerializableExtra("pokemon", Pokemon::class.java)
    } else
        intent.getSerializableExtra("pokemon") as Pokemon

}

 */
