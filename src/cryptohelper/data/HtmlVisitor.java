//Classe che implementa l'interfaccia Visitor e si occupa della stampa con formattazione dei vari testi
package cryptohelper.data;

import cryptohelper.interfaces.MessaggioDestinatario;
import cryptohelper.interfaces.MessaggioMittente;
import cryptohelper.interfaces.Visitor;

public class HtmlVisitor implements Visitor {

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

    @Override
    public String visit(MessaggioDestinatario msgDestinatario) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<p>");
        sb.append("<b>Mittente: </b>").append(msgDestinatario.getMittente().getNome());
        sb.append(" ").append(msgDestinatario.getMittente().getCognome());
        sb.append("</p>");
        sb.append("<p>");
        sb.append("<b>Titolo messaggio: </b>").append(msgDestinatario.getTitolo());
        sb.append("</p>");
        sb.append("<p>");
        sb.append("<b>Testo Messaggio:</b><br/>").append(msgDestinatario.getTesto());
        sb.append("</p>");
        sb.append("</html>");
        return sb.toString();
    }

    @Override
    public String visit(MessaggioMittente messaggioMittente) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<p>");
        sb.append(messaggioMittente.getTesto());
        sb.append("</p>");
        sb.append("</html>");
        return sb.toString();

    }

}
