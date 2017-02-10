package model

import play.api.Play
import play.api.data.Form
import play.api.data.Forms._
import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.Future
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import scala.concurrent.ExecutionContext.Implicits.global

case class Usercs(username: String, password: String, first_name: String, last_name: String, status: String)

case class UsercsFormData(username: String, password: String, first_name: String, last_name: String, status: String)

object UsercsForm {

  val form = Form(
    mapping(
      "username" -> nonEmptyText,
      "password" -> nonEmptyText,
      "first_name" -> nonEmptyText,
      "last_name" -> nonEmptyText,
      "status" -> nonEmptyText
    )(UsercsFormData.apply)(UsercsFormData.unapply)
  )
}

class UsercsTableDef(tag: Tag) extends Table[Usercs](tag, "usercs") {

  def username = column[String]("username", O.PrimaryKey)
  def password = column[String]("password")
  def first_name = column[String]("first_name")
  def last_name = column[String]("last_name")
  def status = column[String]("status")

  override def * =
    (username,password, first_name, last_name, status) <>(Usercs.tupled, Usercs.unapply)
}