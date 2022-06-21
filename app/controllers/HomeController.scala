package controllers

import models.{EmployeeTableDef, Employees2}
import models.Employees2.employees

import javax.inject._
import play.api._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc._
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery
import scala.concurrent.ExecutionContext
import slick.jdbc.MySQLProfile.api._


/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, val cc: ControllerComponents)(implicit ec: ExecutionContext)
  extends AbstractController(cc) with HasDatabaseConfigProvider[JdbcProfile] {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action.async { implicit request: Request[AnyContent] =>

    val employees= TableQuery[EmployeeTableDef]
    def all = db.run(employees.result)
  //  val employeesResult= Employees2.listAll
    all.map(employees=> Ok(views.html.index(employees)))
   // Ok(views.html.index())
  }
}
