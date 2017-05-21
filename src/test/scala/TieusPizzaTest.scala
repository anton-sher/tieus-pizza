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

  test("degenerate case") {
    val input = ProblemInput(1, Array(0), Array(10))

    val solution = TieusPizza.solve(input)

    assert(solution == 10)
  }

  test("ordering time with gaps 1") {
    val input = ProblemInput(3, Array(1, 3, 5), Array(1, 1, 1))

    val solution = TieusPizza.solve(input)

    assert(solution == 1)
  }

  test("ordering time with gaps 2") {
    val input = ProblemInput(3, Array(1, 3, 5), Array(4, 8, 6))

    val solution = TieusPizza.solve(input)

    assert(solution == 8)
  }

  test("extreme values") {
    val input = ProblemInput(100000, Array.fill[Long](100000)(1000000000), Array.fill[Long](100000)(1000000000))

    val solution = TieusPizza.solve(input)

    assert(solution == 1000000000)
  }
}
