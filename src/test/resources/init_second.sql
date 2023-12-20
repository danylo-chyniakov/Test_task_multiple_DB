create table public.users2
(
    user_id2    varchar(50) not null
        primary key,
    login2      varchar(50) not null,
    first_name2 varchar(50) not null,
    last_name2  varchar(50) not null
);

INSERT INTO public.users2 (user_id2, login2, first_name2, last_name2)
VALUES
    ('DB_2_user_id_1', 'login_1', 'John', 'Doe'),
    ('DB_2_user_id_2', 'login_2', 'Jane', 'Doe'),
    ('DB_2_user_id_3', 'login_3', 'Bob', 'Smith'),
    ('DB_2_user_id_4', 'login_4', 'Alice', 'Johnson'),
    ('DB_2_user_id_5', 'login_5', 'Charlie', 'Brown');