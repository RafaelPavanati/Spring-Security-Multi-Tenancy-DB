DROP VIEW IF EXISTS public.vw_clientes;
DROP TABLE IF EXISTS public.clientes;
DROP SEQUENCE IF EXISTS clientes_seq;

create table public.usuarios(
  id_usuario bigint,
  email varchar(100),
  nome varchar(100),
  tenant varchar(100) default current_setting('context.tenant'::text) not null
);

create or replace view public.vw_usuarios as
select * from public.usuarios where tenant = current_setting('context.tenant'::text);

create sequence usuarios_seq;