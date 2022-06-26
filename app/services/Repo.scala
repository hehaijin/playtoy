package services

import models.{EmployeeTableDef, SalaryTableDef}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery

import javax.inject.Inject
import slick.jdbc.MySQLProfile.api._

class Repo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  val employees= TableQuery[EmployeeTableDef]
  val salaries= TableQuery[SalaryTableDef]

  def getAll = {
    val employeesAndSalaries = for {
      emp <- employees
      sal <- salaries if emp.employeeNo === sal.employeeNo && sal.salary > 100000
    } yield (emp.employeeNo, emp.firstName, emp.lastName, sal.salary)
    db.run(employeesAndSalaries.sortBy(_._4.desc).take(15).result)
  }

  def listEmployees= {
    db.run(employees.take(10).result)
  }

}
