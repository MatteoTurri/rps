package rps

import io.buildo.enumero._

object Game {
  def play(): Unit = {
    println("Please select your move: input 0 for Rock, 1 for Paper and 2 for Scissors")
    val userInput: String = scala.io.StdIn.readLine();
    val userMove: Option[Move] = matchInput(userInput)
    val cpuMove: Move = getCpuMove()
    userMove.map(x => getOutcome(x, cpuMove)).getOrElse(println("User input is not valid"))
  }
 
  private def matchInput(x: String): Option[Move] = {
    implicitly[CaseEnumIndex[Move]].caseFromIndex(x)
  }

  private def getMoveString(x: Move): String = {
    implicitly[CaseEnumSerialization[Move]].caseToString(x)
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
