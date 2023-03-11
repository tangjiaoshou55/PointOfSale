package store.model.items

class Item(description:String, var basePrice:Double) {

  // TODO: Complete this class according to the features listed in the HW document

  var modifiers: List[Modifier] = List()
  def addModifier(modifier: Modifier): Unit = {
    modifiers = modifiers :+ modifier
  }

  def description(): String = {
    description
  }

  def setBasePrice(price:Double): Unit = {
    basePrice = price
  }

  def price():Double = {
    var price:Double = basePrice
    for (modifier <- modifiers){
      price = modifier.updatePrice(price)
    }

    return price
  }

  def tax():Double = {
    val finalPrice:Double = price()
    var tax:Double = 0.0
    for (modifier <- modifiers){
      tax += modifier.computeTax(finalPrice)
    }

    return tax
  }
}
