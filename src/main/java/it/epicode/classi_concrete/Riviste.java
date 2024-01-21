package it.epicode.classi_concrete;

import it.epicode.classi_astratte.Specifiche_Uguali;
import it.epicode.utilita.Periodo_riviste;

public class Riviste extends Specifiche_Uguali {
    public Periodo_riviste periodicita;

    public Riviste(String codiceISBN, String titolo, int annoPubblicazione, int numeroPagine, Periodo_riviste periodicita) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Periodo_riviste getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodo_riviste periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return String.format("Rivista [Codice ISBN: %s, Titolo: %s, Anno Pubblicazione: %d, Numero Pagine: %d, Periodicita: %s]",
                getCodiceISBN(), getTitolo(), getAnnoPubblicazione(), getNumeroPagine(), periodicita);
    }
}