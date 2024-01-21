package it.epicode;

import com.github.javafaker.Faker;
import it.epicode.classi_astratte.Specifiche_Uguali;
import it.epicode.classi_concrete.Libri;
import it.epicode.classi_concrete.Riviste;
import it.epicode.utilita.Archivio_Bibliotecario;
import it.epicode.utilita.Periodo_riviste;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {

    // CREAZIONE DELL CATALOGO INIZIALE
    Archivio_Bibliotecario archivio = new Archivio_Bibliotecario();
    Faker faker = new Faker();

    // CREAZIONI DI LIBRI E RIVISTE IN MODO RANDOMICO
    for (int i = 0; i < 10; i++) {
      Libri libri = new Libri(
              RandomStringUtils.randomAlphanumeric(10),
              faker.book().title(),
              faker.number().numberBetween(1900, 2024),
              faker.number().numberBetween(100, 500),
              faker.book().author(),
              faker.book().genre());
      // USIAMO METODO "AGGIUNGI ELEMENTO"
      archivio.aggiungiElemento(libri);
      Periodo_riviste[] periodicitaValues = Periodo_riviste.values();
      int randomIndex = new Random().nextInt(periodicitaValues.length);
      Periodo_riviste periodicita = periodicitaValues[randomIndex];
      Riviste riviste = new Riviste(
              RandomStringUtils.randomAlphanumeric(12),
              faker.book().title(),
              faker.number().numberBetween(1950, 2024),
              faker.number().numberBetween(80, 322),
              periodicita);
      // USIAMO METODO "AGGIUNGI ELEMENTO"
      archivio.aggiungiElemento(riviste);
    }
    // STAMPIAMO L'ARCHIVIO INIZIALE
    System.out.println(archivio);

    // APRIAMO LO SCANNER
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("Scegli un'azione:");
      System.out.println("1. Cerca per ISBN");
      System.out.println("2. Rimuovi per ISBN");
      System.out.println("3. Cerca per Anno");
      System.out.println("4. Cerca per Autore");
      System.out.println("5. Salva su Disco");
      System.out.println("6. Carica da Disco");
      System.out.println("7. Esci");

      int scelta = scanner.nextInt();
      scanner.nextLine();  // Consuma la nuova riga rimasta dopo nextInt()

      switch (scelta) {
        case 1:
          System.out.print("Inserisci un codice ISBN per la ricerca: ");
          String isbnRicerca = scanner.nextLine();
          archivio.cercaPerISBN(isbnRicerca).ifPresentOrElse(
                  elemento -> System.out.println("Elemento trovato:\n" + elemento),
                  () -> System.out.println("Nessun elemento trovato per l'ISBN: " + isbnRicerca)
          );
          break;
        case 2:
          System.out.print("Inserisci un codice ISBN per la rimozione: ");
          String isbnRimozione = scanner.nextLine();
          archivio.rimuoviPerIsbn(isbnRimozione);
          System.out.println("Catalogo dopo la rimozione:");
          System.out.println(archivio);
          break;
        case 3:
          System.out.print("Inserisci un anno per la ricerca: ");
          int annoRicerca = scanner.nextInt();
          scanner.nextLine();  // Consuma la nuova riga rimasta dopo nextInt()
          List<Specifiche_Uguali> risultati = archivio.cercaPerAnno(annoRicerca);

          if (risultati.isEmpty()) {
            System.out.println("\nNessun elemento trovato per l'anno " + annoRicerca + ".\n");
          } else {
            System.out.println("\nElementi trovati per l'anno " + annoRicerca + ":\n");
            risultati.forEach(System.out::println);
          }
          break;
        case 4:
          System.out.print("Inserisci un autore per la ricerca: ");
          String autoreRicerca = scanner.nextLine();
          List<Specifiche_Uguali> risultatiAutore = archivio.cercaPerAutore(autoreRicerca);

          if (risultatiAutore.isEmpty()) {
            System.out.println("\nNessun elemento trovato per l'autore " + autoreRicerca + ".\n");
          } else {
            System.out.println("\nElementi trovati per l'autore " + autoreRicerca + ":\n");
            risultatiAutore.forEach(System.out::println);
          }
          break;
        case 5:
          System.out.print("Inserisci il nome del file per il salvataggio su disco: ");
          String nomeFileSalvataggio = scanner.nextLine();
          archivio.salvaSuDisco(nomeFileSalvataggio);
          System.out.println("Archivio salvato su disco.");
          break;
        case 6:
          System.out.print("Inserisci il nome del file per il caricamento da disco: ");
          String nomeFileCaricamento = scanner.nextLine();
          archivio.caricaDaDisco(nomeFileCaricamento);
          System.out.println("Archivio caricato da disco.");
          break;

        case 7:
          System.out.println("Uscita dal programma.");
          System.exit(0);
        default:
          System.out.println("Scelta non valida. Riprova.");
      }
      System.out.println("\n------------------------\n");


    }
  }
}
