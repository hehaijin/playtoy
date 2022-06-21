package models

import slick.lifted.TableQuery

import java.sql.Date
import scala.concurrent.Future
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._


case class Employee( val firstName: String, val lastName: String)




