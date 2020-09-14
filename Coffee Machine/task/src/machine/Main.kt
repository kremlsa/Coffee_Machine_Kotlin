package machine

import java.util.*

val scanner = Scanner(System.`in`)
var isRun = true

fun main() {
    val coffemachine = CoffeeMachine()
    while(isRun) {
        when (coffemachine.state) {
            "standby" -> coffemachine.showMenu()
            "buy" -> coffemachine.showBuyMenu()
            "fill" -> coffemachine.showFillMenu()
        }
        val input = scanner.nextLine()
        isRun = coffemachine.operate(input)
        if(coffemachine.fillState != "fillmilk" && coffemachine.fillState != "fillbeans" && coffemachine.fillState != "fillcups")
            println()
    }
}
