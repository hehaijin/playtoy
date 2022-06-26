package services
import models.{Employee, Salary}
import sangria.macros.derive.{ObjectTypeDescription, deriveObjectType}
import sangria.schema._

object SchemaDefinition {


  val EmployeeType: ObjectType[Unit, Employee] = deriveObjectType[Unit, Employee](
    ObjectTypeDescription("Employee type")
  )

  val SalaryType: ObjectType[Unit, Salary] = deriveObjectType[Unit, Salary](
    ObjectTypeDescription("Salary type")
  )

  val QueryType= ObjectType("Query", fields[Repo, Unit](
    Field("employees", ListType(EmployeeType), resolve =_.ctx.listEmployees, description = Some("List of employees")),
    Field("salaries", ListType(SalaryType), resolve =_.ctx.listSalaries, description = Some("List of salaries")),
  ))


  var schema= Schema(QueryType);
}

