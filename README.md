## 프로젝트 환경
- Java 21
- PostgreSQL 16

## 프로젝트 설정

#### DDL

```sql
-- office 테이블 생성
-- auto-generated definition
create table office
(
    office_id       bigserial
        primary key,
    office_name     varchar(255) not null,
    office_code     varchar(10)
        unique,
    zonecode        varchar(5)   not null,
    office_address  varchar(255) not null,
    address_detail  varchar(255),
    president_name  varchar(255),
    president_email varchar(255) not null,
    mobile_number   varchar(255),
    phone_number    varchar(255)
);

-- office_users 테이블 생성
CREATE TABLE office_users (
    user_id       BIGSERIAL PRIMARY KEY,
    office_id     BIGINT NOT NULL
        CONSTRAINT fk_office
            REFERENCES office
            ON DELETE CASCADE,
    name          VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
    phone_number  VARCHAR(255),
    role          INTEGER NOT NULL
);

```