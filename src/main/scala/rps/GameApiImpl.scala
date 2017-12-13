package rps

import scala.util.Random
import scala.concurrent.{ExecutionContext, Future}
import model.{ Move, Result, Response}
import io.buildo.enumero.CaseEnumSerialization

class GameApiImpl(implicit ec: ExecutionContext) extends GameApi {
  
  override def play(userMove: Move): Future[Either[Throwable,Response]] = Future {
    val cpuMove: Move = getCpuMove()
    Right(Response(userMove, cpuMove, getOutcome(userMove, cpuMove)))
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
