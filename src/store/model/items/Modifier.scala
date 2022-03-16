package store.model.items

abstract class Modifier() {

  def updatePrice (initialPrice: Double): Double

  def computeTax (price: Double): Double


}
