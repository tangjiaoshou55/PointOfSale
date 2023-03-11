package tests

import org.scalatest.FunSuite
import store.model.checkout.SelfCheckout
import store.model.items.{Item, LoyaltySale, Sale, SalesTax}

class ApplicationObjective extends FunSuite {

  test("correct") {

    val testSelfCheckout: SelfCheckout = new SelfCheckout()
    val apple: Item = new Item("apple", 4.0)
    apple.addModifier(new LoyaltySale(50))
    testSelfCheckout.addItemToStore("072", apple)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.numberPressed(7)
    testSelfCheckout.numberPressed(2)
    val display = testSelfCheckout.displayString()
    assert(display == "072")

    testSelfCheckout.enterPressed()
    testSelfCheckout.checkoutPressed()
    testSelfCheckout.loyaltyCardPressed()
    testSelfCheckout.loyaltyCardPressed()
    val cart: List[Item] = testSelfCheckout.itemsInCart()
    val price = cart.head.price()
    val subtotal = testSelfCheckout.subtotal()
    assert(Math.abs(price - 2.0) < 0.001)
    assert(Math.abs(subtotal - 2.0) < 0.001)
    testSelfCheckout.cashPressed()

    testSelfCheckout.numberPressed(0)
    testSelfCheckout.numberPressed(7)
    testSelfCheckout.numberPressed(2)
    testSelfCheckout.enterPressed()
    val newcart: List[Item] = testSelfCheckout.itemsInCart()
    val newprice = newcart.head.price()
    assert(Math.abs(newprice - 4.0) < 0.001)
    testSelfCheckout.loyaltyCardPressed()
    testSelfCheckout.loyaltyCardPressed()
  }
}
