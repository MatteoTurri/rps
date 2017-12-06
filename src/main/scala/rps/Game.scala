package rps

object Game {
  def play(): Unit = {
    println("Please select your move: input 0 for Rock, 1 for Paper and 2 for Scissors")
    val userInput: String = scala.io.StdIn.readLine();
    val userMove: Option[Move] = matchInput(userInput)
    val cpuMove: Move = getCpuMove()
    if(userMove.isDefined)
      getOutcome(userMove.get, cpuMove)
    else
      println("User input is not valid")
  }
 
  private def matchInput(x: String): Option[Move] = {
    x match {
      case "0" => Some(Move.Rock)
      case "1" => Some(Move.Paper)
      case "2" => Some(Move.Scissors)
      case _ => None
    }
  }

  private def getMoveString(x: Move): String = {
    x match {
      case Move.Rock => "Rock"
      case Move.Paper => "Paper"
      case Move.Scissors => "Scissors"
    }
  }

  private def getCpuMove(): Move = {
    val r = scala.util.Random
    val input: String = r.nextInt(3).toString
    matchInput(input).get
  }

  private def getOutcome(user: Move, cpu: Move): Unit = {
    println("User move is "+getMoveString(user))
    println("CPU move is "+getMoveString(cpu))
    (user,cpu) match {
      case (Move.Rock, Move.Scissors) | (Move.Paper, Move.Rock) | (Move.Scissors, Move.Paper) => println("User wins!")
      case (x, y) if x == y => println("Draw")
      case _ => println("CPU wins!")
    }
  }
}
