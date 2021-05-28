import java.io.File
import java.util.Scanner

object main extends App{
  val fileName = "./up13-inscription.csv";
  val fsdir = new File(fileName);
  if(fsdir.exists()) {
    // Applying a loop
    while (true) {
      // Reads the line from the Console
      val universite = scala.io.StdIn.readLine()
      val scanner = new Scanner(System.in)
      print("Entrez le nom de l'université svp : ")
      val insec = new InscriptionUtil(fileName, universite);
      insec.inscriptionUtil();
      //prints newline
      println()
    }
  }else{
    println("Le ficheir up13-inscription.csv n'existe pas , veuillez le crée svp")
  }



}
