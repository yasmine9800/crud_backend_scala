import Main.{data, system}
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshalling.ToEntityMarshaller
import org.openapitools.server.api.{StudentsApi, StudentsApiMarshaller, StudentsApiService}
import org.openapitools.server.model.Student
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{complete, pathPrefix}
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import org.openapitools.server.data.DataInformation
import spray.json.DefaultJsonProtocol._

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.io.StdIn


object Main extends App {

  // needed to run the route
  implicit val system = ActorSystem("the-online-bar")

  implicit val materializer = ActorMaterializer()
  // needed for the future map/flatmap in the end and future in fetchItem and saveOrder
  implicit val executionContext = system.dispatcher

  val data = new DataInformation(system)

  object DefaultMarshaller extends StudentsApiMarshaller {
    override implicit def toEntityMarshallerStudentarray: ToEntityMarshaller[List[Student]] = listFormat(jsonFormat5(Student))

    override implicit def toEntityMarshallerStudent: ToEntityMarshaller[Student] = jsonFormat5(Student)
  }

  object DefaultService extends StudentsApiService {
    /**
     * Code: 200, Message: student, DataType: List[Student]
     */
    override def createStudents(lastname: String, firstname: String, address: String, birthdate: String)(implicit toEntityMarshallerStudentarray: ToEntityMarshaller[List[Student]]): Route ={
      val int = (data.listEtudiant.toStream.map(e=>e.id).toList.max)+1;
      val studiant1 = new Student(int,lastname,firstname,address,birthdate);

      data.listEtudiant = studiant1::  data.listEtudiant
      complete(StatusCodes.Created, data.listEtudiant)
    }

    /**
     * Code: 200, Message: An paged array of students, DataType: List[Student]
     */
    override def listStudents(limit: Option[Int])(implicit toEntityMarshallerStudentarray: ToEntityMarshaller[List[Student]]): Route ={
      if(limit!=null && !limit.isEmpty) {
        complete(StatusCodes.OK, data.listEtudiant.toStream.slice(0,limit.get).toList)
      }else{
        complete(StatusCodes.OK, data.listEtudiant)
      }

    }
     /** Code: 200, Message: Expected response to a valid request, DataType: Student
     */
    override def showStudentById(studentId: String)(implicit toEntityMarshallerStudent: ToEntityMarshaller[Student]): Route = {
      val student = data.listEtudiant.toStream.find(e=>e.id==studentId.toLong)
      if (!student.isEmpty)
        complete(StatusCodes.OK, student.get)
      else
        complete(StatusCodes.NotFound, "get - No student found")
    }

    /**
     * Code: 200, Message: Expected response to a valid request, DataType: Student
     */
    override def updateStudent(lastname: String, firstname: String, address: String, birthdate: String, studentId: String)(implicit toEntityMarshallerStudent: ToEntityMarshaller[Student]): Route = {
      val student = data.listEtudiant.toStream.find(e=>e.id==studentId.toLong)
      if (!student.isEmpty) {
        data.listEtudiant.dropWhile(e=>e.id==studentId.toLong);
        val studiant1 = new Student(studentId.toLong,lastname,firstname,address,birthdate);
        data.listEtudiant = studiant1::  data.listEtudiant
        complete(StatusCodes.OK, studiant1)
      } else {
        complete(StatusCodes.NotFound, "update - No student found")
      }
    }
  }



  val api = new StudentsApi(DefaultService, DefaultMarshaller)

  val bindingFuture = Http().bindAndHandle(pathPrefix("api"){api.route}, "localhost", 8080)

  val host = "localhost"
  val port = 8080

  println(s"Server online at http://${host}:${port}/\nPress RETURN to stop...")

  Await.result(system.whenTerminated, Duration.Inf)


}
