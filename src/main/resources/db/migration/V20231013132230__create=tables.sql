CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
    id uuid,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    balance decimal not null,
    document varchar(255) not null,
    email varchar(255) not null,
    password integer not null,
    primary key(id)
);

CREATE TABLE transactions (
    id uuid,
    sender uuid not null,
    receiver uuid not null,
    amount decimal not null,
    transaction_date DATE DEFAULT NOW()::DATE,
    primary key (id),
    foreign key(sender) references users(id),
    foreign key(receiver) references users(id)
);
