package cryptohelper.interfaces;

import cryptohelper.data.Messaggio;
import cryptohelper.data.Proposta;
import cryptohelper.data.SessioneLavoro;

public interface Visitor {

    public String visit(Proposta proposta);

    public String visit(MessaggioDestinatario msgDestinatario);

    public String visit(MessaggioMittente messaggioMittente);

    public String visit(Messaggio msg);

    public String visit(SessioneLavoro sl);
}
