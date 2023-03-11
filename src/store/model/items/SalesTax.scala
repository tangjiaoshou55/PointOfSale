package store.model.items

class SalesTax(percentTax:Double) extends Modifier {

  override def computeTax(price: Double): Double = {
    price * (percentTax/100)
  }

  override def updatePrice(originPrice: Double): Double = {
    originPrice
  }

  override def percentage(): Double = {
    this.percentTax
  }
}
