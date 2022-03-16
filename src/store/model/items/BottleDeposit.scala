package store.model.items

class BottleDeposit (total: Double) extends Modifier {

  override def updatePrice(initialPrice: Double): Double = {
    initialPrice
  }

  override def computeTax(input: Double): Double = {
    total
  }
}
