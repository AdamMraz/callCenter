CREATE SEQUENCE IF NOT EXISTS seq_task INCREMENT 1 MINVALUE 1 MAXVALUE 999999999;
create table if not exists task
(
    task_id     bigint not null
    default seq_task.nextval
    constraint task_pkey
    primary key,
    comment     varchar(255),
    create_date timestamp,
    number      bigint not null,
    status      integer,
    update_date timestamp
);
INSERT INTO task (comment, create_date, number, status, update_date)
        VALUES ('', '2021-09-26 22:17:38.281000', 2255, 0, null);
INSERT INTO task (comment, create_date, number, status, update_date)
        VALUES ('', '2021-09-26 22:17:42.019000', 65, 0, null);
INSERT INTO task (comment, create_date, number, status, update_date)
        VALUES ('', '2021-09-26 22:17:51.492000', 417, 0, null);
INSERT INTO task (comment, create_date, number, status, update_date)
        VALUES ('Timeout', '2021-09-26 22:17:28.194000', 625, 0, '2021-09-26 22:19:24.958000');
INSERT INTO task (comment, create_date, number, status, update_date)
        VALUES ('Completed', '2021-09-26 22:17:32.657000', 412, 1, '2021-09-26 22:20:18.220000');
INSERT INTO task (comment, create_date, number, status, update_date)
        VALUES ('', '2021-09-26 22:17:53.867000', 632, 1, '2021-09-26 22:20:49.480000');
INSERT INTO task (comment, create_date, number, status, update_date)
        VALUES ('', '2021-09-26 22:17:47.778000', 32, 1, '2021-09-26 22:20:38.985000');