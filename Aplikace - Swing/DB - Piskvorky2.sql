
-- pro zruseni tabulky vysledku her
DROP TABLE APP.HISTORY;


-- pro vytvoreni nove tabulky vysledku her
CREATE TABLE APP.HISTORY(
ID INT NOT NULL,
PLAYER1 VARCHAR(30),
PLAYER2 VARCHAR(30),
RESULTS INT,
constraint PK_HISTORY primary key (ID)
);


--pro ziskani vsech vysledku (pokud neni do filteru zadano jmeno)
--      vybrane vysledky jsou serazeny od posledniho k prvnimu (posledni hra bude na zacatku)
select * from APP.HISTORY order by (0-ID);


--pro vybrani vsech vysledku podle hledaneho jmena
--      SEARCHED_NAME je jmeno(varchar, podle ktereho jsou vysledky hledany
select * from APP.HISTORY where PLAYER1 = SEARCHED_NAME or PLAYER2 = SEARCHED_NAME order by (0-ID);


--pro nalezeni nejvyssiho ID(int) hry
select max(id) dalsi from APP.HISTORY;


--pro vlozeni noveho vysledku do tabulky
--      NEW_ID je identifikacni cislo(int) hry
--      PLAYER1_NAME je jmeno(varchar) prvniho hrace
--      PLAYER2_NAME je jmeno(varchar druheho hrace
--      RESULT_NUMBER je cislo(int) hrace, ktery vyhral (1 nebo 2), pro remizu je cislo 0.
insert into APP.HISTORY (ID, PLAYER1, PLAYER2, RESULTS) values (NEW_ID, 'PLAYER1_NAME', 'PLAYER2_NAME', RESULTS_NUMBER);


