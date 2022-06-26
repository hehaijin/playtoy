package controllers
import javax.inject._
import play.api.mvc._
import sangria.execution.Executor
import sangria.macros.LiteralGraphQLStringContext
import services.{Repo, SchemaDefinition}

import scala.concurrent.ExecutionContext


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


  def graphqlHandler = Action.async { implicit request =>
    val query = graphql"{ employees{employeeNo}  }"
    val result = Executor.execute(SchemaDefinition.schema,  query, repo)
    result.map(r => Ok(r.toString))
  }

}
