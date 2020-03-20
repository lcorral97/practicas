Create table temporal (
id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
observation_time varchar(8),
temperature integer,
weather_descriptions varchar(50),
wind_speed integer,
wind_degree integer,
wind_dir varchar(5),
pressure integer,
precip double,
humidity integer
);

/*Delete From temporal;

Alter table temporal
alter column id
restart with 1;*/

Select * From temporal;