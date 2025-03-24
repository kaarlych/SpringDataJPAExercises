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