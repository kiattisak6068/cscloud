package model

import play.api.Play
import play.api.data.Form
import play.api.data.Forms._
import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.Future
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import scala.concurrent.ExecutionContext.Implicits.global

case class Employee(id: Long, firstName: String, lastName: String, username: String, password: String)

case class EmployeeFormData(firstName: String, lastName: String, username: String, password: String)

object EmployeeForm {

  val form = Form(
    mapping(
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText,
      "username" -> nonEmptyText,
      "password" -> nonEmptyText
    )(EmployeeFormData.apply)(EmployeeFormData.unapply)
  )
}

class EmployeeTableDef(tag: Tag) extends Table[Employee](tag, "employee") {

  def id = column[Long]("id", O.PrimaryKey,O.AutoInc)
  def firstName = column[String]("first_name")
  def lastName = column[String]("last_name")
  def username = column[String]("username")
  def password = column[String]("password")

  override def * =
    (id, firstName, lastName, username, password) <>(Employee.tupled, Employee.unapply)
}

object Employees {

  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)

  val employees = TableQuery[EmployeeTableDef]

  def add(employee: Employee): Future[String] = {
    dbConfig.db.run(employees += employee).map(res => "Employee successfully added").recover {
      case ex: Exception => ex.getCause.getMessage
    }
  }

  def delete(id: Long): Future[Int] = {
    dbConfig.db.run(employees.filter(_.id === id).delete)
  }

  def get(id: Long): Future[Option[Employee]] = {
    dbConfig.db.run(employees.filter(_.id === id).result.headOption)
  }

  def listAll: Future[Seq[Employee]] = {
    dbConfig.db.run(employees.result)
  }

}
