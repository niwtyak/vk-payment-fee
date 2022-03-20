enum class CardType {
    Mastercard, Maestro, Visa, Mup, VKPay
}

fun calculateFee(
    cardType: CardType = CardType.VKPay,
    monthTransfer: Int = 0,
    amount: Int
): Double {
    return when (cardType) {
        CardType.VKPay -> 0.0
        CardType.Mastercard, CardType.Maestro -> {
            if (monthTransfer in 30000..7500000)
                0.0
            else
                amount * 0.006 + 2000
        }
        CardType.Mup, CardType.Visa -> {
            val fee = amount * 0.0075
            if (fee >= 3500.0) fee else 3500.0
        }
    }
}

fun main() {
    println("Комисия за перевод 100000 руб. равна ${calculateFee(amount = 10000000) / 100} руб.")
    println(
        "Комисия за перевод 100000 руб. равна ${
            calculateFee(
                cardType = CardType.Mup,
                amount = 10000000
            ) / 100
        } руб."
    )
    println("Комисия за перевод 100000 руб. равна ${calculateFee(CardType.Maestro, 1000000, 10000000) / 100} руб.")
    println("Комисия за перевод 100000 руб. равна ${calculateFee(CardType.Maestro, 8000000, 10000000) / 100} руб.")
}