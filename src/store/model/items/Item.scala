package store.model.items

class Item(description: String, var basePrice: Double) {

  // TODO: Complete this class according to the features listed in the HW document

  var items: List[Modifier] = List()
  def addModifier(item: Modifier): Unit = {
    items=items :+ item
  }

  def description(): String = {
    return description
  }

  def setBasePrice(price: Double): Unit = {
     this.basePrice = price
  }

  def price(): Double = {
    var price: Double = basePrice
    for (item <- items) {
      price = item.updatePrice(price)
    }

    return price
  }

  def tax(): Double = {
    val finalPrice: Double = price()
    var tax: Double = 0.0
    for (item <- items){
      tax += item.computeTax(finalPrice)
    }

    return tax
  }
}
