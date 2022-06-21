package models

import slick.lifted.TableQuery
import slick.jdbc.MySQLProfile.api._

object Employees2{
  val db= Database.forConfig("mysql");
  val employees= TableQuery[EmployeeTableDef]

  def listAll = {
    db.run(employees.result)
  }

}

