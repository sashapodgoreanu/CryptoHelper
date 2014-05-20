//Classe che implementa l'interfaccia Visitor e si occupa della stampa con formattazione dei vari testi
package cryptohelper.data;

import cryptohelper.interfaces.MessaggioDestinatario;
import cryptohelper.interfaces.MessaggioMittente;
import cryptohelper.interfaces.Visitor;

public class HtmlVisitorV2 implements Visitor {

    /**
     * NellHtmlVisitorV2a V2 cambia sb.append("<b>Testo Messaggio:</b><br/>").append(msgDestinatario.getTesto());
     * in
     * sb.append("<b>Testo Messaggio:</b><br/>").append(msgDestinatario.getTestoCifrato());
     */
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
        sb.append("<b>Testo Messaggio:</b><br/>").append(msgDestinatario.getTestoCifrato());
        sb.append("</p>");
        sb.append("</html>");
        return sb.toString();
    }

    @Override
    public String visit(Proposta proposta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String visit(MessaggioMittente messaggioMittente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
