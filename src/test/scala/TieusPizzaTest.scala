class TieusPizzaTest extends org.scalatest.FunSuite {
  test("solve sample 00") {
    val input = Seq(CustomerOrder(0, 0, 3), CustomerOrder(1, 1, 9), CustomerOrder(2, 2, 6))

    val solution = TieusPizza.solve(input)

    assert(solution == 9)
  }

  test("solve sample 01") {
    val input = Seq(CustomerOrder(0, 0, 3), CustomerOrder(1, 1, 9), CustomerOrder(2, 2, 5))

    val solution = TieusPizza.solve(input)

    assert(solution == 8)
  }

  test("solving order in sample 01") {
    val input = Seq(CustomerOrder(0, 0, 3), CustomerOrder(1, 1, 9), CustomerOrder(2, 2, 5))

    val servingOrder = TieusPizza.findServingOrder(input)

    assert(servingOrder.map(_.sequenceNumber) == Seq(0, 2, 1))
  }

  test("degenerate case") {
    val input = Seq(CustomerOrder(0, 0, 10))

    val solution = TieusPizza.solve(input)

    assert(solution == 10)
  }

  test("ordering time with gaps 1") {
    val input = Seq(CustomerOrder(0, 1, 1), CustomerOrder(1, 3, 1), CustomerOrder(2, 5, 1))

    val solution = TieusPizza.solve(input)

    assert(solution == 1)
  }

  test("ordering time with gaps 2") {
    val input = Seq(CustomerOrder(0, 1, 4), CustomerOrder(1, 3, 8), CustomerOrder(2, 5, 6))

    val solution = TieusPizza.solve(input)

    assert(solution == 8)
  }

  test("extreme values: everyone comes at the same time and has same cooking duration, maxed out") {
    var nr = 0
    val numberOfCustomers = 100000
    val input = Array.fill[CustomerOrder](numberOfCustomers){val o = CustomerOrder(nr, 1000000000, 1000000000); nr += 1; o}

    val solution = TieusPizza.solve(input)

    val expectedResult = 1000000000 * (1 to numberOfCustomers).map(_.toLong).sum / numberOfCustomers
    assert(solution == expectedResult)
  }
}
