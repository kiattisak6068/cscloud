package controllers

import model.{Employee, EmployeeForm}
import play.api.mvc._
import scala.concurrent.Future
import service.EmployeeService
import scala.concurrent.ExecutionContext.Implicits.global

class CscloudstorageController extends Controller {

  def index = Action.async { implicit request =>
    EmployeeService.listAllEmployees map { employees =>
      Ok(views.html.test(EmployeeForm.form, employees))
    }
  }

  def addEmployee() = Action.async { implicit request =>
    EmployeeForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok(views.html.test(errorForm, Seq.empty[Employee]))),
      data => {
        val newEmployee = Employee(0, data.firstName, data.lastName, data.username, data.password)
        EmployeeService.addEmployee(newEmployee).map(res =>
          Redirect(routes.CscloudstorageController.index())
        )
      })
  }

  def deleteEmployee(id: Long) = Action.async { implicit request =>
    EmployeeService.deleteemployee(id) map { res =>
      Redirect(routes.CscloudstorageController.index())
    }
  }

}

