package it.epicode.classi_concrete;

import it.epicode.classi_astratte.Specifiche_Uguali;

public class Libri extends Specifiche_Uguali {
    private String autore;
    private String genere;

    public Libri(String codiceISBN, String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }
    @Override
    public String toString() {
        return String.format("Libro [Codice ISBN: %s, Titolo: %s, Anno Pubblicazione: %d, Numero Pagine: %d, Autore: %s, Genere: %s]",
                getCodiceISBN(), getTitolo(), getAnnoPubblicazione(), getNumeroPagine(), autore, genere);
    }
}
