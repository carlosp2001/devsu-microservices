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
    id         text         not null primary key,
    saldo      decimal      not null,
    tipo       varchar(255) not null,
    estado     boolean      not null,
    cliente_id text         not null,
    created_at timestamp    not null default now(),
    updated_at timestamp    not null default now()
);

-- Peticion
create table peticion
(
    peticion_id uuid         not null primary key,
    estado      varchar(255) not null,
    mensaje     varchar(255) not null,
    created_at  timestamp    not null default now(),
    updated_at  timestamp    not null default now()
);


-- Movimiento
create sequence movimiento_id_seq start 1;

create function generate_movimiento_id()
    returns text as
$$
declare
    seq_num int;
begin
    seq_num := nextval('movimiento_id_seq');
    return 'MOVIMIENTO-' || lpad(seq_num::text, 5, '0');
end;
$$ language plpgsql;

create table movimiento
(
    id               text         not null primary key,
    tipo             varchar(255) not null,
    monto            decimal      not null,
    cuenta_id        text         not null,
    saldo_inicial    decimal      not null,
    saldo_disponible decimal      not null,
    created_at       timestamp    not null default now(),
    updated_at       timestamp    not null default now()
);
