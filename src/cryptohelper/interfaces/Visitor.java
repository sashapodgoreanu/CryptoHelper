package cryptohelper.interfaces;

import cryptohelper.data.Proposta;

public interface Visitor {

    public String visit(Proposta proposta);

    public String visit(MessaggioDestinatario msgDestinatario);

    public String visit(MessaggioMittente messaggioMittente);
}
