# Api  Akka-http

Une API très simplifiée pour gérer les étudiants


    ## API

          ### Students

          |Name|Role|
          |----|----|
          |`org.openapitools.server.api.StudentsController`|akka-http API controller|
          |`org.openapitools.server.api.StudentsApi`|Representing trait|
              |`org.openapitools.server.api.StudentsApiImpl`|Default implementation|

                * `POST /students/{lastname}/{firstname}/{address}/{birthdate}` - Create a student
                * `GET /students?limit=[value]` - List all students
                * `GET /students/{studentId}` - Info for a specific student
                * `PUT /students/{lastname}/{firstname}/{address}/{birthdate}/{studentId}` - update  student

