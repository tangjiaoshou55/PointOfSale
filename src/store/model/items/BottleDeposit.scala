package store.model.items

class BottleDeposit(totalPrice:Double) extends Modifier {

  override def updatePrice(originPrice: Double): Double = {
    originPrice
  }

  override def computeTax(price: Double): Double = {
    totalPrice
  }

  override def percentage(): Double = {
    totalPrice
  }
}
