package tests

import org.scalatest.FunSuite
import store.model.checkout.SelfCheckout
import store.model.items.Item

class Task1 extends FunSuite {

  test("correct Item") {
    var descriptions: List[String] = List("items", "items2", "items3")
    var prices: List[Double] = List(1.2, 2.2, 5.1)

    descriptions = descriptions :+ "item4"
    prices = prices :+ 5.99
    descriptions = descriptions :+ "item5"
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

  test("change price") {
    var testSelfCheckout: SelfCheckout = new SelfCheckout()
    var testItem: Item = new Item("x", 1.0)
    testSelfCheckout.addItemToStore("472", testItem)
    testSelfCheckout.numberPressed(4)
    testSelfCheckout.numberPressed(7)
    testSelfCheckout.numberPressed(2)
    testSelfCheckout.enterPressed()
    val cart: List[Item] = testSelfCheckout.itemsInCart()

    testItem.setBasePrice(2.0)
    assert(Math.abs(testItem.price() - 2.0) < 0.0001)
  }

  test("correct code") {
    var testSelfCheckout: SelfCheckout = new SelfCheckout()

    var testItem: Item = new Item("Cheese", 12.0)
    testSelfCheckout.addItemToStore("472", testItem)
    testSelfCheckout.numberPressed(4)
    testSelfCheckout.numberPressed(7)
    testSelfCheckout.numberPressed(2)
    testSelfCheckout.enterPressed()
    val cart: List[Item] = testSelfCheckout.itemsInCart()
    assert(cart.head.description() == "Cheese")
    assert(Math.abs(cart.head.price() - 12.0) < 0.0001)
  }

  test("same twice"){
    var testSelfCheckout: SelfCheckout = new SelfCheckout()
    var testItem: Item = new Item("test item", 100.0)
    testSelfCheckout.addItemToStore("123", testItem)
    var testItem2: Item = new Item("large", 20.0)
    testSelfCheckout.addItemToStore("072", testItem2)

    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(2)
    testSelfCheckout.numberPressed(3)
    testSelfCheckout.enterPressed()
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(2)
    testSelfCheckout.numberPressed(3)
    testSelfCheckout.enterPressed()
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.numberPressed(7)
    testSelfCheckout.numberPressed(2)
    testSelfCheckout.enterPressed()
    testSelfCheckout.numberPressed(7)
    testSelfCheckout.numberPressed(8)
    testSelfCheckout.numberPressed(9)
    testSelfCheckout.enterPressed()


    val chart: List[Item] = testSelfCheckout.itemsInCart()

    assert(testSelfCheckout.displayString() == "")

    assert(chart.size == 4)

    assert(chart(0).description() == "test item", "testing description " +
      "expected: " + "test item" + " actually got: " + chart(0).description())
    assert(chart(0).price - 100.0 < 0.0001, "testing setBasePrice " +
      "expected: " + 100.0 + " got: " + chart(0).price())

    assert(chart(1).description() == "test item", "testing description " +
      "expected: " + "test item" + " actually got: " + chart(1).description())
    assert(chart(1).price - 100.0 < 0.0001, "testing setBasePrice " +
      "expected: " + 100.0 + " got: " + chart(1).price())

    testItem.setBasePrice(200.0)
    assert(Math.abs(chart(1).price() - 200.0) < 0.0001)

    assert(chart(2).description() == "large", "testing description " +
      "expected: " + "large" + " actually got: " + chart(2).description())
    assert(chart(2).price - 20.0 < 0.0001, "testing setBasePrice " +
      "expected: " + 20.0 + " got: " + chart(2).price())

    assert(chart(3).description() == "error", "testing description " +
      "expected: " + "error" + " actually got: " + chart(3).description())
    assert(chart(3).price - 0.0 < 0.0001, "testing setBasePrice " +
      "expected: " + 0.0 + " got: " + chart(3).price())

  }

  test("test clear"){
    var testSelfCheckout: SelfCheckout = new SelfCheckout()
    var testItem: Item = new Item("test item", 100.0)
    testSelfCheckout.addItemToStore("123", testItem)

    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(2)
    testSelfCheckout.numberPressed(3)
    testSelfCheckout.clearPressed()
    assert(testSelfCheckout.displayString == "")
  }

  test("display"){
    var testSelfCheckout: SelfCheckout = new SelfCheckout()
    assert(testSelfCheckout.displayString() == "")
    testSelfCheckout.numberPressed(9)
    assert(testSelfCheckout.displayString() == "9")
  }

  test("wrong twice") {
    val checkout: SelfCheckout = new SelfCheckout
    var testItem: Item = new Item("cake", 12.0)
    var testItem2: Item = new Item("cheese", 10.0)
    checkout.addItemToStore("072", testItem)
    checkout.addItemToStore("015", testItem2)
    checkout.numberPressed(0)

    checkout.numberPressed(7)
    checkout.numberPressed(2)
    checkout.numberPressed(0)
    checkout.numberPressed(7)
    checkout.numberPressed(2)
    checkout.numberPressed(0)
    checkout.numberPressed(1)
    checkout.numberPressed(5)
    checkout.enterPressed()
    checkout.numberPressed(0)
    checkout.numberPressed(1)
    checkout.numberPressed(2)
    checkout.enterPressed()

    val cart: List[Item] = checkout.itemsInCart()

    assert(cart.size == 2)

    assert(cart(0).description() == "error")
    assert(Math.abs(cart(0).price() - 0.0) < 0.0001)

    assert(cart(1).description() == "error")
    assert(Math.abs(cart(1).price() - 0.0) < 0.0001)
  }

}
