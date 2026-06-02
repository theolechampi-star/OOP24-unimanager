Relazione Tecnica: OOP24-unimanager
Studente:Theophile Kuetche Tadomdjou  
Corso:Programmazione Oggetto-Orientata (OOP)  


 1. Descrizione del Dominio ed Obiettivi
Il progetto OOP24-unimanager  è un sistema software orientato agli oggetti sviluppato in linguaggio Java per la gestione digitale e l'automazione dei flussi informativi all'interno di un Ateneo universitario. 

L'obiettivo principale dell'applicazione è centralizzare la manipolazione e il tracciamento delle entità core che caratterizzano la vita accademica, eliminando le ridondanze informative e garantendo la coerenza dei dati. Il sistema offre strumenti per:
  a)Gestione delle Anagrafiche: Iscrizione, archiviazione e tracciamento dello storico degli studenti e dei relativi corsi di studio.
  b)Pianificazione degli Appelli:  Associazione temporale e logica di sessioni d'esame ai rispettivi corsi di insegnamento.
  c)Valutazione e Carriera: Registrazione dei voti conseguiti, validazione dei vincoli di idoneità (es. voto compreso tra 18 e 30, o gestione delle lodi) e calcolo dinamico delle metriche di rendimento.
    Analisi Statistica : Monitoraggio delle performance medie sia a livello del singolo studente (media ponderata o aritmetica della carriera) sia a livello di corso (andamento generale delle votazioni d'esame per monitorare la difficoltà degli appelli) .

    

2. Architettura del Sistema e Design Pattern
Il sistema adotta un'architettura modulare ispirata al pattern MVC (Model-View-Controller), che garantisce la netta separazione tra la logica di business (modello dati) e l'interfaccia di interazione con l'utente.

 Componenti Principali (Model):
`GestoreAteneo` : Funge da classe *Facade* e controller globale del backend. Centralizza le collezioni di studenti e corsi, esponendo le API per le operazioni CRUD (Create, Read, Update, Delete) e per l'elaborazione statistica dei dati aggregati.
   a)`Studente`: Incapsula i dati anagrafici (Nome, Cognome, Matricola) e la carriera scolastica del discente, memorizzando la mappatura dei voti conseguiti.
   b)`Corso`: Rappresenta un insegnamento accademico, definito da un codice univoco, denominazione e crediti formativi (CFU).
   c)`Appello`: Modella una specifica sessione d'esame legata a un corso, definendo la data e la lista degli studenti iscritti.
   d)`Voto`: Oggetto immutabile che associa una valutazione numerica o un giudizio di idoneità a uno studente per un determinato appello.


3. Dettagli Implementativi e Scelte Progettuali
Nello sviluppo del software sono stati applicati i principi fondamentali della programmazione a oggetti (Incapsulamento, Ereditarietà, Polimorfismo):

   a)Information Hiding :Tutti i campi sensibili delle classi (come i dati anagrafici o le collezioni interne) sono dichiarati `private`. L'accesso e la modifica controllata avvengono esclusivamente tramite metodi getter e setter, applicando dove necessario la *defensive copying* per evitare la fuga di riferimenti interni.
   
    b) Utilizzo del Java Collections Framework:** Per la gestione delle entità sono state scelte strutture dati dinamiche avanzate. In particolare, si è fatto uso di `Map` (es. `HashMap`) per garantire la ricerca immediata degli studenti tramite il loro identificativo univoco (Matricola), e di `List` (`ArrayList`) per preservare l'ordine di inserimento degli appelli o la cronologia dei voti.
   
   c) Robustezza del Codice (Exception Handling):** Il sistema non fallisce in modo anomalo in caso di input non validi. Sono stati implementati controlli preventivi e sollevate eccezioni personalizzate (es. inserimento di voti fuori dal range consentito o tentativi di iscrizione duplicata allo stesso appello).


  4. Estensione dell'Interfaccia Grafica (GUI)
Come richiesto per i requisiti standard di usabilità del progetto, l'applicazione integra un'interfaccia grafica (GUI) sviluppata per facilitare l'interazione con l'utente non tecnico.

La View si articola in schermate principali :
  a) Pannello di Controllo Generale:** Consente la visualizzazione tabellare dell'intero database dell'ateneo (studenti iscritti e corsi attivi).
  b)Form di Inserimento:** Finestre di dialogo dedicate alla registrazione rapida di un nuovo studente o alla creazione di un appello d'esame.
  c)Dashboard Statistica:** Un'area dedicata in cui, selezionando uno studente dal menu, il sistema calcola e mostra istantaneamente a schermo la media aggiornata in tempo reale, migliorando drasticamente l'esperienza utente rispetto all'output standard da terminale.
