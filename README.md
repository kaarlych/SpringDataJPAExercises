### -> Flyway 🕊️
- baze danych mozna podzielic na schematy
- w trakcie zycia aplikacji mozna zadac sobie pytanie
jak sensownie zarzadzac schematami baz danych
- tworzenie nowych funkcjonalnosci w aplikacji czesto wymaga zmian
na bazie danych
- flyway pomaga w zarzadzaniu schematami baz danych
- flyway to taki git dla baz danych
- to ze dodajemy kolumne zmieniamy cos etc flyway to sledzi
- hibernate ddl auto - ten parametr rowniez sledzi zmiany w schematach
- jest jeszcze cos takiego jak LiquiBase 
- w duzym uproszczeniu mozna powiedziec, ze flyway aktualizuje baze danych
z jednej wersji do drugiej przy wykorzystaniu migracji. Migracje takie moga byc 
pisane przy wykorzystaniu SQL albo w Javie, dla bardziej skomplikowanych zapytan.
- Migracje takie moga byc wersjonowane lub powtarzalne. Kazda migracja wersjonowana
moze miec swoja unikalna wersje i byc wykonana dokladnie raz. Migracje powtarzalne nie 
maja wersji, sa one wykonywane za kazdym razem gdy zmieni sie ich suma kontrolna (poruszymy
tematyke sumy kontrolnej). W przykladach skupimy sie na migracjach wersjonowanych.

### -> Flyway migrations
```text
Przyklad: <prefix><version>__<description>.sql
```
```text
V2_1__create_ordering_tables.sql
V4_0__add_price_column_to_product_table.sql
V12__remove_since_column_from_customer_table.sql
V4_11__add_unique_key_on_employee_table.sql
```

### -> Testy
- testy oparte o baze danych in memory, np. H2
- testy oparte o rzeczywista baze danych stojaca gdzies w infrastrukturze
firmy 
- testy oparte o technologie testcointainers
- testy przy wykorzystaniu adnotacji @SpringDataJpa - o tym opowiemy 
jak przejdziemy do omowienia projektu Spring Boot, natomiast bedzie to wisienka
na torcie, po omowieniu wszystkich trzech poprzednich mozliwosci.
Jest to pewnego rodzaju uzupelnienie do poprzednich trzech wymienionych mozliwosci - do tego potrzebny
jest Spring Boot.

### -> Testy oparte o baze danych in-memory np. H2
- testy oparte nie o prawdziwa baze danych aplikacji tylko o tymczasowa baze danych
ktora istnieje w pamieci RAM. Jak test sie skonczy to baza danych jest kasowana. Tylko i wylacznie
na potrzeby testow tworzymy baze danych i potem ja kasujemy. W swoim normalnym dzialaniu
aplikacja moze dzialac w oparciu o postgresql, ale na potrzeby testow mozemy korzystac z innej, ktora
istnieje w bazie danych np. H2.
- H2 jest tworzona na czas trwania testu (inaczej niz postgres ktory zapisuje dane na dysku)
- H2 korzysta sie tylko do testow - nie powinno jej sie uzywac jako normalnej bazy danych
w aplikacjach.
- wyilozowane srodowisko - developerzy nie wchodza sobie w droge

### -> Testy oparte o baze danych w srodowisku testowym
- Baza danych dla srodowiska developerskiego DEV,
- Baza danych dla srodowiska testowego TEST,
- Baza danych dla srodowiska produkcyjnego PROD - do tej bazy danych
developerzy nie maja bezposredniego manualnego dostepu, ta baza danych to swietosc,
to jest baza danych z ktorej korzysta aplikacja na produkcji oraz klienci aplikacji
- Baza danych dedykowana do testow integracyjnych
- -> Zalety: 
    - jestesmy blizej postgresa niz h2
    - latwiej wykryc bledy zwiazane z postgresem niz na h2
    - zaleta jest to ze jest to sam postgres
- -> Wady:
    - wspoldzielenie takiej bazy danych
    - jak trzech czlonkow zespolu uruchomi taka baze danych 
    to pozostala dwojka bedzie musiala posiwecic czas czemu poszlo cos nie tak
    - mozna tez zrobic to w ten sposob ze w testach bedzie sie korzystalo z lokalnej bazy danych
    - git flow/github flow - kod napisany lokalnie musi trafic do jakiegos wspolnego repo
    - jezeli testy IT beda wykorzystywaly lokalna baze danych to jezeli beda one na githubie
    uruchamiane to github bedzie musial miec postawiona baze danych

### -> Czy mozna polaczyc h2 i postgresa?
- biblioteka testcontainers - umozliwia takie cos
- wyizolowany postgres podnoszony tylko na ptorzeby testow
- tymczasowy postgres ma sie nijak do normalnego postgresa
- 