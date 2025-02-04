## 프로젝트 환경
- Java 21
- PostgreSQL 16

## 프로젝트 설정

#### DDL

```sql
-- office 테이블 생성
CREATE TABLE office (
    office_id       BIGSERIAL PRIMARY KEY,
    office_code     VARCHAR(10) UNIQUE,
    zonecode        VARCHAR(5)   NOT NULL,
    office_address  VARCHAR(255) NOT NULL,
    address_detail  VARCHAR(255),
    president_name  VARCHAR(255),
    president_email VARCHAR(255),
    mobile_number   VARCHAR(255),
    phone_number    VARCHAR(255)
);

ALTER TABLE office OWNER TO admin;

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