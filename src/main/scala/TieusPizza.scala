import scala.io.Source

case class ProblemInput(numberOfCustomers: Int, timeOrdered: Array[Int], timeToCook: Array[Int]) {}

object TieusPizza {
  def main(args: Array[String]): Unit = {
    val inputFile = args(0)

    val input = parseInput(inputFile)

    println(solve(input))
  }

  def parseInput(inputFile: String): ProblemInput = {
    val lines = Source.fromFile(inputFile).getLines()

    val numberOfCustomers = lines.next().toInt

    val parsedLines = lines.take(numberOfCustomers).map(_.split(" ").map(_.toInt)).toSeq

    val timeOrdered = parsedLines.map(_ (0))
    val timeToCook = parsedLines.map(_ (1))

    ProblemInput(numberOfCustomers, timeOrdered.toArray, timeToCook.toArray)
  }

  def solve(input: ProblemInput): Int = {
    val servedCustomers: Seq[Int] = findServingOrder(input)

    calculateAverageWaitingTime(input, servedCustomers)
  }

  def findServingOrder(input: ProblemInput): Seq[Int] = {
    var currentWaitingCustomers: Seq[Int] = Seq()
    var servedCustomers: Seq[Int] = Seq()

    var currentTime = 0

    while (servedCustomers.size < input.numberOfCustomers) {
      // TODO this isn't efficient
      currentWaitingCustomers = (0 until input.numberOfCustomers)
        .diff(servedCustomers)
        .filter(c => input.timeOrdered(c) <= currentTime)

      if (currentWaitingCustomers.isEmpty) {
        currentTime += 1
      } else {
        // if there are several customers waiting, one with the fastest pizza goes first. See README.md for proof.
        val nextCustomerToServe = currentWaitingCustomers.minBy(c => input.timeToCook(c))
        currentTime += input.timeToCook(nextCustomerToServe)
        servedCustomers = servedCustomers ++ Seq(nextCustomerToServe)
      }
    }
    servedCustomers
  }

  def calculateAverageWaitingTime(input: ProblemInput, servingOrder: Seq[Int]): Int = {
    var currentTime = 0
    var totalWaitingTime = 0

    servingOrder.foreach { customer =>
      currentTime += input.timeToCook(customer)
      val servingTime = currentTime
      totalWaitingTime += (servingTime - input.timeOrdered(customer))
    }

    totalWaitingTime / input.numberOfCustomers
  }
}
