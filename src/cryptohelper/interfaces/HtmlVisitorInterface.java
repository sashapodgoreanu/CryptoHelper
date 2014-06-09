//Interfaccia implemntata dal concrete Visitor, ovvero dalla classe HTMLVisitor

package cryptohelper.interfaces;

import cryptohelper.data.Proposta;
import cryptohelper.data.SessioneLavoro;
import cryptohelper.data.SistemaCifratura;
import cryptohelper.data.Soluzione;

public interface HtmlVisitorInterface {

    public String visit(Proposta proposta);

    public String visit(SistemaCifratura sdc);

    public String visit(MessaggioDestinatario msgDestinatario);

    public String visit(MessaggioMittente mesgMittente);

    public String visit(MessaggioIntercettato mesgMittente);

    public String visit(SessioneLavoro sl);

    public String visit(Soluzione s);
}
