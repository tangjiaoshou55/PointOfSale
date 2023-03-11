package store.model.items

class LoyaltySale(percentSale:Double) extends Modifier{

  override def computeTax(inputprice: Double): Double = {
    0.0
  }

  override def updatePrice(originPrice: Double): Double = {
    this.state.updatePrice(originPrice)
  }

  override def percentage(): Double = {
    this.percentSale
  }

}
