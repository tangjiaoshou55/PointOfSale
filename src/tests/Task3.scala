package tests

import org.scalatest.FunSuite
import store.model.checkout.SelfCheckout
import store.model.items.{Item, Sale, SalesTax}

class Task3 extends FunSuite{
  test("with clear") {
    val testSelfCheckout: SelfCheckout = new SelfCheckout
    val testItem: Item = new Item("x", 1.0)
    testSelfCheckout.addItemToStore("472", testItem)
    assert(testSelfCheckout.displayString() == "")
    testSelfCheckout.numberPressed(4)
    testSelfCheckout.numberPressed(7)
    testSelfCheckout.numberPressed(2)
    testSelfCheckout.enterPressed()
    testSelfCheckout.enterPressed() // rescan with enter
    assert(testSelfCheckout.displayString() == "")
    testSelfCheckout.numberPressed(4)
    testSelfCheckout.numberPressed(7)
    testSelfCheckout.numberPressed(2)
    assert(testSelfCheckout.displayString() == "472")
    testSelfCheckout.clearPressed()
    assert(testSelfCheckout.displayString() == "")
    testSelfCheckout.enterPressed()
    val cart: List[Item] = testSelfCheckout.itemsInCart()
    assert(cart.size == 3)
    assert(cart(0).description() == "x")
    assert(Math.abs(cart(0).price() - 1.0) < 0.0001)
    assert(cart(1).description() == "x")
    assert(Math.abs(cart(1).price() - 1.0) < 0.0001)
    assert(cart(2).description() == "error")
    assert(Math.abs(cart(2).price() - 0.0) < 0.0001)
  }
  test("not with clear") {
    var testSelfCheckout: SelfCheckout = new SelfCheckout()

    var testItem: Item = new Item("Cheese", 12.0)
    testSelfCheckout.addItemToStore("472", testItem)
    testSelfCheckout.numberPressed(4)
    testSelfCheckout.numberPressed(7)
    testSelfCheckout.numberPressed(2)
    testSelfCheckout.enterPressed()
    testSelfCheckout.enterPressed()
    testSelfCheckout.numberPressed(4)
    testSelfCheckout.numberPressed(7)
    testSelfCheckout.enterPressed()
    val cart: List[Item] = testSelfCheckout.itemsInCart()
    assert(cart.size == 3)

    testSelfCheckout.checkoutPressed()
    testSelfCheckout.displayString()
    assert(testSelfCheckout.displayString()=="cash or credit") //display cash or credit
    testSelfCheckout.cashPressed()
    assert(testSelfCheckout.displayString()=="")
    testSelfCheckout.numberPressed(4)
    testSelfCheckout.numberPressed(7)
    testSelfCheckout.numberPressed(2)
    testSelfCheckout.enterPressed()
    assert(testSelfCheckout.itemsInCart().size == 1)
  }
}
