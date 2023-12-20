create table public.users
(
    user_id    varchar(50) not null
        primary key,
    login      varchar(50) not null,
    first_name varchar(50) not null,
    last_name  varchar(50) not null
);

INSERT INTO public.users (user_id, login, first_name, last_name)
VALUES
    ('DB_1_example-user-id-1', 'user-1', 'User', 'Userenko'),
    ('DB_1_example-user-id-2', 'user-2', 'Testuser', 'Testov'),
    ('DB_1_example-user-id-3', 'user-3', 'John', 'Doe'),
    ('DB_1_example-user-id-4', 'user-4', 'Alice', 'Johnson'),
    ('DB_1_example-user-id-5', 'user-5', 'Bob', 'Smith');