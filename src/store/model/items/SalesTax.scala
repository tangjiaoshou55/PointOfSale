package store.model.items

class SalesTax (percentageOfSaleTax: Double) extends Modifier {

  override def updatePrice(initialPrice: Double): Double = {
    initialPrice
  }

  override def computeTax(price: Double): Double = {
    price * this.percentageOfSaleTax * 0.01
  }
}
