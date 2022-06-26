package models

import slick.lifted.TableQuery

import java.sql.Date
import scala.concurrent.Future
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._


case class Salary(val employeeNo : Int , val salary: Int)

class SalaryTableDef(tag: Tag) extends Table[Salary](tag, None, "salaries") {
  def employeeNo= column[Int]("emp_no");
  def salary= column[Int]("salary")
  override  def * = (employeeNo,salary) <> (Salary.tupled, Salary.unapply)
}


