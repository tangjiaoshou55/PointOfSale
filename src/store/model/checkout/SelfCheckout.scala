package store.model.checkout

import store.model.items.Item

class SelfCheckout {

  var allProduct: Map[String, Item] = Map()

  def addItemToStore(barcode: String, item: Item): Unit = {
    // This method adds an item to your store's checkout system. It does not add an item to the customer's cart
    allProduct += (barcode -> item)
  }

  var numPressed: String = ""

  def numberPressed(number: Int): Unit = {
    numPressed += number.toString
    displayString()
  }

  def clearPressed(): Unit = {
    numPressed = ""
    displayString()
  }

  var chart: List[Item] = List()

  def enterPressed(): Unit = {
    chart :+= allProduct.getOrElse(numPressed, new Item("error", 0.0))
    clearPressed()
  }

  def checkoutPressed(): Unit = {
    // TODO
  }

  def cashPressed(): Unit = {
    // TODO
  }

  def creditPressed(): Unit = {
    // TODO
  }

  def loyaltyCardPressed(): Unit = {
    // TODO
  }

  def displayString(): String = {
    return numPressed
  }

  def itemsInCart(): List[Item] = {
    return chart
  }

  def subtotal(): Double = {
    var total: Double = 0.0

    for (item <- chart){
      total += item.price()
    }

    return total
  }

  def tax(): Double = {
    var totalTax: Double = 0.0

    for (item <- chart){
      totalTax += item.tax()
    }

    return totalTax
  }

  def total(): Double = {
    return subtotal() + tax()
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
  }

}
