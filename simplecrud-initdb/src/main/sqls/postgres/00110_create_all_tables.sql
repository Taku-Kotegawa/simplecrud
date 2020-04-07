-- User テーブル
create table if not exists  users (
    uid varchar(60) primary key,
    name varchar(60),
    pass varchar(255),
    mail varchar(254),
    status boolean,
    created_at timestamp,
    changed_at timestamp,
    comment text
)
;

COMMENT ON TABLE users IS 'ユーザ';

COMMENT ON COLUMN users.uid IS 'ユーザID';
COMMENT ON COLUMN users.name IS '氏名';
COMMENT ON COLUMN users.pass IS 'パスワード';
COMMENT ON COLUMN users.mail IS 'メール';
COMMENT ON COLUMN users.status IS 'ステータス';
COMMENT ON COLUMN users.created_at IS '作成日時';
COMMENT ON COLUMN users.changed_at IS '更新日時';
