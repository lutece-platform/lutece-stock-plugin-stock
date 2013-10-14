
--
-- Base de données: 'billetterie'
--

-- --------------------------------------------------------

--
-- Structure de la table 'stock_category'
--

CREATE TABLE IF NOT EXISTS stock_category (
  id_category int(11) NOT NULL DEFAULT '0',
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  description varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  parent_id int(11) DEFAULT NULL,
  provider_id int(11) DEFAULT NULL,
  PRIMARY KEY (id_category),
  KEY FK9792900769015ED5 (provider_id),
  KEY FK97929007A735EC89 (parent_id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_category_attribute'
--

CREATE TABLE IF NOT EXISTS stock_category_attribute (
  owner_id int(11) NOT NULL,
  attribute_key varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  attribute_value text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (owner_id,attribute_key)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_category_attribute_date'
--

CREATE TABLE IF NOT EXISTS stock_category_attribute_date (
  id int(11) NOT NULL AUTO_INCREMENT,
  owner_id int(11) NOT NULL,
  attribute_key varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  attribute_value timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY owner_id (owner_id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_category_attribute_num'
--

CREATE TABLE IF NOT EXISTS stock_category_attribute_num (
  owner_id int(11) NOT NULL,
  attribute_key varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  attribute_value decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (owner_id,attribute_key)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_offer'
--

CREATE TABLE IF NOT EXISTS stock_offer (
  id_offer int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  description varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  quantity int(11) NOT NULL,
  price float NOT NULL DEFAULT '0',
  statut varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  product_id int(11) NOT NULL,
  offer_genre_id int(11) NOT NULL,
  PRIMARY KEY (id_offer),
  KEY product_id (product_id),
  KEY offer_genre_id (offer_genre_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_offer_attribute'
--

CREATE TABLE IF NOT EXISTS stock_offer_attribute (
  id int(11) NOT NULL AUTO_INCREMENT,
  owner_id int(11) NOT NULL,
  attribute_key varchar(255) NOT NULL,
  attribute_value text NOT NULL,
  PRIMARY KEY (id),
  KEY owner_id (owner_id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_offer_attribute_date'
--

CREATE TABLE IF NOT EXISTS stock_offer_attribute_date (
  owner_id int(11) NOT NULL,
  attribute_key varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  attribute_value timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (owner_id,attribute_key)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_offer_attribute_num'
--

CREATE TABLE IF NOT EXISTS stock_offer_attribute_num (
  id int(11) NOT NULL AUTO_INCREMENT,
  owner_id int(11) NOT NULL,
  attribute_key varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  attribute_value decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY owner_id (owner_id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_offer_genre'
--

CREATE TABLE IF NOT EXISTS stock_offer_genre (
  id_offer_genre int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (id_offer_genre)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_offer_genre_attribute'
--

CREATE TABLE IF NOT EXISTS stock_offer_genre_attribute (
  owner_id int(11) NOT NULL,
  attribute_key varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  attribute_value text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (owner_id,attribute_key)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_offer_genre_attribute_date'
--

CREATE TABLE IF NOT EXISTS stock_offer_genre_attribute_date (
  owner_id int(11) NOT NULL,
  attribute_key varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  attribute_value timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (owner_id,attribute_key)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_offer_genre_attribute_num'
--

CREATE TABLE IF NOT EXISTS stock_offer_genre_attribute_num (
  owner_id int(11) NOT NULL,
  attribute_key varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  attribute_value decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (owner_id,attribute_key)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_product'
--

CREATE TABLE IF NOT EXISTS stock_product (
  id_product int(11) NOT NULL DEFAULT '0',
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  description text COLLATE utf8_unicode_ci,
  price float DEFAULT NULL,
  category_id_category int(11) DEFAULT NULL,
  provider_id_provider int(11) DEFAULT NULL,
  PRIMARY KEY (id_product),
  KEY FK1BA2F4C6FC6A399A (category_id_category),
  KEY provider_id_provider (provider_id_provider),
  KEY provider_id_provider_2 (provider_id_provider)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE IF NOT EXISTS stock_product_image (
  id_product int(11) NOT NULL,
  tb_image blob,
  image mediumblob,
  PRIMARY KEY (id_product)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


-- --------------------------------------------------------

--
-- Structure de la table 'stock_product_attribute'
--

CREATE TABLE IF NOT EXISTS stock_product_attribute (
  owner_id int(11) NOT NULL,
  attribute_key varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  attribute_value text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (owner_id,attribute_key)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_product_attribute_date'
--

CREATE TABLE IF NOT EXISTS stock_product_attribute_date (
  owner_id int(11) NOT NULL,
  attribute_key varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  attribute_value timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (owner_id,attribute_key)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_product_attribute_num'
--

CREATE TABLE IF NOT EXISTS stock_product_attribute_num (
  owner_id int(11) NOT NULL,
  attribute_key varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  attribute_value decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (owner_id,attribute_key)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


-- --------------------------------------------------------

--
-- Structure de la table 'stock_provider'
--

CREATE TABLE IF NOT EXISTS stock_provider (
  id_provider int(11) NOT NULL DEFAULT '0',
  address varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  contactName varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  mail varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  phone_number varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (id_provider)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_provider_attribute'
--

CREATE TABLE IF NOT EXISTS stock_provider_attribute (
  owner_id int(11) NOT NULL,
  attribute_key varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  attribute_value text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (owner_id,attribute_key)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_provider_attribute_date'
--

CREATE TABLE IF NOT EXISTS stock_provider_attribute_date (
  owner_id int(11) NOT NULL,
  attribute_key varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  attribute_value timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (owner_id,attribute_key)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_provider_attribute_num'
--

CREATE TABLE IF NOT EXISTS stock_provider_attribute_num (
  owner_id int(11) NOT NULL,
  attribute_key varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  attribute_value decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (owner_id,attribute_key)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_provider_stock_category'
--

CREATE TABLE IF NOT EXISTS stock_provider_stock_category (
  stock_provider_id_provider int(11) NOT NULL DEFAULT '0',
  products_id_category int(11) NOT NULL DEFAULT '0',
  UNIQUE KEY products_id_category (products_id_category),
  KEY FKF592D1EC8CC9E500 (products_id_category),
  KEY FKF592D1ECF087CD7C (stock_provider_id_provider)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_purchase'
--

CREATE TABLE IF NOT EXISTS stock_purchase (
  id_purchase int(11) NOT NULL,
  userName varchar(255) NOT NULL,
  quantity int(11) NOT NULL,
  offer_id int(11) NOT NULL,
  PRIMARY KEY (id_purchase)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_purchase_attribute'
--

CREATE TABLE IF NOT EXISTS stock_purchase_attribute (
  owner_id int(11) NOT NULL DEFAULT '0',
  attribute_key varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  attribute_value text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (owner_id,attribute_key)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_purchase_attribute_date'
--

CREATE TABLE IF NOT EXISTS stock_purchase_attribute_date (
  owner_id int(11) NOT NULL DEFAULT '0',
  attribute_key varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  attribute_value timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (owner_id,attribute_key)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_purchase_attribute_num'
--

CREATE TABLE IF NOT EXISTS stock_purchase_attribute_num (
  owner_id int(11) NOT NULL DEFAULT '0',
  attribute_key varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  attribute_value decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (owner_id,attribute_key)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_sequences'
--

CREATE TABLE IF NOT EXISTS stock_sequences (
  sequence_name varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  next_val bigint(20) DEFAULT NULL,
  PRIMARY KEY (sequence_name)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_ticket_product_statistic'
--

CREATE TABLE IF NOT EXISTS stock_ticket_product_statistic (
  id_product_statistic int(11) NOT NULL AUTO_INCREMENT,
  dayOfYear int(11) NOT NULL,
  `week` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  product_id_product int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id_product_statistic)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table 'stock_ticket_purchase_statistic'
--

CREATE TABLE IF NOT EXISTS stock_ticket_purchase_statistic (
  id_purchase_statistic int(11) NOT NULL,
  dayOfYear int(11) NOT NULL,
  `week` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  purchase_id_purchase int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id_purchase_statistic)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `stock_offer`
--
ALTER TABLE `stock_offer`
  ADD CONSTRAINT stock_offer_ibfk_1 FOREIGN KEY (product_id) REFERENCES stock_product (id_product);

--
-- Contraintes pour la table `stock_offer_attribute`
--
ALTER TABLE `stock_offer_attribute`
  ADD CONSTRAINT stock_offer_attribute_ibfk_2 FOREIGN KEY (owner_id) REFERENCES stock_offer (id_offer),
  ADD CONSTRAINT stock_offer_attribute_ibfk_1 FOREIGN KEY (owner_id) REFERENCES stock_offer (id_offer);

--
-- Contraintes pour la table `stock_offer_attribute_date`
--
ALTER TABLE `stock_offer_attribute_date`
  ADD CONSTRAINT stock_offer_attribute_date_ibfk_1 FOREIGN KEY (owner_id) REFERENCES stock_offer (id_offer);

--
-- Contraintes pour la table `stock_offer_attribute_num`
--
ALTER TABLE `stock_offer_attribute_num`
  ADD CONSTRAINT stock_offer_attribute_num_ibfk_1 FOREIGN KEY (owner_id) REFERENCES stock_offer (id_offer);

--
-- Contraintes pour la table `stock_product_attribute`
--
ALTER TABLE `stock_product_attribute`
  ADD CONSTRAINT stock_product_attribute_ibfk_3 FOREIGN KEY (owner_id) REFERENCES stock_product (id_product),
  ADD CONSTRAINT stock_product_attribute_ibfk_1 FOREIGN KEY (owner_id) REFERENCES stock_product (id_product),
  ADD CONSTRAINT stock_product_attribute_ibfk_2 FOREIGN KEY (owner_id) REFERENCES stock_product (id_product);

--
-- Contraintes pour la table `stock_product_attribute_date`
--
ALTER TABLE `stock_product_attribute_date`
  ADD CONSTRAINT stock_product_attribute_date_ibfk_1 FOREIGN KEY (owner_id) REFERENCES stock_product (id_product);

--
-- Contraintes pour la table `stock_product_attribute_num`
--
ALTER TABLE `stock_product_attribute_num`
  ADD CONSTRAINT stock_product_attribute_num_ibfk_1 FOREIGN KEY (owner_id) REFERENCES stock_product (id_product);

--
-- Structure de la table 'stock_product_subscription_user'
--

CREATE TABLE IF NOT EXISTS stock_product_subscription_user (
  id int(11) NOT NULL,
  userName varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  id_product int(11) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


--
-- Contraintes pour la table `stock_product_subscription_user`
--
ALTER TABLE `stock_product_subscription_user`
  ADD CONSTRAINT stock_product_subscription_user_ibfk_1 FOREIGN KEY (id_product) REFERENCES stock_product (id_product);