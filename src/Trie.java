import java.util.ArrayList;
import java.util.List;

public class Trie {

    private Node root;

    public Trie(String... input) {
        root = new Node(input);
        root.generateFailureLinks();
    }

    public Node getRoot() {
        return root;
    }

    @Override
    public String toString() {
        return root.toString();
    }

    public List<String> substrings(String source) {
        List<String> out = new ArrayList<>();
        char currChar;
        Node currNode = root;
        Node child;

        for(int i = 0; i < source.length(); i++) {
            currChar = source.charAt(i);
            child = currNode.getChild(currChar);
            if(child == null) {
                if(currNode != root)
                    i--;
                currNode = currNode.getFaiulreLink();
            }
            else {
                currNode = child;
                if(child.isWord())
                    out.add(child.getPrefix());
            }

        }

        return out;
    }

    public static void main(String... args) {

        String[] input = {"vita", "luce", "ora", "ciao", "egli", "lui", "cielo"};
        Trie trie = new Trie(input);


        String source = "Il v. 1 è stato interpretato da alcuni come in quella metà della vita che si trascorre domendo (Dante racconterebbe una visione avuta in sogno), ma l'autore si rifà quasi certamente a un passo biblico (Isaia, 38, 10) dove si dice in dimidio dierum meorum vadam ad portas Inferi, cioè «andrò presso la porta dell'Inferno a metà dei miei giorni». Dante stesso, in Conv., IV, 23 descrive la vita umana come un arco che inizia a declinare dopo i 35 anni di età, senza contare che descrive il suo viaggio come realmente avvenuto (egli è andato sensibilimente nell'Aldilà). In Ps., LXXXIX, 10 si legge inoltre che dies annorum nostrorum... septuaginta anni («la vita dell'uomo dura settant'anni»), per cui è evidente che Dante intende collocare il suo viaggio nella primavera dell'anno 1300.\n" +
                "Al v. 5 selva selvaggia  è una paronomasia di forte sapore guittoniano.\n" +
                "Il sonno  citato al v. 11 è quello della ragione che conduce al peccato, come spesso indicato nelle Scritture.\n" +
                "Il pianeta  del v. 17 è ovviamente il Sole.\n" +
                "Nel v. 27 il che può avere valore di soggetto o di compl. oggetto, quindi il senso può essere la selva, che non lasciò vivere nessuno oppure la selva, che nessuna persona vivente poté abbandonare. Pare più probabile la prima interpretazione, nel senso che il peccato provoca la morte dell'anima portando alla dannazione.\n" +
                "Il v. 30 è stato variamente interpretato, ma forse Dante indica semplicemente che, tentando di scalare il colle, il piede più basso è quello più saldo e quindi l'ascesa è alquanto incerta. Altri pensano che il piede più basso sia il sinistro, simbolo degli appetiti materiali che frenano Dante sulla strada della salvezza (le due ipotesi non si escludono a vicenda).\n" +
                "I vv. 37-40 indicano che è l'alba e il Sole è in congiunzione con la costellazione dell'Ariete, quella che era con lui al momento della Creazione fissata tradizionalmente in primavera: l'equinozio primaverile era considerato momento favorevole, quindi anche per questa ragione Dante si riconforta (l'indicazione permette inoltre di collocare il tempo dell'azione tra marzo e aprile del 1300, come successivamente verrà meglio precisato).";

        List<String> substrings = trie.substrings(source);

        System.out.println(substrings.size());

        substrings.forEach(System.out::println);
    }
}
