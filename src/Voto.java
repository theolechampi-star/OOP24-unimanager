

import java.time.LocalDate;
public class Voto {

    private  int id  ;
    private Studenti   studente;
     private Esame appello;
     private int valore; // 18-30 (o 30 e lode se si vuole estendere)
      private LocalDate dataRegistrazione;




    public Voto(int id, Studenti studente, Esame appello, int valore, LocalDate dataRegistrazione) {
      
        this.id = id;
        this.studente =  studente ;
          this.appello = appello;
        this.valore  = valore ;
     this.dataRegistrazione = dataRegistrazione;



    }


    public int getId () {
 return id;
    }


    public void setId(int id)   {
    this.id = id;
    } 

    public   Studenti getStudente() {
        return studente;
    }

    public void setStudente(Studenti studente) {
     this.studente =  studente;
    }

         public Esame  getAppello()  {
         return   appello;
     }

         public void setAppello(Esame appello)  {
          this.appello  =   appello ;

    }


      public int getValore() {
          return  valore;

     }

    
 public void setValore(int  valore)  {
          this.valore =  valore ;

    }

 public LocalDate  getDataRegistrazione () {
         return  dataRegistrazione ;

     }

     public void  setDataRegistrazione( LocalDate dataRegistrazione) {
        this.dataRegistrazione = dataRegistrazione ;

    }

     @Override
    public String  toString() {
         return id + " - " + studente.getNome()  + " " + studente.getCognome() + " -> " + valore + " (" + appello.getCorso().getNome() + " il " + appello.getData() + ")";
      }
 }
