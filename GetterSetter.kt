package estudos

class Pessoa() {
    var nome = ""
        get() {
            println("Utilizado GET nome")
            return field.uppercase()
        }
        set(value) {
            println("Utilizado SET nome")
            field = value
        }
    // `field` se refere a variável `nome`

    var idade = 0
        get() {
            println("Utilizado GET idade")
            return field
        }
        set(value) {
            println("Utilizado SET idade")
            field = value
        }
    // `field` se refere a variável `idade`

    val maiorIdade: Boolean get() = idade >= 18
}