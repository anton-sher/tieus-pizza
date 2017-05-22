import scala.collection.mutable.ListBuffer
import scala.io.Source

case class CustomerOrder(sequenceNumber: Int, timeOrdered: Long, cookingDuration: Long) {}

object TieusPizza {
  def main(args: Array[String]): Unit = {
    val inputFile = args(0)

    val input = parseInput(inputFile)

    println(solve(input))
  }

  def parseInput(inputFile: String): Seq[CustomerOrder] = {
    val lines = Source.fromFile(inputFile).getLines()

    val numberOfCustomers = lines.next().toInt

    val parsedLines = lines.take(numberOfCustomers).map(_.split(" ").map(_.toLong)).toSeq

    parsedLines
      .zip(0 until numberOfCustomers)
      .map(line_number => CustomerOrder(line_number._2, line_number._1(0), line_number._1(1)))
  }

  def solve(input: Seq[CustomerOrder]): Long = {
    val ordersAsServed: Seq[CustomerOrder] = findServingOrder(input)

    calculateAverageWaitingTime(ordersAsServed)
  }

  def findServingOrder(input: Seq[CustomerOrder]): Seq[CustomerOrder] = {
    val futureCustomers: ListBuffer[CustomerOrder] = ListBuffer() ++ input
    val currentWaitingCustomers: ListBuffer[CustomerOrder] = ListBuffer()
    val servedCustomers: ListBuffer[CustomerOrder] = ListBuffer()

    var currentTime: Long = 0

    while (servedCustomers.size < input.size) {
      while (futureCustomers.nonEmpty && futureCustomers.head.timeOrdered <= currentTime) {
        currentWaitingCustomers += futureCustomers.remove(0)
      }

      if (currentWaitingCustomers.isEmpty) {
        currentTime = futureCustomers.head.timeOrdered
      } else {
        // if there are several customers waiting, one with the fastest pizza goes first. See README.md for proof.
        val nextCustomerToServe = currentWaitingCustomers.minBy(_.cookingDuration)
        currentTime += nextCustomerToServe.cookingDuration
        servedCustomers += nextCustomerToServe
        currentWaitingCustomers -= nextCustomerToServe
      }
    }
    servedCustomers
  }

  def calculateAverageWaitingTime(ordersAsServed: Seq[CustomerOrder]): Long = {
    var currentTime: Long = 0
    var totalWaitingTime: Long = 0

    ordersAsServed.foreach { order =>
      if (currentTime < order.timeOrdered) currentTime = order.timeOrdered
      currentTime += order.cookingDuration
      val servingTime = currentTime
      totalWaitingTime += (servingTime - order.timeOrdered)
    }

    totalWaitingTime / ordersAsServed.size
  }
}
