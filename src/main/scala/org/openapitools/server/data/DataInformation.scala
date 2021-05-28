package org.openapitools.server.data

import akka.actor.ActorSystem
import org.openapitools.server.model.Student;

/**
 * hamadou yasmine
 * @param system
 */
class DataInformation(system: ActorSystem) {
    var studiant1 = new Student(1,"HAMADOU1","Yasmine1","8 rue roset","05-04-1998")
    var studiant2 = new Student(2,"HAMADOU2","Yasmine2","8 rue roset","05-04-1998")
    var studiant3 = new Student(3,"HAMADOU3","Yasmine3","8 rue roset","05-04-1998")
    var studiant4 = new Student(4,"HAMADOU4","Yasmine4","8 rue roset","05-04-1998")
    var studiant5 = new Student(5,"HAMADOU5","Yasmine5","8 rue roset","05-04-1998")
    var studiant6 = new Student(6,"HAMADOU6","Yasmine6","8 rue roset","05-04-1998")
    var studiant7 = new Student(7,"HAMADOU7","Yasmine7","8 rue roset","05-04-1998")
    var studiant8 = new Student(8,"HAMADOU8","Yasmine8","8 rue roset","05-04-1998")
    var studiant9 = new Student(9,"HAMADOU9","Yasmine9","8 rue roset","05-04-1998")
    var studiant10 = new Student(10,"HAMADOU10","Yasmine10","8 rue roset","05-04-1998")
    var listEtudiant = List(studiant1, studiant2, studiant3, studiant4, studiant5, studiant6, studiant7, studiant8, studiant9, studiant10)
}
