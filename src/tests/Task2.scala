package tests
import org.scalatest.FunSuite
import store.model.checkout.SelfCheckout
import store.model.items.{BottleDeposit, Item, Sale, SalesTax}
import store.view.SelfCheckoutGUI
class Task2 extends FunSuite {

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

  test("all correct"){
    val testSelfCheckout: SelfCheckout = new SelfCheckout
    val testItem: Item = new Item("x", 1.0)
    testItem.addModifier(new Sale(35.7))
    testItem.addModifier(new SalesTax(8.9))
    testSelfCheckout.addItemToStore("472", testItem)

    testSelfCheckout.numberPressed(4)
    testSelfCheckout.numberPressed(7)
    testSelfCheckout.numberPressed(2)
    testSelfCheckout.enterPressed()

    assert(Math.abs(testSelfCheckout.subtotal() - 0.643) < 0.0001)
    assert(Math.abs(testSelfCheckout.tax() - 0.0572) < 0.0001)
  }

  test(" extra_penny_bottle_deposit") {
    var testSelfCheckout: SelfCheckout = new SelfCheckout()
    var testItem: Item = new Item("Cheese", 12.0)
    testItem.addModifier(new Sale(40))
    testItem.addModifier(new SalesTax(8.9))
    testItem.addModifier(new BottleDeposit(12.0))
    testSelfCheckout.addItemToStore("123", testItem)

    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(2)
    testSelfCheckout.numberPressed(3)
    testSelfCheckout.enterPressed()
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(2)
    testSelfCheckout.numberPressed(3)
    testSelfCheckout.enterPressed()
    assert(Math.abs(testSelfCheckout.subtotal() - 14.4) < 0.0001)
    assert(Math.abs(testSelfCheckout.tax() - 25.2816) < 0.0001)
  }

  test("twice new Sale") {
    var testSelfCheckout: SelfCheckout = new SelfCheckout()
    var testItem: Item = new Item("Cheese", 12.0)
    testItem.addModifier(new Sale(40))
    testItem.addModifier(new Sale(20))
    testItem.addModifier(new SalesTax(8.9))
    testItem.addModifier(new BottleDeposit(12.0))
    testSelfCheckout.addItemToStore("123", testItem)

    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(2)
    testSelfCheckout.numberPressed(3)
    testSelfCheckout.enterPressed()

    assert(Math.abs(testSelfCheckout.subtotal() - 5.76) < 0.0001)
    assert(Math.abs(testSelfCheckout.tax() - 12.5126) < 0.0001)
    assert(Math.abs(testSelfCheckout.total() - 18.2726) < 0.0001)
  }
}