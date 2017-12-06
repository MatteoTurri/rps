package rps

sealed trait Move
object Move {
  case object Rock extends Move
  case object Paper extends Move
  case object Scissors extends Move
}
