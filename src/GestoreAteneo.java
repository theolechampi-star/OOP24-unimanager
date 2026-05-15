


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class GestoreAteneo {
    private List<Studenti> studenti = new ArrayList<>();
    private List<lezione> corsi = new ArrayList<>();
    private List<Esame> appelli = new ArrayList<>();
    private List<Voto> voti = new ArrayList<>();

    private int nextStudenteId = 1;
    private int nextCorsoId = 1;
    private int nextAppelloId = 1;
    private int nextVotoId = 1;

    // Aggiunge uno studente e ritorna l'oggetto creato
    public Studenti aggiungiStudente(String nome, String cognome, String matricola) {
        Studenti s = new Studenti(nextStudenteId++, nome, cognome, matricola);
        studenti.add(s);
        return s;
    }

    // Aggiunge un corso
    public lezione aggiungiCorso(String nome, int cfu) {
        lezione c = new lezione(nextCorsoId++, nome, cfu);
        corsi.add(c);
        return c;
    }

 public Optional<Esame>   creaAppello(int corsoId, LocalDate data, String luogo) {
        Optional<lezione> oc = trovaCorsoPerId(corsoId);
        if (oc.isEmpty()) return Optional.empty();
         Esame a = new Esame(nextAppelloId++, oc.get(), data, luogo);
        appelli.add(a);
        return Optional.of(a) ;


    }



public Optional<Voto> registraVoto(int studenteId, int appelloId, int valore) {
        Optional<Studenti> os = trovaStudentePerId(studenteId);
        Optional<Esame> oa = trovaAppelloPerId(appelloId);
         if (os.isEmpty() || oa.isEmpty()) return Optional.empty();
         Voto v = new Voto(nextVotoId++, os.get(), oa.get(), valore, LocalDate.now());
             voti.add(v);
    return Optional.of (v);
    }

    public Optional<Double> mediaStudente(int studenteId) {
        double somma = 0;
        int count = 0;
        for (Voto v : voti) {
            if (v.getStudente().getId() == studenteId) {
                somma += v.getValore();
                count++;
            }
        }

         if (count == 0) return Optional.empty();
         return Optional.of(somma / count);
    }

    public Optional<Double> mediaCorso(int corsoId) {
        double somma = 0;
        int count = 0;
        for (Voto v : voti) {
            if  (v.getAppello().getCorso().getId() == corsoId) {
                somma += v.getValore();
                count++;
            }
         }
        if (count == 0) return Optional.empty();
         return Optional.of(somma / count);
    }
    public Optional<Studenti> trovaStudentePerId(int id) {
    return studenti.stream().filter(s -> s.getId() == id).findFirst();
    }

    public Optional<lezione> trovaCorsoPerId(int id) {
         return corsi.stream().filter (c -> c.getId() == id).findFirst();
    }

    public Optional<Esame> trovaAppelloPerId(int id) {
        return appelli.stream().filter(a -> a.getId() == id).findFirst();
    }

    

    public List<Studenti>  listaStudenti() {

 return new ArrayList<> (studenti);
 }

    public List<lezione> listaCorsi() {
    return new ArrayList<>(corsi);
    }

    public List<Esame> listaAppelli() {

        return new ArrayList<> (appelli);
    }

    public List<Voto> listaVoti() {
         return new ArrayList<>(voti) ;

    }


}
