
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