package rps

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import wiro.Config
import wiro.server.akkaHttp._
import wiro.server.akkaHttp.FailSupport._
import rps.model._
import de.heikoseeberger.akkahttpcirce.ErrorAccumulatingCirceSupport._
import io.circe.generic.auto._
import io.buildo.enumero.circe._

object GameServer extends App with RouterDerivationModule {
    
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  implicit def throwableResponse: ToHttpResponse[Throwable] = null
  val gameRouter = deriveRouter[GameApi](new GameApiImpl)

  val rpcServer = new HttpRPCServer(
    config = Config("localhost", 8080),
    routers = List(gameRouter)
  )
}
