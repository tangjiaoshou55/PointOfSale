package store.model.items

class normalState (modifier: Modifier) extends LoyaltyState() {

  override def updatePrice(originPrice: Double): Double = {
    originPrice
  }
}
