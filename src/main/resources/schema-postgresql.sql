/* Serial = inteiro e autoincrement */
CREATE TABLE IF NOT EXISTS carro (
    id serial PRIMARY KEY, 
    marca varchar(20),
    modelo varchar(20),
    cor varchar(15),
    ano int
);
        -- comando magico: ( drop schema public; )