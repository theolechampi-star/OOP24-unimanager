
import java.time.LocalDate;

import java.util.Scanner ;


import java.util.Optional;
public class Main {
    private static  GestoreAteneo gestore =  new GestoreAteneo();
     private static Scanner scanner = new Scanner(System.in);

    public static void main(String[]   args)  {
        boolean running = true;
                while (running) {
                    stampaMenu();
                          String scelta = scanner.nextLine().trim();
            switch (scelta) {
                  case "1" -> cmdAggiungiStudente();
                  case "2" -> cmdAggiungiCorso();
                  case "3" -> cmdCreaAppello();
                 case "4" -> cmdRegistraVoto();
                case "5" -> cmdElencaStudenti();
                  case "6" -> cmdElencaCorsi();
                case "7" -> cmdElencaAppelli();
            case "8" -> cmdMostraMedie();
                case "0" ->    {
                    running = false;
                     System.out.println ("Uscita. Arrivederci.");
                }


                default -> System.out.println("Scelta non valida.");
            }
        }

        scanner.close();
    }

     private static void stampaMenu() {


          System.out.println("\n--- UniManager (Gestionale esami) ---");
        System.out.println("1) Aggiungi    Studente");
        System.out.println("2) Aggiungi Corso  ") ;
        System.out.println("3) Crea Appello");
             System.out.println("4) Registra Voto");
         System.out.println("5) Elenca Studenti");
         System.out.println("6) Elenca Corsi");
    System.out.println("7) Elenca Appelli");
         System.out.println("8) Mostra Medie")  ;
        System.out.println("0) Esci ");
         System.out.print("Seleziona: ") ;

    }

private static void  cmdAggiungiStudente() {
          System.out.print("Nome: ");
    String nome = scanner.nextLine().trim();

        System.out.print("Cognome: ") ;

        String cognome = scanner.nextLine().trim();

         System.out.print("Matricola: ") ;
            String matricola = scanner.nextLine().trim();
             Studenti s = gestore.aggiungiStudente(nome, cognome, matricola);
    System.out.println("Studente aggiunto: " + s);

    }

     private static  void cmdAggiungiCorso( )  {
        System.out.print ("Nome corso: ");
        String nome =   scanner.nextLine().trim();
       
        System.out.print("CFU: ");
        int cfu = leggiIntero();
          lezione c = gestore.aggiungiCorso(nome, cfu);
     System.out.println ("Corso aggiunto: " + c) ;

    }

    private static  void cmdCreaAppello()  {
         cmdElencaCorsi()   ;
        System.out.print("ID corso    per l'appello  : ");
          int corsoId = leggiIntero();
        System.out.print("Data  (YYYY-MM-DD ) : ");
        String dataStr = scanner.nextLine().trim();
         LocalDate  data;
        try {
             data = LocalDate.parse(dataStr);
        } catch (Exception e) {

            System.out.println("Formato data non valido.");
            return;
        }
        System.out.print("Luogo: ");
        String luogo = scanner.nextLine().trim();
        Optional<Esame> ab= gestore.creaAppello(corsoId, data, luogo);
         ab.ifPresentOrElse(
            b -> System.out.println("Appello    creato: " + b),
            () -> System.out.println("Corso non trovato.   ")
        );
    }

           private static void cmdRegistraVoto() {
        cmdElencaStudenti();
           System.out.print("ID studente: ");
        int sid = leggiIntero();
        cmdElencaAppelli ();
        System.out.print("ID   appello: ")   ;
        int aid = leggiIntero();

        System.out.print("  Voto (18-  30): ");
         int voto = leggiIntero();
        Optional<Voto> xy = gestore.registraVoto(sid, aid, voto);
        xy.ifPresentOrElse(
            y-> System.out.println("Voto registrato: " +  y),
            () -> System.out.println("Studente o appello non trovato.")
        );
    }

    private static void cmdElencaStudenti() {
        System.out.println("--- Studenti ---") ;
        for (Studenti s : gestore.listaStudenti()) {
             System.out.println(s);
         }
     }

private static void cmdElencaCorsi() {
       
    System.out.println("--- Corsi ---");
         for (lezione c : gestore.listaCorsi()) {
             System.out.println(c);
        
        
            }
    }

    private static void   cmdElencaAppelli()  {
          System.out.println("--- Appelli ---");
               for (Esame a : gestore.listaAppelli()) {
        System.out.println(a);
         }
     }

            private static void cmdMostraMedie() {
        System.out.println("1) Media studente");
         System.out.println("2)   Media corso");
        System.out.print("Seleziona: ")  ;
        String sc = scanner.nextLine().trim();
        if    (sc.equals("1")) {
            cmdElencaStudenti();
              System.out.print("ID studente: ");
            int sid = leggiIntero();
              Optional<Double> md = gestore.mediaStudente(sid);
            md.ifPresentOrElse(

                m -> System.out.printf("Media studente %d: %.2f%n", sid, m),
                () -> System.out.println("Nessun voto per questo studente.")
            );
        } else if (sc.equals("2")) {
             cmdElencaCorsi();
            System.out.print("ID corso: ");
               int cid = leggiIntero();
            Optional<Double> mc = gestore.mediaCorso(cid);
                   mc.ifPresentOrElse(
                m ->   System.out.printf("Media corso %d: %.2f%n", cid, m),
                ()  ->     System.out.println("Nessun voto per questo corso.")
            );
        } else {
            System.out.println("Scelta   non  valida.")   ;
        }
    }
    private static int  leggiIntero() {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
             } catch (NumberFormatException e) {
                
                System.out.print("Inserisci un numero valido: ");
              }
         }
           }

  }
