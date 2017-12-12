package rps

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.{ ActorMaterializer, Materializer }
import scala.io.StdIn
import model._
import de.heikoseeberger.akkahttpcirce.ErrorAccumulatingCirceSupport._
import io.circe.generic.auto._
import io.buildo.enumero.circe._

object Webserver {
  def main(args: Array[String]): Unit = {
    
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()

    Http().bindAndHandle(route, "localhost", 8080)
    StdIn.readLine("Press Return to exit")
    system.terminate()
  }

  private def route (implicit materializer: Materializer) = {

    pathPrefix("rps") {
      path("play"){
        post {
          entity(as[Request]) { request =>
            complete {
              Game.play(request.userMove)
            }
          }
        }
      }
    }
  } ~ options (complete())

}
