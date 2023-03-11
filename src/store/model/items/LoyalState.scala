package store.model.items

class LoyalState (modifier: Modifier) extends LoyaltyState() {

  override def updatePrice(originPrice: Double): Double = {
    val percentage = this.modifier.percentage()
    originPrice*((100-percentage)*0.01)
  }
}
