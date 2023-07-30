-- INSERT
--     IGNORE INTO shop (
--         id,
--         name,
--         phone,
--         address,
--         description,
--         account,
--         password,
--         img,
--         is_delete,
--         disable,
--         create_time,
--         change_time
--     )
-- VALUES
--     (
--         1,
--         'valueA',
--         '123456789',
--         "address",
--         "description",
--         "account",
--         "password",
--         "img",
--         false,
--         false,
--         CURRENT_TIME,
--         CURRENT_TIME
--     ),
--     (
--         2,
--         'valueB',
--         '123456789',
--         "address",
--         "description",
--         "account",
--         "password",
--         "img",
--         false,
--         1,
--         CURRENT_TIME,
--         CURRENT_TIME
--     );

INSERT
    IGNORE INTO shop (
        id,
        name,
        phone,
        address,
        description,
        account,
        password,
        is_delete,
        disable,
        create_time,
        change_time
    )
VALUES
    (
        1,
        'valueA',
        '123456789',
        "address",
        "description",
        "account",
        "password",
        false,
        false,
        CURRENT_TIME,
        CURRENT_TIME
    ),
    (
        2,
        'valueB',
        '123456789',
        "address",
        "description",
        "account",
        "password",
        false,
        1,
        CURRENT_TIME,
        CURRENT_TIME
    );

INSERT
    IGNORE INTO product (id, name, shop_id)
VALUES
    (1, 'value1A', 1),
    (2, 'value1B', 1),
    (3, 'value1C', 1),
    (4, 'value1D', 1);

INSERT
    IGNORE INTO category (id, name)
VALUES
    (1, 'categoryA'),
    (2, 'categoryB'),
    (3, 'categoryB'),
    (4, 'categoryB');

INSERT INTO
    category_product (category_id, product_id)
select
    1,
    1
where
    not exists (
        select
            1
        from
            category_product
        where
            category_id = 1
            and product_id = 1
    );

INSERT INTO
    category_product (category_id, product_id)
select
    2,
    2
where
    not exists (
        select
            1
        from
            category_product
        where
            category_id = 2
            and product_id = 2
    );

INSERT INTO
    category_product (category_id, product_id)
select
    3,
    1
where
    not exists (
        select
            1
        from
            category_product
        where
            category_id = 3
            and product_id = 1
    );

INSERT INTO
    category_product (category_id, product_id)
select
    3,
    2
where
    not exists (
        select
            1
        from
            category_product
        where
            category_id = 3
            and product_id = 2
    );

INSERT
    IGNORE INTO user (id, name, phone, account, password, role)
VALUES
    (
        1,
        'valueA',
        '123456789',
        'admin',
        'password',
        'admin'
    ),
    (
        2,
        'valueB',
        '123456789',
        'user',
        'password',
        'user'
    );