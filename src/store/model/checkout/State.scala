package store.model.checkout

import store.model.items.Item

abstract class State (main:SelfCheckout) {
  def addItemToStore(barcode: String, item: Item): Unit

  def numberPressed(number: Int): Unit

  def clearPressed(): Unit

  def enterPressed(): Unit

  def checkoutPressed(): Unit

  def cashPressed(): Unit

  def creditPressed(): Unit

  def loyaltyCardPressed(): Unit

  def displayString(): String

  def itemsInCart(): List[Item]

  def subtotal(): Double

  def tax(): Double

  def total(): Double

  def prepareStore(): Unit
}