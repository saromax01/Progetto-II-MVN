package it.epicode.utilita;

import it.epicode.classi_astratte.Specifiche_Uguali;
import it.epicode.classi_concrete.Libri;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Archivio_Bibliotecario {
    List<Specifiche_Uguali> catalogo = new ArrayList<>();

    public void aggiungiElemento(Specifiche_Uguali elemento) {
        catalogo.add(elemento);
    }

    public void rimuoviPerIsbn(String codISBN) {
        catalogo.removeIf(elemento -> elemento.getCodiceISBN().equals(codISBN));
    }

    public Optional<Specifiche_Uguali> cercaPerISBN(String codiceISBN) {
        return catalogo.stream()
                .filter(elemento -> elemento.getCodiceISBN().equals(codiceISBN)).findFirst();
    }

    public List<Specifiche_Uguali> cercaPerAnno(int anno) {
        return catalogo.stream()
                .filter(elemento -> elemento.getAnnoPubblicazione() == anno)
                .toList();
    }

    public List<Specifiche_Uguali> cercaPerAutore(String autore) {
        return catalogo.stream()
                .filter(elemento -> elemento instanceof Libri && ((Libri)elemento).getAutore().equals(autore))
                .toList();

    }

    public void salvaSuDisco(String nomeFile) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(nomeFile))) {
            objectOutputStream.writeObject(catalogo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void caricaDaDisco(String nomeFile) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeFile))) {
            catalogo = (List<Specifiche_Uguali>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Archivio Bibliotecario:\n");
        for (Specifiche_Uguali elemento : catalogo) {
            result.append(elemento).append("\n");
        }
        return result.toString();
    }

}
