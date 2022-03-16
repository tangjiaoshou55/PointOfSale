package tests

import org.scalatest.FunSuite
import store.model.checkout.SelfCheckout
import store.model.items.Item
import store.view.SelfCheckoutGUI

class Task1 extends FunSuite {

  test("your test name") {
    //    var testSelfCheckout: SelfCheckout = new SelfCheckout()
    //
    //    var testItem: Item = new Item("test item", 100.0)
    //
    //    testSelfCheckout.addItemToStore("123", testItem)
    // TODO
    var descriptions: List[String] = List("items", "items2", "items3")
    var prices: List[Double] = List(1.2, 2.2, 5.1)

    descriptions = descriptions :+ "My description"
    prices = prices :+ 5.99
    descriptions = descriptions :+ "Oreos"
    prices = prices :+ 4.50

    var items: List[Item] = List()
    for (index <- descriptions.indices) {
      items = items :+ new Item(descriptions(index), prices(index))

      assert(items(index).description() == descriptions(index), "testing description " +
        "expected: " + descriptions(index) + " actually got: " + items(index).description())
      assert(Math.abs(items(index).price() - prices(index)) < 0.0001, "testing setBasePrice " +
        "expected: " + prices(index) + " got: " + items(index).price())

      items(index).setBasePrice(prices(index) - 5)
      assert(Math.abs(items(index).price() - (prices(index) - 5)) < 0.0001, "testing setBasePrice " +
        "expected: " + (prices(index) - 5) + " got: " + items(index).price())
    }
  }
  test("clear??") {
    val checkout: SelfCheckout = new SelfCheckout
    var testItem: Item = new Item("cheese", 12.0)
    checkout.addItemToStore("472", testItem)
    checkout.numberPressed(4)
    checkout.numberPressed(7)
    checkout.numberPressed(2)
    checkout.enterPressed()
    val cart: List[Item] = checkout.itemsInCart()
    assert(cart.size == 1)
    assert(cart.head.description() == "cheese")
    assert(Math.abs(cart.head.price() - 12.0) < 0.0001)
  }
  test("same twice") {
    val checkout: SelfCheckout = new SelfCheckout
    var testItem: Item = new Item("cheese", 12.0)
    var testItem2: Item = new Item("big", 10.0)
    checkout.addItemToStore("072", testItem)
    checkout.addItemToStore("022", testItem2)

    checkout.numberPressed(0)
    checkout.numberPressed(7)
    checkout.numberPressed(2)
    checkout.enterPressed()
    checkout.numberPressed(0)
    checkout.numberPressed(7)
    checkout.numberPressed(2)
    checkout.enterPressed()
    checkout.numberPressed(0)
    checkout.numberPressed(2)
    checkout.numberPressed(2)
    checkout.enterPressed()
    checkout.numberPressed(0)
    checkout.numberPressed(1)
    checkout.numberPressed(2)
    checkout.enterPressed()

    val cart: List[Item] = checkout.itemsInCart()
    assert(cart.size == 4)
    assert(cart.head.description() == "cheese")
    assert(Math.abs(cart.head.price() - 12.0) < 0.0001)
    assert(cart(1).description() == "cheese")
    assert(Math.abs(cart(1).price() - 12.0) < 0.0001)
    testItem.setBasePrice(2.0)
    assert(Math.abs(cart(1).price() - 2.0) < 0.0001)
    assert(cart(2).description() == "big")
    assert(Math.abs(cart(2).price() - 10.0) < 0.0001)
    assert(cart(3).description() == "error")
    assert(Math.abs(cart(3).price() - 0.0) < 0.0001)
  }
  test("change price") {
    var testItem = new Item("c", 1.0)
    testItem.setBasePrice(2.0)
    assert(Math.abs(testItem.price() - 2.0) < 0.0001)
  }
  test("display") {
    val checkout: SelfCheckout = new SelfCheckout
    assert(checkout.displayString() == "")
    checkout.numberPressed(7)
    assert(checkout.displayString() == "7")
  }
  test(" twice") {
    val checkout: SelfCheckout = new SelfCheckout
    var testItem: Item = new Item("cheese", 12.0)
    var testItem2: Item = new Item("big", 10.0)
    checkout.addItemToStore("072", testItem)
    checkout.addItemToStore("022", testItem2)
    checkout.numberPressed(0)

    checkout.numberPressed(7)
    checkout.numberPressed(2)
    checkout.numberPressed(0)
    checkout.numberPressed(7)
    checkout.numberPressed(2)
    checkout.numberPressed(0)
    checkout.numberPressed(2)
    checkout.numberPressed(2)
    checkout.enterPressed()
    checkout.numberPressed(0)
    checkout.numberPressed(1)
    checkout.numberPressed(2)
    checkout.enterPressed()
    val cart: List[Item] = checkout.itemsInCart()
    assert(cart.size == 2)
    assert(cart.head.description() == "error")
    assert(Math.abs(cart.head.price() - 0.0) < 0.0001)

    assert(cart(1).description() == "error")
    assert(Math.abs(cart(1).price() - 0.0) < 0.0001)
  }
  test("clear"){
    val checkout: SelfCheckout = new SelfCheckout
    var testItem: Item = new Item("cheese", 12.0)
    var testItem2: Item = new Item("big", 10.0)
    checkout.addItemToStore("072", testItem)
    checkout.addItemToStore("022", testItem2)
    checkout.numberPressed(0)
    checkout.numberPressed(7)
    checkout.numberPressed(2)
    checkout.clearPressed()
    assert(checkout.displayString=="")
  }
}
