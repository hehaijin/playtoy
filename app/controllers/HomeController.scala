package controllers
import play.api.libs.json.{JsObject, JsString, JsValue}

import javax.inject._
import play.api.mvc._
import sangria.execution.Executor
import sangria.macros.LiteralGraphQLStringContext
import sangria.parser.QueryParser
import services.{Repo, SchemaDefinition}
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}
import sangria.marshalling.playJson._

// import sangria.marshalling.sprayJson.{SprayJsonInputUnmarshaller, SprayJsonResultMarshaller, SprayJsonMarshallerForType}
import sangria.marshalling.InputUnmarshaller
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val repo: Repo,  cc: ControllerComponents)(implicit ec: ExecutionContext)
  extends AbstractController(cc)  {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action.async { implicit request: Request[AnyContent] =>
    repo.getAll.map(list => Ok(views.html.index(list)))
  }


  def graphqlHandlerStatic = Action.async { implicit request =>
    val query = graphql"{ employees{employeeNo}  }"
    val result = Executor.execute(SchemaDefinition.schema,  query, repo)
    result.map(r => Ok(r.toString))
  }

  def graphqlHandler = Action.async (parse.json)  { implicit request =>

    val JsObject(fields) = request.body
    val JsString(query) = fields("query")
    val vars = fields.get("variables") match {
      case Some(obj: JsObject) => obj
      case _ => JsObject.empty
   }
    val operation = fields.get("operationName")  collect {
      case JsString(op) => op
    }

    QueryParser.parse(query) match {
      case Success(queryAst) =>
        Executor.execute(SchemaDefinition.schema, queryAst, repo, operationName = operation, variables = vars)
          .map( r => Ok(r.toString))
      case Failure(error) =>
        Future.successful(BadRequest(error.getMessage))
    }
  }


}

