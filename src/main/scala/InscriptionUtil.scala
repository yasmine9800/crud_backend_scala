
import java.io.File

class InscriptionUtil(filename: String, nomUniversite:String ) {

  val entete = "code_etu lib_diplome niveau_dans_le_diplome libelle_discipline_diplome code_etape libelle_etape libelle_composante libelle_academie_bac annee_bac regroupement_bac annee_inscription".split(' ').toVector

  def inscriptionUtil(): Unit = {
    // Repertoire d'écriture des jeux de données internes */
    val dir = "../output2022/"
    val fsdir = new File(dir);
    if (!fsdir.exists) fsdir.mkdirs
    if (!fsdir.exists) {
      println("Désolé, je n'arrive pas à créer vos répertoires, j'annule les tâches.")
    }
    //.filter(v=>v(1).equals(nomUniversite))
    val variable: List[Vector[String]] = read_csv(filename);
    if(variable.isEmpty) {
      println("Désolé, la source est vide")
    }else {
      val inscriptionList: List[Inscription] = ecrireAndGetClassCase(variable,variable.head,fsdir +"/inscription_csv.csv",nomUniversite)
      inscriptionList.foreach(x => println(x));
    }

  }

  // Lecture des données depuis les csv
  def read_csv(filename: String): List[Vector[String]] = {
    val source = scala.io.Source.fromFile(filename, "utf-8")
    val corps = source.getLines.toList
    source.close()
    def parse_line(ligne:String): Vector[String]= ligne.split(';').toVector;
    corps.map(parse_line)
  }
  case class Inscription(codeEtu : String ,
                         universiteLibDiplome : String,
                         niveauDansLeDiplome : String,
                         libelleDisciplineDiplome : String,
                         codeEtape : String,
                         libelleEtape : String,
                         libelleComposante : String,
                         libelleAcademieBac : String,
                         anneeBac : String,
                         regroupementBac : String,
                         anneeInscription : String,
                         universite : String
                        )

  def ecrireAndGetClassCase(lignes:List[Vector[String]],entete : Vector[String],  filename: String,universite : String ) : List[Inscription] =  {
    import java.io._
    val file = new File(filename)
    val bw = new BufferedWriter(new FileWriter(file))
    var i = 1;
    var listSortie = List.empty[Inscription]
    val  code_etu = entete.indexOf("code_etu")
    val  lib_diplome = entete.indexOf("lib_diplome")
    val  niveau_dans_le_diplome =   entete.indexOf("niveau_dans_le_diplome")
    val  libelle_discipline_diplome =   entete.indexOf("libelle_discipline_diplome")
    val  code_etape =   entete.indexOf("code_etape")
    val  libelle_academie_bac =   entete.indexOf("libelle_academie_bac")
    val  annee_bac =  entete.indexOf("annee_bac")
    val  annee_inscription =   entete.indexOf("annee_inscription")
    val  libelle_etape =   entete.indexOf("libelle_etape")
    val  regroupement_bac =   entete.indexOf("regroupement_bac")
    val libelle_composante =   entete.indexOf("libelle_composante")
    lignes.foreach(x => {

          if (i==1) {
             val v = "univ;" +: x
            bw.write(v .map(_.replaceAllLiterally(";","")).mkString(";") + "\n")
          } else {
            val inscription = Inscription(x(code_etu),
              x(lib_diplome),
              x(niveau_dans_le_diplome),
              x(libelle_discipline_diplome),
              x(code_etape),
              x(libelle_etape),
              x(libelle_composante),
              x(libelle_academie_bac),
              x(annee_bac),
              x(regroupement_bac),
              x(annee_inscription),
              universite
            );
            listSortie = inscription:: listSortie
            val v = universite +: x
            bw.write(v .map(_.replaceAllLiterally(";","")).mkString(";") + "\n")


          }
          i=2;

    })
    bw.close
    listSortie;
  }


}
