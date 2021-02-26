import java.io._

object Main {
   def main(args: Array[String]) {

    if ( args.length == 0 ) {
      println("query result : run result <date> <customer id>")
      println("count wins on day : run countWins <date>")
    } else {
      var s = new System() 
      val random = scala.util.Random

      for ( a <- 1 to 4 ) {
        s.newDraw(a)
        for ( b <- 1 to 99999 ) {
          s.enter( b,  random.nextInt(999) )
        }
        s.playDate( a )
      }

      if (args(0) == "result") {
        s.result( args(1).toInt , args(2).toInt )
      }

      if (args(0) == "countWins") {
        println( s.lottery.get( args(1).toInt ).get.winners.length )
      }
    }
   }
}

class System() {
  var lottery: Map[Int,Draw] = Map()

  def newDraw(date:Int) = {
    lottery += (date -> new Draw())
    // println(lottery)
  }
  
  def enter(id: Int, num: Int) {
    val t = new Ticket(id,num)
    lottery.last._2.enter(t)
    // println("Draw->"+lottery.last._2.tickets)
  }

  def playToday() {
    lottery.last._2.play()
    // println(lottery.last._2.randomInt + " - " + lottery.last._2.winners)
  }

  def playDate(date:Int) {
    lottery.get(date).get.play()
    // println(date)
  }

  def result(date:Int,customer:Int) {
    val ret = lottery.get(date).get.winners.contains(customer)
    println(ret)
  }
}

class Draw() {
  var tickets: List[Ticket] = List()
  var winners: List[Int] = List()

  val random = scala.util.Random
  val randomInt: Int = random.nextInt(3)

  def play() {
    winners = tickets.filter( _.number == randomInt ).map(x => x.customer)
  }

  def enter(pticket: Ticket) {
    tickets = pticket :: tickets
  }
}

class Ticket(
  val customer: Int,
  val number: Int
)
