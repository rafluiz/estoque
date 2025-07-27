drop table centrocusto;

create table centrocusto(

    id bigint not null auto_increment,
    nome varchar(100) not null unique,
    apelido varchar(100) not null unique,
    ativo boolean not null,

    primary key(id)

);