create sequence if not exists public.id_seq as bigint start with 1000;

create table if not exists public.addresses
(
    id       bigint not null default nextval('id_seq') primary key,
    city     varchar(45),
    zip_code varchar(6),
    street   varchar(45)
);

create table if not exists public.user_infos
(
    id           bigint                      not null default nextval('id_seq') primary key,
    first_name   varchar(45)                 not null,
    last_name    varchar(45)                 not null,
    email_name   varchar(45)                 not null,
    password     varchar(255)                not null,
    pesel        varchar(11)                 not null,
    birthdate    timestamp without time zone not null,
    role         varchar(45)                 not null,
    phone_number varchar(9)                  not null,
    address_id   bigint                      not null,
    created_at   timestamp without time zone not null,
    modified_at  timestamp without time zone not null,
    constraint fk_address foreign key (address_id) references addresses (id)
);

create table if not exists public.patients
(
    id            bigint not null default nextval('id_seq') primary key,
    user_infos_id bigint not null,
    constraint fk_user_infos foreign key (user_infos_id) references user_infos (id)
);

create table if not exists public.doctors
(
    id             bigint      not null default nextval('id_seq') primary key,
    specialization varchar(45) not null,
    user_infos_id  bigint      not null,
    constraint fk_user_infos foreign key (user_infos_id) references user_infos (id)
);

create table if not exists public.appointments
(
    id               bigint                      not null default nextval('id_seq') primary key,
    patient_id       bigint                      not null,
    doctor_id        bigint                      not null,
    date             timestamp without time zone not null,
    patient_symptoms text,
    status           varchar(45),
    recommendations  text,
    created_at       timestamp without time zone not null,
    modified_at      timestamp without time zone not null,
    constraint fk_patient_id foreign key (patient_id) references patients (id),
    constraint fk_doctor_id foreign key (doctor_id) references doctors (id)
);