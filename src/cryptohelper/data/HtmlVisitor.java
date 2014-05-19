/*
 * Copyright 2014 Sasha Alexandru Podgoreanu.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cryptohelper.data;

import cryptohelper.interfaces.MessaggioDestinatario;
import cryptohelper.interfaces.MessaggioMittente;
import cryptohelper.interfaces.Visitor;

/**
 *
 * @author Sasha Alexandru Podgoreanu
 */
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
    
    public String visit(MessaggioMittente messaggioMittente) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<p>");
        sb.append("Mittente: ").append(messaggioMittente.getMittente().getNome());
        sb.append(" ").append(messaggioMittente.getMittente().getCognome());
        sb.append("</p>");
        sb.append("<p>");
        sb.append("Titolo messaggio: ").append(messaggioMittente.getTitolo());
        sb.append("</p>");
        sb.append("<p>");
        sb.append("Testo Messaggio:<br/>").append(messaggioMittente.getTesto());
        sb.append("</p>");
        sb.append("</html>");
        return sb.toString();

    }

}
