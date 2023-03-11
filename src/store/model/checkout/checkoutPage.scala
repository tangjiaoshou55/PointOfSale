package store.model.checkout

import store.model.items.{Item, LoyalState}

class checkoutPage(main: SelfCheckout) extends State(main) {


  override def addItemToStore(barcode: String, item: Item): Unit = {

  }

  override def numberPressed(number: Int): Unit = {

  }

  override def clearPressed(): Unit = {

  }

  override def enterPressed(): Unit = {

  }

  override def checkoutPressed(): Unit = {

  }

  override def cashPressed(): Unit = {
    main.chart = List()
    main.state = new normal(main)
  }

  override def creditPressed(): Unit = {
    main.chart = List()
    main.state = new normal(main)
  }

  override def loyaltyCardPressed(): Unit = {
    for (value <- main.inventory.values) {
      for (mod <- value.modifiers) {
        mod.state = new LoyalState(mod)
      }
    }

    main.state = new loyalCheckoutPage(main)
  }

  override def displayString(): String = {
    "cash or credit"
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
