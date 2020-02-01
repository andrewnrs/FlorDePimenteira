
DROP VIEW IF EXISTS `valor_total_do_pedido`;

DROP VIEW IF EXISTS `valor_total_por_item_do_pedido`;

DROP VIEW IF EXISTS `itens_vendidos_hoje`;

CREATE VIEW `valor_total_do_pedido` AS
    SELECT 
        SUM((`item_pedido`.`valor_unit` * `item_pedido`.`qtd`)) AS `total`,
        `item_pedido`.`pedido_id` AS `pedido_id`
    FROM
        `item_pedido`
    GROUP BY `item_pedido`.`pedido_id`;
   
CREATE VIEW `valor_total_por_item_do_pedido` AS
    SELECT 
        SUM((`item_pedido`.`valor_unit` * `item_pedido`.`qtd`)) AS `total`,
        `item_pedido`.`item_cardapio_id` AS `item_cardapio_id`
    FROM
        `item_pedido`
    GROUP BY `item_pedido`.`pedido_id`, `item_pedido`.`item_cardapio_id`;
    
CREATE VIEW `itens_vendidos_hoje` AS
    SELECT 
        SUM(`ip`.`qtd`) AS `qtd`, `ic`.`nome` AS `nome`
    FROM
        (`item_pedido` `ip`
        JOIN `item_cardapio` `ic`)
    WHERE
        (`ic`.`id` = `ip`.`item_cardapio_id`)
    GROUP BY `ip`.`item_cardapio_id`