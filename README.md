# POS_Teory_6ACIF
Project POS Theory 6ACIF

Di Spoto Giorigo Matteo

## Funzionamento del Programma
è strutturato in modo da porter elaborare più grafici e matrici salvandoli in un Pool, dove vengono salvati anche i risultati delle varie operazioni. 

Ongi funzone non restituisce in se un vaolre, ma solo il nome in cui un oggetto è stato salvatoin memoria in modo tale che la fuzione successiva o l'utete possa recuperare i dati dalla Memoria. 
La Memoria (pool) non ha un limite predefinito ma solo i limiit impostait da JAVA.

### Avvio del programma
Una volta fatto partire viene richiesto il nome utente.

### Produk Key
Per le funzioanlità avvanzatte è neccessario inserire iun `Produkt Key` *funzone non implementata*

### SPS 
E una protezione del programma che conta quante volte una funzione ricorsova viene chiamata, e, superato il limite, va in errore evitando cilci infiniti. 

Esiste una funzone simlile anche per le matrici limitando a 30 i componetni di una matrici, evistando calcoli troppo complessi o che conproettono la stabilità del Programma

### Inserimento dati 
È possibile inserire i dati sia manualmente che caricando un file usando i comandi apositi.

Tutti i Grafici e Matrici hanno un nome con il quale è possibile recuperarlo dalla memoroa

- Grafici 
    - `GRA_ADD_MANUAL`
        -  Viene Rischiesto il nome del Grafico che serve per recuperarlo dalla memoria
        - Viene richiesto quanti Noti ci sono del graifco 
        - Se il grafico ha dei pesi
    - `GRA_ADD`
        - Aggiunta di un grafico da File
        - Viene richiesto il nome del Grafico
- Matrici
    - `MAT_ADD`
        - Aggiunta automatico di una Matrice da File
        - Viene richiesto il nome della Matrice e verifica che sia Adiacente (simetrica)
    - `MAT_ADD_MANUAL`
        - Aggiunta manuale di una Matrice Adiacente
        - Viene richiesto il nome della Matrice e verifica che sia Adiacente (simetrica)
        - Richede quanti elementi ha la Matrice


**NOTA :** per impostazione predefinta, il separatore dei valori è punto e virgola `;` ma è possibile mettere un valore a scielta usando il comando `SET_SEPARA_VALORE`

---

## Matrice
I comandi per le matrcini iniziano con il prefisson `MTA_` 


#### Crea Grafico da Matrice  - MAT_CREATE_GRAFICO
Trasforma una matrice in un Grafico assegnando automaticanete un nome a ogni Punto.
Controlla che i colegamenti sinano validi, e che ogni collegamento abbia il rispettivo (A->B e B->A)

 ```
 for (int x = 0; x < dimeMatrx; x++) { // Cicolo per ogni Nodo
    //Aggiungo punti
    datiTemp.put(lettere.get(x).toString(), null);  //Punto -> Collegamenti (con i relativi Pesi)

    Map<String , Integer> colegamenti = new HashMap<>(); //Singolo Coloegametno
    for (int y = 0; y < dimeMatrx; y++) {
        int peso = matriceOrigne.getValore(x, y); //Prendo il peso attuale del Colegametno 
        if(peso != 0){ //Se è diverso da 0 vuol dire che c'è un colegamento
            if(peso > 1 ){ isGraficoConPeso = true; }
            else { isGraficoConPeso = false; }
            colegamenti.put(lettere.get(y).toString(), peso); //Lo aggiungo al colegmento per il punto Attuale 
        }
    }//for Y
    datiTemp.put(lettere.get(x).toString(), colegamenti); //Salvo sul Grafico
}//for X
 ```
 ---
 ## Grafici
 I comandi per i Grafici iniziano con il prefisson `GRA_` 

 ### Articolazoni - GRA_ARTI
Ricerca le Articolazoni in un Grafico.
Le articolazioni sono quei Nodi che se tolti dividono un grafico in 2 pezzi.

Cicla per ogni ogni punto del grafico e verifica che non ci sia già passato (Nodi già visitati salvati in un Arry). Se non è stato visitato richiama la funzone `DFS`

### Calcolo Distanza - GRA_CALC_DIST 
Per prima cosa genero una Matrice di "ritorno" (come Output), con valori predefiniit impostati a 0 e al cicolo di tutti Nodi del Grafico modifico i valori della Matrice.

```
// Aggiorno la matrice delle distanze con i pesi dei collegamenti dal grafo

// Itero su ogni nodo sorgente del grafo (entrySet della mappa principale)
for (Map.Entry<String, Map<String, Integer>> entry : graficoDati.entrySet()) {
    String da = entry.getKey(); // Nodo sorgente (nome come stringa)
    int indiceDa = nomeToIndice.get(da); // Converto il nome del nodo sorgente in indice intero

    // Itero su tutti i nodi collegati al nodo sorgente
    for (Map.Entry<String, Integer> collegamento : entry.getValue().entrySet()) {
        String a = collegamento.getKey(); // Nodo destinazione (nome)
        int indiceA = nomeToIndice.get(a); // Converto il nome del nodo destinazione in indice intero
        int peso = collegamento.getValue(); // Peso del collegamento tra "da" e "a"

        // Stampo a scopo di debug il collegamento con peso
        Libreria.debug(da + " -> " + a + " : " + peso);

        // Imposto il valore del peso nella matrice delle distanze alla posizione corretta
        matriceDistanza.setValore(indiceDa, indiceA, peso);
    }
}

// Algoritmo di Floyd-Warshall per il calcolo dei cammini minimi tra tutte le coppie di nodi

// Itero su ogni nodo intermedio k
for (int k = 0; k < V; k++) {
    // Itero su ogni nodo sorgente i
    for (int i = 0; i < V; i++) {
        // Itero su ogni nodo destinazione j
        for (int j = 0; j < V; j++) {
            // Recupero le distanze i->k, k->j e i->j attuali
            int dik = matriceDistanza.getValore(i, k);
            int dkj = matriceDistanza.getValore(k, j);
            int dij = matriceDistanza.getValore(i, j);

            // Se esiste un cammino i->k->j più corto rispetto a quello attuale i->j
            if (dik != maxInt && dkj != maxInt && dij > dik + dkj) {
                // Aggiorno la distanza i->j con la somma del cammino i->k + k->j
                matriceDistanza.setValore(i, j, dik + dkj);
            }
        } // Fine ciclo j
    } // Fine ciclo i
} // Fine ciclo k
```

### Calcolo Depth-First Search - GRA_DFS
Comando ustao anche da altri componenti del Programma.
Chiama in maniera ricorsiva la funzone `calcolaDFS` che per questo motivo è stto il controllo di SPS.

Per prima cosa, dato un Grafico cicla su tutti i suoi nodi (se non sono già statai visitati)

```
// Il nodo non è stato ancora visitato, quindi lo segno come visitato
visitati.add(nodoOra);

// Aggiungo il nodo corrente alla componente connessa in costruzione
componente.add(nodoOra);

// Ciclo su tutti i nodi adiacenti al nodo corrente
// Uso getOrDefault per gestire il caso in cui nodoOra non abbia vicini (evita NullPointerException)
for (String nodoPuntuale : grafico.getGraficoDati()
        .getOrDefault(nodoOra, Collections.emptyMap())
        .keySet()) {

    // Richiamo ricorsivamente la DFS sul nodo adiacente non ancora visitato
    calcolaDFS(nodoPuntuale, nomeGrafico, visitati, componente);
}
```

### Diametro - GRA_DURCHMESSER
Funzone per il calcolo del Diametro. Per funzionare richiama la Funzone per il calcolo del Escentricita.
E avendo i Nodi già calcolati con Escentricita cerca il valore Massimo.

### Escentricita - GRA_EXZENTR
Per prima cosa calclola la distanza da un grafico. Dal Grafico estra la Matrice Adiacente e cicla per i colegamenti 

```
// Ciclo sulla matrice per ogni punto (riga) e calcolo il valore massimo tra le sue colonne (pesi)
// Recupero la struttura dati della matrice (lista di liste di interi)
ArrayList<ArrayList<Integer>> matriceDati = matrice.getMatriceDati();

// Itero sulle righe della matrice, ognuna rappresenta un "punto" o un nodo
for (int i = 0; i < matriceDati.size(); i++) {

    // Inizializzo il massimo trovato a Integer.MIN_VALUE (il minimo valore possibile per un intero)
    int maxTrovato = Integer.MIN_VALUE;

    // Itero su ogni valore della riga corrente (cioè i valori collegati al punto i)
    for (int j = 0; j < matriceDati.get(i).size(); j++) {

        // Se il valore corrente è maggiore di maxTrovato, lo aggiorno
        if (matriceDati.get(i).get(j) > maxTrovato) {
            maxTrovato = matriceDati.get(i).get(j);
        } // end IF
    } // end Punto Ora (fine ciclo sulle colonne della riga i)

    // Aggiungo il valore massimo trovato per il punto i nella struttura di ritorno (mappata per nome)
    returnPunti.addValore(matrice.getNomePuntoDaIndex(i), maxTrovato);

} // end Ciclo Punti (fine ciclo su tutte le righe della matrice)
```


## Punti (o Nodi)

Anche i Punti (meglio noti con Nodi) sono salvati come Oggetti nella Memoria e hanno un proprio Pool. 
Come nei Grafici e nelle Matrici eisisnto specifici comandi per la stampa, ricerca e cancellazione dalla Memoria

----
## Interefaccia gra - GUI
L'interfaccai grafica è a riga di comando come un Terminale. 
Per eseguire comandi si deve scrivere, come riportato nel elenco dei comandi qui sotto. 

Per eseguire i calcoli tutti in sieme usare il comando `RUN`

## Elenco dei Comandi & Funzionamento dei comandi
I comandi non fa distizione tra Maiuscole o minuscole 

Per sicurezza usare il comando **`stop`** per l'arresto d'Emergenza del programma.
Per ogni comando potrebbe essere richiesto degli input, seguire le indicazioni a scermo.

Per avere **supporto** a ogni comando è possibile scirvere il paramentro **?** (punto di dimanda) che farà visualizare una piccola guida e una breve descrizione del comanda


| Comando               | Descrizione                             |
|-----------------------|-----------------------------------------|
| DEBUG                | Attiva o disattiva il debug              |
| EXIT                 | Chiude il programma                      |
| GRA_ADD              | Aggiunge un Grafico da File              |
| GRA_ADD_MANUAL       | Agggiunge manualmente un Grafico         |
| GRA_ARTI             | Calcolo delle articolazioni del Grafico  |
| GRA_CALC_DIST        | Calcola la distanza di un Grafico        |
| GRA_DELETE           | Cancella un Grafico dalla memora         |
| GRA_DFS              | Calcolo Depth-First Search da un Grafico |
| GRA_DURCHMESSER      | Calcolo del Diamoetro di un Grafico      |
| GRA_EXZENTR          | Calcola la Escentricita                  |
| GRA_KOMPONENTEN      | Cacolo da un Grafico componenti connesse |
| GRA_LIST             | Visualizza i Grafici in Memora           |
| GRA_PONTI            | Riceca dei Ponti di un Grafico           |
| GRA_PRINT            | Stampa a vidio di un Grafico             |
| GRA_RADIUS           | Calcolo del Raggio di un Grafico         |
| GRA_ZENTRUM          | Calcola il centro di un Grafico          |
| HELP                 | Visualizza l'elendo dei Comandi          |
| MAT_ADD              | Aggiunta da File di una Matrice          |
| MAT_ADD_MANUAL       | Aggiunta manuale di una Matrice          |
| MAT_CREATE_GRAFICO   | Converte una Matrice in un Grafico       |
| MAT_DELETE           | Cancella dalla memorai una Matrice       |
| MAT_LIST             | Elenco delle matrici in Memora           |
| MAT_PRINT            | Stampa a vidio una Matrice               |
| NOME_UTENTE          | Modifica il nome utente attuale          |
| PK                   | Inserisci il Produk Key                  |
| PUNT_DELETE          | Cancella un elendo di Nodi dalla Memoria |
| PUNT_LIST            | Visualizza i nodi in meoria              |
| PUNT_PRINT           | Stampa un Elenco di tutti Nodi in Memoria|
| RAM                  | Visualizza la RAM usata                  |
| RUN                  | Esecuzione del Programma                 |
| SET_SEPARA_VALORE    | Imposta il separatore di valore          |
| STORIA               | Visualizza gli ultimi 5 comandi          |
| VER                  | Versione del Programma                   |


