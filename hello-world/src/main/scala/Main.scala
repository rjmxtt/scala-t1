import java.io._

object Demo {
   def main(args: Array[String]) {
      // val d = new Draw()
      val s = new System()

      // s.newDraw(10004)

      s.enter(10,0)
      s.enter(11,1)
      s.enter(12,2)
      s.enter(13,3)
      
      s.playToday()

      s.result(100003,12)

      // s.newDraw()
      // d.play
   }
}

class System() {
  var lottery: Map[Int,Draw] = Map(
    100001 -> new Draw(),
    100002 -> new Draw(),
    100003 -> new Draw()
  )

  def newDraw(date:Int) = {
    lottery += (date -> new Draw())
    println(lottery)
  }
  
  def enter(id: Int, num: Int) {
    val t = new Ticket(id,num)
    lottery.last._2.enter(t)
    println("Draw->"+lottery.last._2.tickets)
  }

  def playToday() {
    lottery.last._2.play()
    println(lottery.last._2.randomInt + " - " + lottery.last._2.winners)
  }

  def result(date:Int,customer:Int) {
    val ret = lottery.get(date).get.winners.contains(customer)
    println(ret)
  }
}

class Draw() {
  var tickets: List[Ticket] = List()
  var winners: List[Int] = List(8)

  // val date: Int = pdate
  val random = scala.util.Random
  val randomInt: Int = random.nextInt(3)
  // println(randomInt)

  def play() {
    winners = tickets.filter( _.number == randomInt ).map(x => x.customer)
    // println(winners)
  }

  def enter(pticket: Ticket) {
    tickets = pticket :: tickets
    // println(tickets)
  }
}

class Ticket(
  val customer: Int,
  val number: Int
)