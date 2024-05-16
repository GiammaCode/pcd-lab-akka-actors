package it.unibo.pcd.akka.basics.e01hello;
import akka.actor.typed.ActorSystem;
public class Main {
    public static void main(String[] args) {
        /*
        creo un wrapper/contenitore dove mettere tutti gli actor che vadoa a creare,
        HelloActorJava.create(): Questo metodo dovrebbe ritornare il comportamento iniziale dell'attore,
        che definisce come l'attore dovrebbe reagire ai messaggi. La funzione create() è una factory per il
        comportamento dell'attore HelloActorJava.
        */
        ActorSystem<HelloActorJava.Greet> system = ActorSystem.create(HelloActorJava.create(), "ROOT_PADRE");
        /*
        system.tell(): Questo metodo invia un messaggio all'attore radice dell'ActorSystem. In Akka,
        la comunicazione avviene tramite il passaggio di messaggi in modo asincrono.

        new HelloActorJava.Greet("Akka Typed", system.ignoreRef()): Qui viene creato un messaggio di tipo Greet,
        presumibilmente una classe interna definita in HelloActorJava che contiene informazioni sul saluto.
        "Akka Typed" è il messaggio specifico, mentre system.ignoreRef() è un riferimento attore speciale che
        può essere usato quando non ci si aspetta una risposta.
        È essenzialmente un riferimento "noreply".
        * */
        system.tell(new HelloActorJava.Greet("è il mio messaggio?", system.ignoreRef()));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        system.terminate();
    }
}
