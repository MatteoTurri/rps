package rps

object Game {
  def play(): Unit = {
    println("Please select your move: input 0 for Rock, 1 for Paper and 2 for Scissors")
    val userInput: String = scala.io.StdIn.readLine();
    val userMove: Option[Move] = matchInput(userInput)
    val cpuMove: Option[Move] = getCpuMove()
    println("User move is "+getMoveString(userMove))
    println("CPU move is "+getMoveString(cpuMove))
    println(getOutcome(userMove, cpuMove))
  }
 
  private def matchInput(x: String): Option[Move] = {
    x match {
      case "0" => Some(Move.ROCK)
      case "1" => Some(Move.PAPER)
      case "2" => Some(Move.SCISSORS)
      case _ => None
    }
  }

  private def getMoveString(x: Option[Move]): String = {
    x match {
      case Some(Move.ROCK) => "Rock"
      case Some(Move.PAPER) => "Paper"
      case Some(Move.SCISSORS) => "Scissors"
      case None => "not allowed"
    }
  }

  private def getCpuMove(): Option[Move] = {
    val r = scala.util.Random
    val input: String = r.nextInt(3).toString
    matchInput(input)
  }

  private def getOutcome(user: Option[Move], cpu: Option[Move]): String = {
    (user,cpu) match {
      case (Some(Move.ROCK), Some(Move.SCISSORS)) => "User wins!"
      case (Some(Move.PAPER), Some(Move.ROCK)) => "User wins!" 
      case (Some(Move.SCISSORS), Some(Move.PAPER)) => "User wins!"
      case (None, _) => "Input is not valid"
      case (x, y) if x == y => "Draw"
      case _ => "CPU wins!"
    }
  }
}
