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
$$;

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
$$;

create table cliente
(
    id         text         not null primary key,
    cliente_id varchar(255) not null,
    persona_id text         not null references persona (id),
    estado     varchar(255) not null,
    password   text         not null,
    created_at timestamp    not null default now(),
    updated_at timestamp    not null default now()
);
