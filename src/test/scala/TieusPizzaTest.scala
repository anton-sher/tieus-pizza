class TieusPizzaTest extends org.scalatest.FunSuite {
  test("solve sample 00") {
    val input = ProblemInput(3, Array(0, 1, 2), Array(3, 9, 6))

    val solution = TieusPizza.solve(input)

    assert(solution == 9)
  }

  test("solve sample 01") {
    val input = ProblemInput(3, Array(0, 1, 2), Array(3, 9, 5))

    val solution = TieusPizza.solve(input)

    assert(solution == 8)
  }

  test("solving order in sample 01") {
    val input = ProblemInput(3, Array(0, 1, 2), Array(3, 9, 5))

    val servingOrder = TieusPizza.findServingOrder(input)

    assert(servingOrder == Seq(0, 2, 1))
  }
}
