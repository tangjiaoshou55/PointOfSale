package store.model.items

abstract class LoyaltyState() {

  def updatePrice(originPrice: Double): Double
}
