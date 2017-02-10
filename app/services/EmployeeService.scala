package service

import model.{Employee, Employees}
import scala.concurrent.Future

object EmployeeService {

  def addEmployee(employee: Employee): Future[String] = {
    Employees.add(employee)
  }

  def deleteemployee(id: Long): Future[Int] = {
    Employees.delete(id)
  }

  def getEmployee(id: Long): Future[Option[Employee]] = {
    Employees.get(id)
  }

  def listAllEmployees: Future[Seq[Employee]] = {
    Employees.listAll
  }
}
