CREATE TABLE Product_Info (
  ID          		  INTEGER PRIMARY KEY,
  Item_Name           VARCHAR(30) NOT NULL,
  Unit_Price  		  BIGINT NOT NULL,
  Promo_Code           INTEGER);
  
 CREATE TABLE Active_Promotions (
  Promo_Code           INTEGER PRIMARY KEY,
  Promotion_Name       VARCHAR(30) NOT NULL,
  Effective_Price      BIGINT NOT NULL,
  Promotion_Type   BIGINT NOT NULL,
  Promo_Quantity_Num BIGINT);
 
  