CREATE DATABASE data_warehouse;

USE data_warehouse;

CREATE TABLE CURRENCY(
	code VARCHAR(255) PRIMARY KEY,
	name VARCHAR(255),
	cash_buying DECIMAL(15,2),
	tele_buying DECIMAL(15,2),
	selling DECIMAL(15,2)
)

BULK INSERT CURRENCY
FROM 'E:\Exercise Java\springs\DataWarehouse\data.csv'
WITH (
   FIELDTERMINATOR = ',',
   ROWTERMINATOR = '\n',
   FIRSTROW = 1 -- Use if you have a header row and want to skip it
);


SELECT * FROM CURRENCY

EXEC  sp_rename 'CURRENCY','FOREIGN_CURRENCY' -- Rename

SELECT * FROM FOREIGN_CURRENCY