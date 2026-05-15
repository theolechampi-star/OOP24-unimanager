
import java.time.LocalDate;

public class Esame {

    private int id;
      private lezione corso;
     private LocalDate data;
 private String luogo;

      public Esame(int id, lezione corso, LocalDate data, String luogo) {
       
        this.id = id;
         this.corso = corso;
        this.data = data;
          this.luogo = luogo;
        
        
        }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public lezione getCorso() {
        return corso;
    }

     public void setCorso(lezione corso) {
        this.corso = corso;
       }

    public LocalDate getData() {
        return data;
}

    public void setData(LocalDate data) {
           this.data = data;
    }

    public String getLuogo() {

         
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    @Override
       public String toString() {
        return id + " - Appello: " + corso.getNome() + " il " + data + " (" + luogo + ")";
            }
}
