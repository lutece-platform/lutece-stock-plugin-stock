--Corrige le problème d'affichage des images SITEBILLETTERIE-88

ALTER TABLE stock_offer ADD COLUMN min_tickets INT DEFAULT 0 NOT NULL;
ALTER TABLE stock_offer ADD COLUMN max_tickets INT DEFAULT 0 NOT NULL;

UPDATE stock_offer
SET min_tickets = 1
WHERE min_tickets = 0;

UPDATE stock_offer
SET max_tickets = CASE
    WHEN offer_genre_id = 1 THEN 10
    WHEN offer_genre_id = 2 THEN 2
    WHEN offer_genre_id = 3 THEN 4
END
WHERE max_tickets = 0;
