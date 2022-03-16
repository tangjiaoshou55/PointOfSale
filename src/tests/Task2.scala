package tests
import org.scalatest.FunSuite
import store.model.checkout.SelfCheckout
import store.model.items.{BottleDeposit, Item, Sale, SalesTax}
import store.view.SelfCheckoutGUI
class Task2 extends FunSuite {
  test("item test") {
    //    var testSelfCheckout: SelfCheckout = new SelfCheckout()
    //
    //    var testItem: Item = new Item("test item", 100.0)
    //
    //    testSelfCheckout.addItemToStore("123", testItem)
    // TODO
    var descriptions:List[String]=List()
    var prices:List[Double]=List()
    descriptions=descriptions:+"My description"
    prices=prices:+5.99
    descriptions=descriptions:+"Oreos"
    prices=prices:+4.50
    var items:List[Item]=List()
    for (index <- descriptions.indices){
      items=items:+new Item(descriptions(index),prices(index))

      assert(items(index).description()==descriptions(index),"testing description "+
        "explected: "+descriptions(index)+" actually got: "+items(index).description())
      assert(Math.abs(items(index).price()-prices(index))<.0001,"testing constructor "+
        "expected: "+prices(index)+" got: "+items(index).price())
      items(index).setBasePrice(prices(index)-5)
      assert(Math.abs(items(index).price()-(prices(index)-5))<.0001,"testing setBasePrice "+
        "expected: "+(prices(index)-5)+" got: "+items(index).price())
    }

  }
  test(" normal") {
    val checkout: SelfCheckout = new SelfCheckout
    var testItem2: Item = new Item("orange", 10.0)
    testItem2.addModifier(new Sale(20.0))
    testItem2.addModifier(new SalesTax(10.0))
    checkout.addItemToStore("125", testItem2)
    checkout.numberPressed(1)

    checkout.numberPressed(2)
    checkout.numberPressed(5)
    checkout.enterPressed()
    checkout.numberPressed(1)
    checkout.numberPressed(2)
    checkout.numberPressed(5)
    checkout.enterPressed()
    assert(Math.abs(checkout.subtotal() - 16) < 0.0001)
    assert(Math.abs(checkout.tax() - 1.6) < 0.0001)
  }
  test("sale twice") {
    val checkout: SelfCheckout = new SelfCheckout
    var testItem2: Item = new Item("orange", 10.0)
    testItem2.addModifier(new Sale(20.0))
    testItem2.addModifier(new Sale(10.0))
    testItem2.addModifier(new SalesTax(10.0))
    checkout.addItemToStore("072", testItem2)
    checkout.numberPressed(0)

    checkout.numberPressed(7)
    checkout.numberPressed(2)
    checkout.enterPressed()
    checkout.numberPressed(0)
    checkout.numberPressed(7)
    checkout.numberPressed(2)
    checkout.enterPressed()
    assert(Math.abs(checkout.subtotal() - 14.4) < 0.0001)
    assert(Math.abs(checkout.tax() - 1.44) < 0.00001)
  }
  test(" extra_penny_bottle_deposit") {
    val checkout: SelfCheckout = new SelfCheckout
    var testItem2: Item = new Item("big", 10.0)
    testItem2.addModifier(new Sale(20.0))
    testItem2.addModifier(new SalesTax(10.0))
    testItem2.addModifier(new BottleDeposit(10.0))
    checkout.addItemToStore("072", testItem2)
    checkout.numberPressed(0)

    checkout.numberPressed(7)
    checkout.numberPressed(2)
    checkout.enterPressed()
    checkout.numberPressed(0)
    checkout.numberPressed(7)
    checkout.numberPressed(2)
    checkout.enterPressed()
    assert(Math.abs(checkout.subtotal() - 16.0) < 0.0001)
    assert(Math.abs(checkout.tax() - 21.6) < 0.0001)
  }
}
