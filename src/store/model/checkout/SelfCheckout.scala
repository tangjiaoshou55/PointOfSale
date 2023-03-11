package store.model.checkout

import store.model.items.{Item, LoyaltySale}

class SelfCheckout {

  var state: State = new normal(this)
  var inventory: Map[String, Item] = Map()
  var numberStored:String = ""
  var chart: List[Item] = List()

  def addItemToStore(barcode: String, item: Item): Unit = {
    // This method adds an item to your store's checkout system. It does not add an item to the customer's cart
    this.state.addItemToStore(barcode, item)
  }

  def numberPressed(number: Int): Unit = {
    this.state.numberPressed(number: Int)
  }

  def clearPressed(): Unit = {
    this.state.clearPressed()
  }

  def enterPressed(): Unit = {
    this.state.enterPressed()
  }

  def checkoutPressed(): Unit = {
    this.state.checkoutPressed()
  }

  def cashPressed(): Unit = {
    this.state.cashPressed()
  }

  def creditPressed(): Unit = {
    this.state.creditPressed()
  }

  def loyaltyCardPressed(): Unit = {
    this.state.loyaltyCardPressed()
  }

  def displayString(): String = {
    this.state.displayString()
  }

  def itemsInCart(): List[Item] = {
    this.state.itemsInCart()
  }

  def subtotal(): Double = {
    this.state.subtotal()
  }

  def tax(): Double = {
    this.state.tax()
  }

  def total(): Double = {
    this.state.total()
  }

  def prepareStore(): Unit = {
    // Similar to openMap in the Pale Blue Dot assignment, this method is not required and is
    // meant to help you run manual tests.
    //
    // This method is called by the GUI during setup. Use this method to prepare your
    // items and call addItemToStore to add their barcodes. Also add any sales/tax/etc to your
    // items.
    //
    // This method will not be called during testing and you should not call it in your tests.
    // Each test must setup its own items to ensure compatibility in AutoLab. However, you can
    // write a similar method in your Test Suite classes.

    // Example usage:
    //val testItem: Item = new Item("test item", 100.0)
    //this.addItemToStore("472", testItem)

    val testItem: Item = new Item("x", 20.0)
    val testItem2: Item = new Item("y", 200.0)
    testItem.addModifier(new LoyaltySale(50))
    testItem2.addModifier(new LoyaltySale(40))
    this.addItemToStore("123", testItem)
    this.addItemToStore("234", testItem2)
  }
}
