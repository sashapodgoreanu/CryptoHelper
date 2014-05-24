//Classe che implementa l'interfaccia Visitor e si occupa della stampa con formattazione dei vari testi
package cryptohelper.data;

import cryptohelper.interfaces.MessaggioDestinatario;
import cryptohelper.interfaces.MessaggioIntercettato;
import cryptohelper.interfaces.MessaggioMittente;
import cryptohelper.interfaces.Visitor;

public class HtmlVisitor implements Visitor {

    //Stampa dati oggetto Proposta
    @Override
    public String visit(Proposta proposta) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<p>");
        sb.append("Proponente: ").append(proposta.getProponente().getNome());
        sb.append(" ").append(proposta.getProponente().getCognome());
        sb.append("</p>");
        sb.append("<p>");
        sb.append("Sistema di cifratura: ").append(proposta.getSdc().getMetodo());
        sb.append("<br/>Chiave cifrario: ").append(proposta.getSdc().getChiave());
        sb.append("</p>");
        sb.append("</html>");
        return sb.toString();
    }

    //Stampa dati oggetto MessaggioDestinatario
    @Override
    public String visit(MessaggioDestinatario msgDestinatario) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<p>");
        sb.append("<b>Titolo: </b>").append(msgDestinatario.getTitolo());
        sb.append("</p>");
        sb.append("<p>");
        sb.append("<b>Mittente: </b>").append(msgDestinatario.getMittente().getNome());
        sb.append(" ").append(msgDestinatario.getMittente().getCognome());
        sb.append("</p>");
        sb.append("<p>");
        sb.append("<b>Lingua: </b>").append(msgDestinatario.getLingua());
        sb.append("</p>");
        sb.append("<p>");
        sb.append("<b>Letto: </b>").append(msgDestinatario.isLetto());
        sb.append("</p>");
        sb.append("<p>");
        sb.append("<b>Testo cifrato:</b><br/>").append(msgDestinatario.getTestoCifrato());
        sb.append("</p>");
        return sb.toString();
    }

    //Stampa dati oggetto MessaggioMittente
    @Override
    public String visit(MessaggioMittente msgMittente) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<p>");
        sb.append("<b>Titolo: </b>").append(msgMittente.getTitolo());
        sb.append("</p>");
        sb.append("<p>");
        sb.append("<b>Destinatario: </b>").append(msgMittente.getDestinatario().getNome());
        sb.append(" ").append(msgMittente.getDestinatario().getCognome());
        sb.append("</p>");
        sb.append("<p>");
        sb.append("<b>Lingua: </b>").append(msgMittente.getLingua());
        sb.append("</p>");
        sb.append("<p>");
        sb.append("<b>Testo:</b><br/>").append(msgMittente.getTesto());
        sb.append("</p>");
        sb.append("</html>");
        return sb.toString();
    }

    //Stampa dati oggetto Messaggio Intercettato

    public String visit(MessaggioIntercettato msgIntercettato) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<p>");
        sb.append("<b>Titolo: </b>").append(msgIntercettato.getTitolo());
        sb.append("</p>");
        sb.append("<p>");
        sb.append("<b>Mitente: </b>").append(msgIntercettato.getMittente().getNome());
        sb.append(" ").append(msgIntercettato.getMittente().getCognome());
        sb.append("</p>");
        sb.append("<p>");
        sb.append("<b>Destinatario: </b>").append(msgIntercettato.getDestinatario().getNome());
        sb.append(" ").append(msgIntercettato.getDestinatario().getCognome());
        sb.append("</p>");
        sb.append("<p>");
        sb.append("<b>Lingua: </b>").append(msgIntercettato.getLingua());
        sb.append("</p>");
        sb.append("<p>");
        sb.append("<b>Testo cifrato:</b><br/>").append(msgIntercettato.getTestoCifrato());
        sb.append("</p>");
        sb.append("</html>");
        return sb.toString();
    }
    
      //Stampa dati oggetto SessioneLavoro
    @Override
      public String visit(SessioneLavoro sl) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<p>");
        sb.append("<b>Nome Sessione: </b>").append(sl.getNomeSessione());
        sb.append("</p>");
        sb.append("<p>");
        sb.append("<b>Autore: </b>").append(sl.getUtente().getNome());
        sb.append(" ").append(sl.getUtente().getCognome());
        sb.append("</p>");
        sb.append("<p>");
        sb.append("<b>Data ultima modifica: </b>").append(sl.getUltimaModifica());
        sb.append("</p>");
        sb.append("</html>");
        return sb.toString();
    }
      
}
