package models

import com.google.inject.Inject
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._
import scala.concurrent.{ExecutionContext, Future}


class EmployeeTableDef(tag: Tag) extends Table[Employee](tag, None, "employees") {
 // def employeeNo= column[Int]("employee_no");
  def firstName= column[String]("first_name")
  def lastName= column[String]("last_name")
  override  def * = (firstName, lastName) <> (Employee.tupled, Employee.unapply)
}
