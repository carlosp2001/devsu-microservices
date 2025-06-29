create extension if not exists "uuid-ossp";

create sequence cuenta_id_seq start 1;

-- Cuenta
create function generate_cuenta_id()
    returns text as
$$
declare
    seq_num int;
begin
    seq_num := nextval('cuenta_id_seq');
    return 'CUENTA-' || lpad(seq_num::text, 5, '0');
end;
$$ language plpgsql;

create table cuenta
(
    id            text         not null primary key,
    saldo_inicial decimal      not null,
    tipo          varchar(255) not null,
    estado        boolean      not null,
    cliente_id    text         not null,
    created_at    timestamp    not null default now(),
    updated_at    timestamp    not null default now()
);

-- Cliente
create table peticion
(
    peticion_id uuid not null primary key,
    estado      varchar(255) not null,
    mensaje     varchar(255) not null,
    created_at  timestamp    not null default now(),
    updated_at  timestamp    not null default now()
)
