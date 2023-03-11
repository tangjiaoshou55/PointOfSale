package store.model.checkout

import store.model.items.{Item, normalState,LoyalState}

class loyalNormal (main: SelfCheckout) extends State(main){

  var repeatCheckout: String = ""
  var actual: String = ""

  override def addItemToStore(barcode: String, item: Item): Unit = {
    main.inventory += (barcode -> item)
  }

  override def numberPressed(number: Int): Unit = {
    repeatCheckout = ""
    main.numberStored += number.toString
    displayString()
  }

  override def clearPressed(): Unit = {
    repeatCheckout = ""
    main.numberStored = ""
    displayString()
  }

  override def enterPressed(): Unit = {

    for (value <- main.inventory.values) {
      for (mod <- value.modifiers) {
        mod.state = new LoyalState(mod)
      }
    }

    actual = repeatCheckout + main.numberStored
    repeatCheckout = actual
    main.chart :+= main.inventory.getOrElse(actual, new Item("error", 0.0))
    main.numberStored = ""
    actual = ""
    displayString()
  }

  override def checkoutPressed(): Unit = {

    for (value <- main.inventory.values) {
      for (mod <- value.modifiers) {
        mod.state = new LoyalState(mod)
      }
    }

    main.state = new loyalCheckoutPage(main)
  }

  override def cashPressed(): Unit = {

  }

  override def creditPressed(): Unit = {

  }

  override def loyaltyCardPressed(): Unit = {

  }

  override def displayString(): String = {
    main.numberStored
  }

  override def itemsInCart(): List[Item] = {
    main.chart
  }

  override def subtotal(): Double = {
    var subtotal: Double = 0.0
    for (item <- main.chart) {
      subtotal += item.price()
    }

    subtotal
  }

  override def tax(): Double = {
    var tax: Double = 0.0
    for (item <- main.chart) {
      tax += item.tax()
    }

    tax
  }

  override def total(): Double = {
    subtotal() + tax()
  }

  override def prepareStore(): Unit = {

  }
}
