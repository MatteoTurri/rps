package rps

import scala.concurrent.Future
import wiro.annotation._
import model.{Move, Response}

@path("rps")
trait GameApi {

  @command
  def play(
    userMove: Move
  ): Future[Either[Throwable,Response]]
}
