package rps

import scala.util.Random
import model.{ Move, Result, Response}
import io.buildo.enumero.CaseEnumSerialization

object Game {
  def play(userMove: Move): Response = {
    val cpuMove: Move = getCpuMove()
    Response(userMove, cpuMove, getOutcome(userMove, cpuMove))
  }

  private def getCpuMove(): Move = {
    Random.shuffle(CaseEnumSerialization[Move].values).head
  }

  private def getOutcome(user: Move, cpu: Move): Result = {
    (user,cpu) match {
      case (Move.Rock, Move.Scissors) | (Move.Paper, Move.Rock) | (Move.Scissors, Move.Paper) => Result.Win
      case (x, y) if x == y => Result.Draw
      case _ => Result.Lose
    }
  }
}
