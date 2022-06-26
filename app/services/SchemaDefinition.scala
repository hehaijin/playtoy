package services
import models.Employee
import sangria.macros.derive.{ObjectTypeDescription, deriveObjectType}
import sangria.schema._

object SchemaDefinition {


  val EmployeeType: ObjectType[Unit, Employee] = deriveObjectType[Unit, Employee](
    ObjectTypeDescription("Employee type")
  )

  val QueryType= ObjectType("Query", fields[Repo, Unit](
    Field("employees", ListType(EmployeeType), resolve =_.ctx.listEmployees, description = Some("List of employees"))
  ))


  var schema= Schema(QueryType);
}

