package org.openapitools.server.api

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.marshalling.ToEntityMarshaller
import akka.http.scaladsl.unmarshalling.FromEntityUnmarshaller
import akka.http.scaladsl.unmarshalling.FromStringUnmarshaller
import org.openapitools.server.AkkaHttpHelper._
import java.time.LocalDate
import org.openapitools.server.model.Student


class StudentsApi(
    studentsService: StudentsApiService,
    studentsMarshaller: StudentsApiMarshaller
) {

  
  import studentsMarshaller._

  lazy val route: Route =
    path("students" / Segment / Segment / Segment / Segment) { (lastname, firstname, address, birthdate) => 
      post {  
            studentsService.createStudents(lastname = lastname, firstname = firstname, address = address, birthdate = birthdate)
      }
    } ~
    path("students") { 
      get { 
        parameters("limit".as[Int].?) { (limit) => 
            studentsService.listStudents(limit = limit)
        }
      }
    } ~
    path("students" / Segment) { (studentId) => 
      get {  
            studentsService.showStudentById(studentId = studentId)
      }
    } ~
    path("students" / Segment / Segment / Segment / Segment / Segment) { (lastname, firstname, address, birthdate, studentId) => 
      put {  
            studentsService.updateStudent(lastname = lastname, firstname = firstname, address = address, birthdate = birthdate, studentId = studentId)
      }
    }
}


trait StudentsApiService {

  def createStudents200(responseStudentarray: List[Student])(implicit toEntityMarshallerStudentarray: ToEntityMarshaller[List[Student]]): Route =
    complete((200, responseStudentarray))
  /**
   * Code: 200, Message: student, DataType: List[Student]
   */
  def createStudents(lastname: String, firstname: String, address: String, birthdate: String)
      (implicit toEntityMarshallerStudentarray: ToEntityMarshaller[List[Student]]): Route

  def listStudents200(responseStudentarray: List[Student])(implicit toEntityMarshallerStudentarray: ToEntityMarshaller[List[Student]]): Route =
    complete((200, responseStudentarray))
  /**
   * Code: 200, Message: An paged array of students, DataType: List[Student]
   */
  def listStudents(limit: Option[Int])
      (implicit toEntityMarshallerStudentarray: ToEntityMarshaller[List[Student]]): Route

  def showStudentById200(responseStudent: Student)(implicit toEntityMarshallerStudent: ToEntityMarshaller[Student]): Route =
    complete((200, responseStudent))
  /**
   * Code: 200, Message: Expected response to a valid request, DataType: Student
   */
  def showStudentById(studentId: String)
      (implicit toEntityMarshallerStudent: ToEntityMarshaller[Student]): Route

  def updateStudent200(responseStudent: Student)(implicit toEntityMarshallerStudent: ToEntityMarshaller[Student]): Route =
    complete((200, responseStudent))
  /**
   * Code: 200, Message: Expected response to a valid request, DataType: Student
   */
  def updateStudent(lastname: String, firstname: String, address: String, birthdate: String, studentId: String)
      (implicit toEntityMarshallerStudent: ToEntityMarshaller[Student]): Route

}

trait StudentsApiMarshaller {


  implicit def toEntityMarshallerStudentarray: ToEntityMarshaller[List[Student]]

  implicit def toEntityMarshallerStudent: ToEntityMarshaller[Student]

}

