
DROP TABLE IF EXISTS operation;
CREATE TABLE operation (

    id SERIAL PRIMARY KEY,
    userID Integer,
    operationDate date,
    currencyFrom VARCHAR(255),
    currencyTo VARCHAR(255),
    amount numeric(2,2)

);