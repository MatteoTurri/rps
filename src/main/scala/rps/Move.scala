package rps

sealed trait Move
object Move {
  case object ROCK extends Move
  case object PAPER extends Move
  case object SCISSORS extends Move
  case object NOT_ALLOWED extends Move
}
