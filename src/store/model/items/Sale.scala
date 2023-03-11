package store.model.items

class Sale(percentSale:Double) extends Modifier {

  override def updatePrice(originPrice: Double): Double = {
    return originPrice * (1 - (percentSale * 0.01))
  }

  override def computeTax(price: Double): Double = {
    0.0
  }

  override def percentage(): Double = {
    this.percentSale
  }
}