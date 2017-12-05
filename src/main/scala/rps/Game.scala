package rps

object Game {
  def play(): Unit = {
    println("Please select your move: input 0 for Rock, 1 for Paper and 2 for Scissors")
    val userInput = scala.io.StdIn.readInt()
    val userMove = matchInput(userInput)
    val cpuMove = getCpuMove()
    println("User move is "+getMoveString(userMove))
    println("CPU move is "+getMoveString(cpuMove))
    println(getOutcome(userMove, cpuMove))
  }
 
  def matchInput(x:Int): Move = {
    x match {
      case 0 => Move.ROCK
      case 1 => Move.PAPER
      case 2 => Move.SCISSORS
      case _ => Move.NOT_ALLOWED
    }
  }

  def getMoveString(x:Move): String = {
    x match {
      case Move.ROCK => "Rock"
      case Move.PAPER => "Paper"
      case Move.SCISSORS => "Scissors"
      case Move.NOT_ALLOWED => "not allowed"
    }
  }

  def getCpuMove(): Move = {
    val r = scala.util.Random
    val input = r.nextInt(3)
    matchInput(input)
  }

  def getOutcome(user:Move, cpu:Move): String = {
    (user,cpu) match {
      case (Move.ROCK, Move.SCISSORS) => "User wins!"
      case (Move.PAPER, Move.ROCK) => "User wins!"
      case (Move.SCISSORS, Move.PAPER) => "User wins!"
      case (Move.NOT_ALLOWED, _) => "Input is not valid"
      case (x, y) if x == y => "Draw"
      case _ => "CPU wins!"
    }
  }
}
