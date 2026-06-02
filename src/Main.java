import java.time.LocalDate;
      import java.util.Optional  ;
 import javax.swing.JOptionPane;
 import javax.swing.JTextArea;
  import javax.swing.JScrollPane;

public class Main  {
    private static GestoreAteneo gestore = new GestoreAteneo ( );

    public static void main(String[] args) {
        boolean running = true; 
        while  ( running) {
            String menu = "UniManager (Gestionale Esami) ---\n" +
                          "1 ) Aggiungi Studente\n" +
                          "2) Aggiungi Corso\n" +
                          "3  ) Crea Appello\n" +
                          "4 ) Registra Voto\n" +
                          "5  ) Elenca Studenti\n" +
                          "6) Elenca Corsi\n" +
                          "7 ) Elenca Appelli\n" +
                          "8) Mostra Medie\n" +
                          "0 ) Esci\n\n" +
                          " Seleziona un'operazione:";
            
            String scelta  =  JOptionPane.showInputDialog(null, menu, "UniManager GUI", JOptionPane.QUESTION_MESSAGE ) ;
            if (scelta == null || scelta.trim().equals("0")) {
                running = false;
                     JOptionPane.showMessageDialog(null, "Uscita. Arrivederci.", "UniManager", JOptionPane.INFORMATION_MESSAGE);
                 break;
         }
    switch (scelta.trim()   ) {
                case "1" -> cmdAggiungiStudente() ;
                case "2" -> cmdAggiungiCorso( );
                case "3" ->   cmdCreaAppello();
                case " 4" -> cmdRegistraVoto();
                case  "5" -> cmdElencaStudenti();
                case"6" -> cmdElencaCorsi();
                case "7 " -> cmdElencaAppelli  ();
                case "8"  ->  cmdMostraMedie();
                default -> JOptionPane.showMessageDialog(null,  "Scelta non valida.", "Errore",  JOptionPane.ERROR_MESSAGE) ;
                }
           }
 }

    private static void cmdAggiungiStudente () {
        String nome = JOptionPane.showInputDialog( null, "Nome dello studente : ", "Aggiungi Studente", JOptionPane.QUESTION_MESSAGE);
        if (nome == null) 
            return;
        
        String cognome = JOptionPane.showInputDialog(null, "Cognome dello studente:", " Aggiungi Studente ", JOptionPane.QUESTION_MESSAGE  );
        if (cognome == null) return;
        
        String matricola = JOptionPane.showInputDialog (null, "Matricola:", "Aggiungi Studente", JOptionPane.QUESTION_MESSAGE) ;
        if   (matricola = = null) return;

        Studenti s = gestore.aggiungiStudente( nome.trim(), cognome.trim() ,  matricola.trim() );
        JOptionPane.showMessageDialog(null, "Studente aggiunto con successo:\n" + s, "Successo", JOptionPane.INFORMATION_MESSAGE);
         }

    private  static void     cmdAggiungiCorso() {
        String nome = JOptionPane.showInputDialog(null , "  Nome del corso :", "Aggiungi Corso", JOptionPane.QUESTION_MESSAGE);
        if (nome == null) return;
        String cfuStr = JOptionPane.showInputDialog(null, "Numero di CFU:", "Aggiungi Corso", JOptionPane.QUESTION_MESSAGE);
        if (cfuStr == null) return ;
        try   {
         int cfu   = Integer.parseInt(cfuStr.trim()) ;
                lezione c = gestore.aggiungiCorso(nome.trim(),    cfu);
               JOptionPane.showMessageDialog ( null, "Corso aggiunto con successo:\n" + c, "Successo" ,  JOptionPane.INFORMATION_MESSAGE);
}         (NumberFormatException e)  {
             JOptionPane.showMessageDialog ( null, " Numero di CF U non valido" , "  Errore ", JOptionPane.ERROR_MESSAGE);
             }
             }

          private static void  cmdCreaAppello( )  {
    String corsi = ottieniStringaCorsi();
        String corsoIdStr  = JOptionPane.showInputDialog(null,  corsi + "\nID corso  per l'appello:", " Crea Appello ", JOptionPane.QUESTION_MESSAGE);
        if (corsoIdStr == null) return;

        String dataStr = JOptionPane.showInputDialog(null, "Data dell'appello (YYYY-MM-DD):", "Crea Appello", JOptionPane.QUESTION_MESSAGE);
        if ( dataStr == null)  return;
                      LocalDate data;
                     try     {
                  data = LocalDate.parse(dataStr.trim());
               } catch (Exception e) {
              JOptionPane.showMessageDialog( null, " Formato data non valido (usa YYYY-MM-DD).", "Errore ", JOptionPane.ERROR_MESSAGE);
            return;
         }
            String luogo = JOptionPane.showInputDialog(null, "Luogo dell'appello:", "Crea Appello", JOptionPane.QUESTION_MESSAGE);
                if ( luogo == null)  return   ;

            try {
                   int corsoId  = Integer.parseInt( corsoIdStr.trim()) ;
                     Optional<Esame>  ab =  gestore.creaAppello( corsoId, data,   luogo.trim());
                    ab.ifPresentOrElse(
                     b -> JOptionPane.showMessageDialog(null, "Appello creato con successo:\n" + b, "Successo", JOptionPane.INFORMATION_MESSAGE),
                      ( ) -> JOptionPane.showMessageDialog(null, "Corso non trovato. ", "Errore", JOptionPane.ERROR_MESSAGE)
                );
                    } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog( null, "ID Corso non valido.", " Errore",  JOptionPane.ERROR_MESSAGE);
           }
          }
             private static void  cmdRegistraVoto() {
    
            String studenti    = ottieniStringaStudenti();
             String sidStr =  JOptionPane.showInputDialog(null, studenti +     "\nID studente:", "Registra Voto ", JOptionPane.QUESTION_MESSAGE);
        if (sidStr ==  null) return;

            String appelli = ottieniStringaAppelli();
             String aidStr = JOptionPane.showInputDialog(null, appelli + "\nID appello:", "Registra Voto", JOptionPane.QUESTION_MESSAGE);
              if (aidStr == null)  return;
              String  votoStr = JOptionPane.showInputDialog(null, " Voto (18-30):", "Registra Voto",  JOptionPane.QUESTION_MESSAGE);
               if ( votoStr ==  null) return;

                    try   {
            int sid = Integer.parseInt(sidStr.trim() );
                        
            int aid = Integer.parseInt(aidStr.trim());
            int voto = Integer.parseInt(   votoStr.trim());
     Optional<Voto> xy = gestore.registraVoto(sid, aid, voto);
            xy.ifPresentOrElse(
                y ->  JOptionPane.showMessageDialog( null, "Voto registrato con successo:\n" + y, "Successo", JOptionPane.INFORMATION_MESSAGE),
                  () -> JOptionPane.showMessageDialog(null, "Studente o appello non trovato.", "Errore", JOptionPane.ERROR_MESSAGE)
                ) ;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Inseriti valori numerici non validi.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
           }

            private static void cmdElencaStudenti() {
        mostraInFinestraScorrevole( ottieniStringaStudenti( ), "Elenco Studenti ") ;
    }
    private static void cmdElencaCorsi() {
        mostraInFinestraScorrevole(ottieniStringaCorsi(), " Elenco Corsi ");
    }
    private static void cmdElencaAppelli() {
        mostraInFinestraScorrevole(ottieniStringaAppelli(), " Elenco Appelli ");
    }
     private static void cmdMostraMedie() {
              String opzioni = "1)    Media studente\n2) Media corso\n\nSeleziona :";
           String sc = JOptionPane.showInputDialog(null, opzioni, "Mostra Medie", JOptionPane.QUESTION_MESSAGE);
        if (sc == null) return;

            if (sc.trim().equals("1")) {
            String studenti = ottieniStringaStudenti();
            String sidStr = JOptionPane.showInputDialog(null, studenti + "\nID studente:", "Media Studente", JOptionPane.QUESTION_MESSAGE);
            if (sidStr == null) return;
            try {
            int sid = Integer.parseInt(sidStr.trim()) ;
            Optional<Double> md =     gestore.mediaStudente(sid) ;
             md.ifPresentOrElse  (
                    m -> JOptionPane.showMessageDialog(null, String.format("Media studente %d: %.2f", sid, m), "Media Calcolata", JOptionPane.INFORMATION_MESSAGE),
                    () -> JOptionPane.showMessageDialog(null, "Nessun voto per questo studente.", "Info", JOptionPane.WARNING_MESSAGE )
                     );
            }       catch (NumberFormatException e)     {
                JOptionPane.showMessageDialog(null, "ID non valido.", "Errore", JOptionPane.ERROR_MESSAGE);
                 }
        }           else if (sc.trim().equals("2"))  {
            String corsi =   ottieniStringaCorsi() ;
              String cidStr  = JOptionPane.showInputDialog(null, corsi + "\nID corso:"  , "Media Corso",  JOptionPane.QUESTION_MESSAGE);
            if  (cidStr == null)  return;
                
            try {
                int cid =  Integer.parseInt(cidStr.trim() );
                Optional< Double > mc = gestore.mediaCorso (cid);
                mc.ifPresentOrElse(
                    m ->  JOptionPane.showMessageDialog(null,  String.format("Media corso %d: %.2f", cid, m),  "Media Calcolata"  ,  JOptionPane.INFORMATION_MESSAGE),
                    () -> JOptionPane.showMessageDialog( null, "Nessun voto per questo corso. ", " Info ",  JOptionPane.WARNING_MESSAGE)
                        );
                 } catch (  NumberFormatException e)   {
                JOptionPane.showMessageDialog(null, "ID non valido.", "Errore", JOptionPane.ERROR_MESSAGE);
               }
                 } else {
                    JOptionPane.showMessageDialog( null, "Scelta non valida.", "  Errore"   ,   JOptionPane.ERROR_MESSAGE);
          }
          }

    // Funzioni helper per convertire le liste in testo nella  GUI  .
    private static String ottieniStringaStudenti() {
    StringBuilder s    b = new StringBuilder(" Studenti \n");
        for (Studenti s : gestore.listaStudenti()) {
              sb.append(s).append("\n");
           }
                return sb.toString();
                     }
               private static String ottieniStringaCorsi() {
                StringBuilder sb = new StringBuilder("--- Corsi ---\n");
                 for (lezione c : gestore.listaCorsi())   {
                   sb.append(c).append(" \n")  ;
         }
              return sb.toString( )  ;
            }
private static String ottieniStringaAppelli() {
        StringBuilder sb = new StringBuilder("-Appelli -\n");
        for (Esame a : gestore.listaAppelli()) {
        sb.append(a).append("\n");
        }
        return sb.toString();
        }

        // Mostra i dati   dentro un'area di testo con la  barra di scorrimento 
     private static void montreInFinestraScorrevole(String testo, String titolo) {
            JTextArea textArea = new JTextArea(15, 40);
        textArea.setText(testo) ;
         textArea.setEditable(false);
             JScrollPane scrollPane =  new   JScrollPane(textArea);
        JOptionPane.showMessageDialog(  null, scrollPane, titolo,   JOptionPane.INFORMATION_MESSAGE)   ;
       }
          }
