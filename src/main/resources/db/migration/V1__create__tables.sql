create table if not exists statistic (
    id varchar(36) not null primary key,
    user_id varchar(36) not null,
    math_type varchar(15) not null,
    operation_type varchar(150) not null,
    value double precision not null,
    created_at timestamp not null default current_timestamp,
    status varchar(20) not null default 'ACTIVE'
);
