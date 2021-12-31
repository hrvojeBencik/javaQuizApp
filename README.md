# Java Quiz App

## Uvod
Ova aplikacija je napravljena kao ispitni projekat iz predmeta Java programiranje za smer Master informacione tehnologije na Višoj tehničkoj školi u Novom Sadu.

Cilj mi je bio da napravim kviz aplikaciju, koja u sebi ima sistem autentifikacije korisnika, rang liste sa rezultatima i odabir raznh težina i kategorija kviz pitanja.

Strukturu projekta sam rasporedio u 3 foldera:
  1.  App - Sadrži grafički interfejs aplikacije
  2.  Models - Sadrži klase i enumeratore
  3.  Services - Sadrži kontrolere koje su zaduženi za logiku aplikacije kao što je ApiController i DBManager.
  
Za bazu podataka sam koristio SQLite koji je SQL sistem baza podataka koji se kreira na lokalnoj mašini, a ukoliko ga želimo postaviti na neki server to se vrlo lako radi
menjanjem lokacije baze.

Kvizna pitanja i odgovore nisam sam smislio, nego sam koristion API (https://quizapi.io/) koji mi to već pruža. 
Za kontrolu nad API-em sam napravio "ApiController" klasu koja mi pruža kontrolu nad http zahtevima i onim što šaljem i dobijam od API-a. U dokumentaciji ovog API-a se može naći
kako izgleda JSON koji se dobija kao odgovor od servera i na osnovu toga sam napravio klasu "Question".

Napravio sam 2 enumeratora: 1. "QuestionCategory" - omogućava izbor kategorije pitanja
                            2. "QuestionDifficulty" - omogućava izbog težine pitanja
                       
Klasa "Quiz" sadrži rezultat kviza i niz pitanja.
Klasa "User" sadrži podatke o korisniku (username, password i vreme poslednjeg logovanja)

Klasa "DBManager" predstavlja kontroler nad bazom podataka, u njoj se nalaze sve potrebna CRUD funkcije.
Klase "Queries" i "TableName" su pomoćne klase koje olakšavaju posao u DBManager-u i sprečavaju gramatičke greške.


## Uputstvo za instalaciju

Nakon preuzimanja koda i otvaranja istog preko razvojnog okruženja Eclipse
Potrebno je uraditi 2 stvari kako bi aplikacija funkionisala:
  1.  Na particiji "C" kreirati folder pod nazivom "sqlite" i u njemu folder pod nazivom "db", na ovoj lokaciji će se kreirati naša baza podataka, ukoliko želimo promeniti
      lokaciju baze to možemo uraditi promenom String vrednosti "dbUrl" u "DBManager" klasi.
  
  2.  Trebamo skinuti 2 biblioteke:
        1.  GSON (https://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.6/gson-2.8.6.jar) - služi za parsiranje JSON-a u naše objekte
        2.  SQLITE-JDBC (https://github.com/xerial/sqlite-jdbc/releases/download/3.36.0.3/sqlite-jdbc-3.36.0.3.jar) - služi za SQLite baze podataka
      Nakon što smo skinuli ova dva .jar fajla desnim klikom na naš projekat u Eclipse-u otvaramo prozor "Properites", idemo na opciju "Build path" u levom meniju,
      biramo tab "Libraries". U glavnom prozoru kliknemo na "Classpath" i u desnom meniju kliknemo "Add external JARs", ovde izaberemo oba fajla koja smo skinuli i projekat
      bi trebalo da je spreman za pokretanje.

