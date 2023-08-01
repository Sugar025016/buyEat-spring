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
--         CURRENT_TIMESTAMP,
--         CURRENT_TIMESTAMP
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
--         CURRENT_TIMESTAMP,
--         CURRENT_TIMESTAMP
--     );
INSERT
    IGNORE INTO address (
        id,
        city,
        area,
        detail,
        create_time,
        update_time
    )
VALUES
    (
        1,
        'cityA',
        'area',
        "detail",
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    ),
    (
        2,
        'cityB',
        'area',
        "detail",
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    );

INSERT
    IGNORE INTO shop (
        id,
        name,
        phone,
        description,
        is_delete,
        disable,
        create_time,
        update_time,
        address
    )
VALUES
    (
        1,
        'valueA',
        '123456789',
        "description",
        false,
        false,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        1
    ),
    (
        2,
        'valueB',
        '123456789',
        "description",
        false,
        1,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        2
    );

INSERT
    IGNORE INTO product (id, name, prise, shop_id, is_delete, disable)
VALUES
    (1, 'value1A', 10, 1, false, false),
    (2, 'value1B', 20, 1, false, false),
    (3, 'value1C', 30, 1, false, false),
    (4, 'value1D', 40, 1, false, false);

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
    IGNORE INTO user (
        id,
        name,
        phone,
        account,
        password,
        role,
        create_time,
        update_time
    )
VALUES
    (
        1,
        'valueA',
        '123456789',
        'admin',
        'password',
        'admin',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    ),
    (
        2,
        'valueB',
        '123456789',
        'user',
        'password',
        'user',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    );