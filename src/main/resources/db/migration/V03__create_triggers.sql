DELIMITER //
CREATE TRIGGER `restaurante`.`Estabelece_Valor`
  BEFORE INSERT ON `restaurante`.item_pedido
  FOR EACH ROW
BEGIN
	DECLARE valor_item decimal(10,2);
    
    select valor_vigente INTO valor_item
    from item_cardapio where item_cardapio.id=NEW.item_cardapio_id;

    set NEW.valor_unit  = valor_item;
    
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER `restaurante`.`Inicializa_Conta_Valor_Total`
  AFTER INSERT ON `restaurante`.item_pedido
  FOR EACH ROW
BEGIN
	DECLARE valor_total decimal(10,2);
    
    select total into valor_total from valor_total_do_pedido v where v.pedido_id = NEW.pedido_id; 
	UPDATE pedido SET total = valor_total where pedido.id = new.pedido_id;
END //
DELIMITER ;


DELIMITER //
CREATE TRIGGER `restaurante`.`Atualiza_Horario_Entregue`
  BEFORE UPDATE ON `restaurante`.item_pedido
  FOR EACH ROW
BEGIN
	DECLARE valor_total decimal(10,2);
	IF(OLD.estado <> NEW.estado) THEN
		IF(NEW.estado = 'ENTREGUE') THEN
			set NEW.data_entregue  = CURRRENT_TIMESTAMP();
		END IF;
	END IF;
END //
DELIMITER ;