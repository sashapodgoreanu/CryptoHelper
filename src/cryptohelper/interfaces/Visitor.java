package cryptohelper.interfaces;

import cryptohelper.data.Messaggio;
import cryptohelper.data.Proposta;
import cryptohelper.com.SessioneLavoro;

public interface Visitor {

    public String visit(Proposta proposta);

    public String visit(MessaggioDestinatario msgDestinatario);

    public String visit(MessaggioMittente mesgMittente);

    public String visit(MessaggioIntercettato mesgMittente);

    public String visit(SessioneLavoro sl);
}
