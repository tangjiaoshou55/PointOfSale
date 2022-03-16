package store.model.items

class Sale (percentageOfSale: Double) extends Modifier() {

  override def updatePrice(initialPrice: Double): Double = {
    initialPrice * (1 - (this.percentageOfSale * 0.01))
  }

  override def computeTax(price: Double): Double = {
    0.0
  }
}
