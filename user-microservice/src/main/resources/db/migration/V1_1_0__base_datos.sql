create extension if not exists "uuid-ossp";

create sequence persona_id_seq start 1;

-- Persona
create function generate_persona_id()
    returns text as
$$
declare
    seq_num int;
begin
    seq_num := nextval('persona_id_seq');
    return 'PERSONA-' || lpad(seq_num::text, 5, '0');
end;
$$ language plpgsql;

create table persona
(
    id         text         not null primary key,
    nombre     varchar(255) not null,
    edad       smallint     not null,
    genero     varchar(255),
    direccion  text,
    telefono   varchar(255),
    created_at timestamp    not null default now(),
    updated_at timestamp    not null default now()
);

-- Cliente
create sequence cliente_id_seq start 1;

create function generate_cliente_id()
    returns text as
$$
declare
    seq_num int;
begin
    seq_num := nextval('cliente_id_seq');
    return 'CLIENTE-' || lpad(seq_num::text, 5, '0');
end;
$$ language plpgsql;

create table cliente
(
    id         text         not null primary key,
    persona_id text         not null references persona (id),
    estado     varchar(255) not null,
    password   text         not null,
    created_at timestamp    not null default now(),
    updated_at timestamp    not null default now()
);


create table peticion
(
    peticion_id uuid                  default uuid_generate_v4() not null primary key,
    estado      varchar(255) not null,
    mensaje     varchar(255) not null,
    created_at  timestamp    not null default now(),
    updated_at  timestamp    not null default now()
)
