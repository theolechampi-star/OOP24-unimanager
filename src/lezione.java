      public class lezione {
    private int id;
    private String nome;
 private int cfu;

    public lezione(int id, String nome, int cfu)  {

      this.id = id;
        this.nome = nome;
        this.cfu =  cfu;
      }



    public int getId()  {
         return id;

    }
public void setId(int id) {
        this.id = id;
    } 


    public String getNome() {
 return nome;
     }


      public void setNome(String nome) {
         this.nome =  nome;
    }

     public int getCfu() {
        return cfu;
    }

    public void setCfu(int cfu) {
        this.cfu = cfu;
    }

    @Override
      public String toString() {
     
     
        return id + " - " + nome + " (" + cfu + " CFU)";
 
    }

}
