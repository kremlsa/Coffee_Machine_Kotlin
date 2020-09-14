package machine

class CoffeeMachine {
    var state: String
    var fillState: String
    var water: Int
    var milk: Int
    var bean: Int
    var cups: Int
    var money: Int
    var isRun = true

    constructor() {
        state = State.STANDBY.state
        fillState = State.FILLWATER.state
        water = 400
        milk = 540
        bean = 120
        cups = 9
        money = 550
    }

    enum class Coffee(val water: Int, val milk: Int, val bean: Int, val money: Int) {
        ESPRESSO(250, 0,16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6);
    }

    enum class State(val state: String) {
        STANDBY("standby"),
        FILL("fill"),
        FILLWATER("fillwater"),
        FILLMILK("fillmilk"),
        FILLBEANS("fillbeans"),
        FILLCUPS("fillcups"),
        BUY("buy");
    }

    fun operate(operation: String): Boolean {
        when (state) {
            "standby" -> standbyMenu(operation)
            "buy" -> buyCoffeeMenu(operation)
            "fill" -> fillMachine(operation)
        }
        return isRun
    }

    fun standbyMenu(operation: String) {
        when (operation) {
            "fill" -> state = State.FILL.state
            "buy" -> state = State.BUY.state
            "take" -> takeMoney()
            "remaining" -> getStatus()
            "exit" -> isRun = false
        }

    }

    fun buyCoffeeMenu(operation: String) {
        when (operation) {
            "1" -> {
                prepareCoffee(1)
                state = State.STANDBY.state
            }
            "2" -> {
                prepareCoffee(2)
                state = State.STANDBY.state
            }
            "3" -> {
                prepareCoffee(3)
                state = State.STANDBY.state
            }
            "back" -> {
                state = State.STANDBY.state
            }
        }
    }

    fun prepareCoffee(coffeeDrink: Int) {
        when (coffeeDrink) {
            1 -> {
                if (water >= Coffee.ESPRESSO.water && bean >= Coffee.ESPRESSO.bean && cups >= 1) {
                    water -= Coffee.ESPRESSO.water
                    bean -= Coffee.ESPRESSO.bean
                    cups -= 1
                    money += Coffee.ESPRESSO.money
                    println("I have enough resources, making you a coffee!")
                } else if (water < Coffee.ESPRESSO.water) {
                    println("Sorry, not enough water!")
                } else if (bean < Coffee.ESPRESSO.bean) {
                    println("Sorry, not enough coffee beans!")
                } else if (cups < 1) {
                    println("Sorry, not enough cups!")
                }
            }
            2 -> {
                if (water >= Coffee.LATTE.water && bean >= Coffee.LATTE.bean && cups >= 1 && milk >= Coffee.LATTE.milk) {
                    water -= Coffee.LATTE.water
                    milk -= Coffee.LATTE.milk
                    bean -= Coffee.LATTE.bean
                    cups -= 1
                    money += Coffee.LATTE.money
                    println("I have enough resources, making you a coffee!")
                } else if (water < Coffee.LATTE.water) {
                    println("Sorry, not enough water!")
                } else if (bean < Coffee.LATTE.bean) {
                    println("Sorry, not enough coffee beans!")
                } else if (cups < 1) {
                    println("Sorry, not enough cups!")
                } else if (milk < Coffee.LATTE.milk) {
                    println("Sorry, not enough milk!")
                }
            }
            3 -> {
                if (water >= Coffee.CAPPUCCINO.water && bean >= Coffee.CAPPUCCINO.bean && cups >= 1 && milk >= Coffee.CAPPUCCINO.milk) {
                    water -= Coffee.CAPPUCCINO.water
                    milk -= Coffee.CAPPUCCINO.milk
                    bean -= Coffee.CAPPUCCINO.bean
                    cups -= 1
                    money += Coffee.CAPPUCCINO.money
                    println("I have enough resources, making you a coffee!")
                } else if (water < Coffee.CAPPUCCINO.water) {
                    println("Sorry, not enough water!")
                } else if (Coffee.CAPPUCCINO.bean < 20) {
                    println("Sorry, not enough coffee beans!")
                } else if (cups < 1) {
                    println("Sorry, not enough cups!")
                } else if (milk < Coffee.CAPPUCCINO.milk) {
                    println("Sorry, not enough milk!")
                }
            }
        }
    }

    fun showFillMenu() {
        when(fillState) {
            "fillwater" -> print("Write how many ml of water do you want to add: ")
            "fillmilk" -> print("Write how many ml of milk do you want to add: ")
            "fillbeans" -> print("Write how many grams of coffee beans do you want to add: ")
            "fillcups" -> print("Write how many disposable cups of coffee do you want to add: ")

        }
    }

    fun fillMachine(operations: String) {
        when(fillState) {
            "fillwater" -> {
                water += operations.toInt()
                fillState = State.FILLMILK.state
            }
            "fillmilk" -> {
                milk += operations.toInt()
                fillState = State.FILLBEANS.state
            }
            "fillbeans" -> {
                bean += operations.toInt()
                fillState = State.FILLCUPS.state
            }
            "fillcups" -> {
                cups += operations.toInt()
                fillState = State.FILLWATER.state
                state = State.STANDBY.state
            }

        }
    }

    fun showMenu() {
        print("Write action (buy, fill, take, remaining, exit): ")
    }

    fun showBuyMenu() {
        print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
    }

    fun getStatus() {
        println()
        println("The coffee machine has:\n" +
                "$water of water\n" +
                "$milk of milk\n" +
                "$bean of coffee beans\n" +
                "$cups of disposable cups\n" +
                "$money of money")
    }

    fun takeMoney() {
        println("I gave you \$$money")
        money = 0
    }

}