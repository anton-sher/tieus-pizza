import java.io.{File, PrintStream}

import scala.util.Random

class TieusPizzaTest extends org.scalatest.FunSuite {
  test("solve sample 00") {
    val input = Seq(CustomerOrder(0, 3), CustomerOrder(1, 9), CustomerOrder(2, 6))

    val solution = TieusPizza.solve(input)

    assert(solution == 9)
  }

  test("solve sample 01") {
    val input = Seq(CustomerOrder(0, 3), CustomerOrder(1, 9), CustomerOrder(2, 5))

    val solution = TieusPizza.solve(input)

    assert(solution == 8)
  }

  test("solving order in sample 01") {
    val input = Seq(CustomerOrder(0, 3), CustomerOrder(1, 9), CustomerOrder(2, 5))

    val servingOrder = TieusPizza.findServingOrder(input)

    assert(servingOrder == Seq(CustomerOrder(0, 3), CustomerOrder(2, 5), CustomerOrder(1, 9)))
  }

  test("degenerate case") {
    val input = Seq(CustomerOrder(1, 10))

    val solution = TieusPizza.solve(input)

    assert(solution == 10)
  }

  test("ordering time with gaps 1") {
    val input = Seq(CustomerOrder(1, 1), CustomerOrder(3, 1), CustomerOrder(5, 1))

    val solution = TieusPizza.solve(input)

    assert(solution == 1)
  }

  test("ordering time with gaps 2") {
    val input = Seq(CustomerOrder(1, 4), CustomerOrder(3, 8), CustomerOrder(5, 6))

    val solution = TieusPizza.solve(input)

    assert(solution == 8)
  }

  private val maximalTimeValue = 1000000000

  test("extreme values: everyone comes at the same time and has same cooking duration, maxed out") {
    val numberOfCustomers = 100000
    val input = Array.fill[CustomerOrder](numberOfCustomers){CustomerOrder(maximalTimeValue, maximalTimeValue)}

    val solution = TieusPizza.solve(input)

    val expectedResult = maximalTimeValue * (1 to numberOfCustomers).map(_.toLong).sum / numberOfCustomers
    assert(solution == expectedResult)
  }

  test("reading extreme values from a file shouldn't explode") {
    val numberOfCustomers = 100000
    val input = Array.fill[CustomerOrder](numberOfCustomers){CustomerOrder(maximalTimeValue, maximalTimeValue)}

    val tempFile = File.createTempFile("test", "input")
    tempFile.deleteOnExit()
    val out = new PrintStream(tempFile)

    out.println(numberOfCustomers)
    input.foreach(co => out.println(co.timeOrdered + " " + co.cookingDuration))
    out.close()

    val fromFile = TieusPizza.parseInput(tempFile.getAbsolutePath)

    assert(fromFile.toArray sameElements input)
  }

  test("random input should not crash") {
    val numberOfCustomers = 100000
    val incomingTime = Array.fill[Long](numberOfCustomers)(Random.nextInt(maximalTimeValue)).iterator
    val input = Array.fill[CustomerOrder](numberOfCustomers){CustomerOrder(incomingTime.next(), Random.nextInt(maximalTimeValue))}

    val solution = TieusPizza.solve(input)

    assert(solution > 0)
  }
}
