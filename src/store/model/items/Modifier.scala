package store.model.items

abstract class Modifier() {

  var state: LoyaltyState = new normalState(this)
  
  def updatePrice(originPrice: Double): Double

  def computeTax(price: Double): Double

  def percentage(): Double
}
