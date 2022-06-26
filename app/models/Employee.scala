package models

import slick.lifted.TableQuery

import java.sql.Date
import scala.concurrent.Future
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._


case class Employee(val employeeNo : Int , val firstName: String, val lastName: String)

class EmployeeTableDef(tag: Tag) extends Table[Employee](tag, None, "employees") {
  def employeeNo= column[Int]("emp_no");
  def firstName= column[String]("first_name")
  def lastName= column[String]("last_name")
  override  def * = (employeeNo,firstName, lastName) <> (Employee.tupled, Employee.unapply)
}





