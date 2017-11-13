-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.1.21-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para teatro
CREATE DATABASE IF NOT EXISTS `teatro` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `teatro`;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla teatro.persona
CREATE TABLE IF NOT EXISTS `persona` (
  `dni` int(11) NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `apellido` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla teatro.compañia
CREATE TABLE IF NOT EXISTS `compañia` (
  `id_compañia` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `prestigio` varchar(50) DEFAULT NULL,
  `es_tercera` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id_compañia`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla teatro.tipoabono
CREATE TABLE IF NOT EXISTS `tipoabono` (
  `id_tipo_abono` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  PRIMARY KEY (`id_tipo_abono`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando estructura para tabla teatro.abono
CREATE TABLE IF NOT EXISTS `abono` (
  `dni` int(11) NOT NULL,
  `numero` int(11) NOT NULL,
  `fecha_compra` datetime DEFAULT NULL,
  `fecha_vencimiento` datetime DEFAULT NULL,
  `cantidad_usos` int(11) DEFAULT NULL,
  `id_tipo_abono` int(11) DEFAULT NULL,
  PRIMARY KEY (`dni`,`numero`),
  KEY `FK_abono_tipoabono` (`id_tipo_abono`),
  CONSTRAINT `FK__persona` FOREIGN KEY (`dni`) REFERENCES `persona` (`dni`),
  CONSTRAINT `FK_abono_tipoabono` FOREIGN KEY (`id_tipo_abono`) REFERENCES `tipoabono` (`id_tipo_abono`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla teatro.obrateatro
CREATE TABLE IF NOT EXISTS `obrateatro` (
  `id_obra_teatro` int(11) NOT NULL,
  `año` int(11) NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `genero` varchar(50) NOT NULL,
  PRIMARY KEY (`id_obra_teatro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla teatro.autor
CREATE TABLE IF NOT EXISTS `autor` (
  `dni_autor` int(11) NOT NULL,
  `id_obra_teatro` int(11) NOT NULL,
  PRIMARY KEY (`dni_autor`,`id_obra_teatro`),
  KEY `FK__obrateatro1` (`id_obra_teatro`),
  CONSTRAINT `FK__obrateatro1` FOREIGN KEY (`id_obra_teatro`) REFERENCES `obrateatro` (`id_obra_teatro`),
  CONSTRAINT `FK__persona1` FOREIGN KEY (`dni_autor`) REFERENCES `persona` (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla teatro.espectador
CREATE TABLE IF NOT EXISTS `espectador` (
  `id_espectador` int(11) NOT NULL,
  `tipo` varchar(50) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  PRIMARY KEY (`id_espectador`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla teatro.organismo
CREATE TABLE IF NOT EXISTS `organismo` (
  `id_organismo` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `telefono` varchar(50) DEFAULT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_organismo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla teatro.representacion
CREATE TABLE IF NOT EXISTS `representacion` (
  `id_obra_teatro` int(11) NOT NULL,
  `id_representacion` int(11) NOT NULL,
  `fecha_anuncio` datetime DEFAULT NULL,
  `fecha_obra` datetime DEFAULT NULL,
  `precio_compra` float DEFAULT NULL,
  `costo_mantenimiento` float DEFAULT NULL,
  `costo_preparacion` float DEFAULT NULL,
  PRIMARY KEY (`id_obra_teatro`,`id_representacion`),
  CONSTRAINT `FK__obrateatro123` FOREIGN KEY (`id_obra_teatro`) REFERENCES `obrateatro` (`id_obra_teatro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla teatro.participa
CREATE TABLE IF NOT EXISTS `participa` (
  `id_participa` int(11) NOT NULL,
  `dni` int(11) NOT NULL,
  `id_representacion` int(11) NOT NULL,
  `id_obra_teatro` int(11) NOT NULL,
  PRIMARY KEY (`id_participa`,`dni`,`id_representacion`,`id_obra_teatro`),
  KEY `FK__persona1234` (`dni`),
  KEY `FK__representacion1234` (`id_representacion`,`id_obra_teatro`),
  CONSTRAINT `FK__persona1234` FOREIGN KEY (`dni`) REFERENCES `persona` (`dni`),
  CONSTRAINT `FK__representacion1234` FOREIGN KEY (`id_representacion`, `id_obra_teatro`) REFERENCES `representacion` (`id_obra_teatro`, `id_representacion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla teatro.entrada
CREATE TABLE IF NOT EXISTS `entrada` (
  `numero` int(11) NOT NULL,
  `butaca` int(11) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `id_espectador` int(11) DEFAULT NULL,
  `id_obra_teatro` int(11) DEFAULT NULL,
  `dni` int(11) DEFAULT NULL,
  `id_representacion` int(11) DEFAULT NULL,
  `numero_abono` int(11) DEFAULT NULL,
  PRIMARY KEY (`numero`,`butaca`),
  KEY `FK_entrada_espectador` (`id_espectador`),
  KEY `FK_entrada_representacion` (`id_obra_teatro`,`id_representacion`),
  KEY `FK_entrada_abono` (`dni`,`numero_abono`),
  CONSTRAINT `FK_entrada_abono` FOREIGN KEY (`dni`, `numero_abono`) REFERENCES `abono` (`dni`, `numero`),
  CONSTRAINT `FK_entrada_espectador` FOREIGN KEY (`id_espectador`) REFERENCES `espectador` (`id_espectador`),
  CONSTRAINT `FK_entrada_representacion` FOREIGN KEY (`id_obra_teatro`, `id_representacion`) REFERENCES `representacion` (`id_obra_teatro`, `id_representacion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla teatro.subvencion
CREATE TABLE IF NOT EXISTS `subvencion` (
  `id_subvencion` int(11) NOT NULL,
  `id_obra_teatro` int(11) NOT NULL,
  `id_organismo` int(11) NOT NULL,
  `aporte` float DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`id_subvencion`,`id_obra_teatro`,`id_organismo`),
  KEY `FK__obrateatro` (`id_obra_teatro`),
  KEY `FK_subvencion_organismo` (`id_organismo`),
  CONSTRAINT `FK__obrateatro` FOREIGN KEY (`id_obra_teatro`) REFERENCES `obrateatro` (`id_obra_teatro`),
  CONSTRAINT `FK_subvencion_organismo` FOREIGN KEY (`id_organismo`) REFERENCES `organismo` (`id_organismo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla teatro.venta
CREATE TABLE IF NOT EXISTS `venta` (
  `nro_venta` int(11) NOT NULL,
  `precio` float NOT NULL,
  `fecha` datetime NOT NULL,
  `id_obra_teatro` int(11) NOT NULL,
  `id_compañia` int(11) NOT NULL,
  PRIMARY KEY (`nro_venta`),
  KEY `FK__obrateatro12` (`id_obra_teatro`),
  KEY `FK__compañia12` (`id_compañia`),
  CONSTRAINT `FK__compañia12` FOREIGN KEY (`id_compañia`) REFERENCES `compañia` (`id_compañia`),
  CONSTRAINT `FK__obrateatro12` FOREIGN KEY (`id_obra_teatro`) REFERENCES `obrateatro` (`id_obra_teatro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
-- Volcando estructura para tabla teatro.rol
CREATE TABLE IF NOT EXISTS `rol` (
  `id_participa` int(11) NOT NULL,
  `dni` int(11) NOT NULL,
  `id_representacion` int(11) NOT NULL,
  `id_obra_teatro` int(11) NOT NULL,
  `rol` varchar(50) NOT NULL,
  PRIMARY KEY (`id_participa`,`dni`,`id_representacion`,`id_obra_teatro`,`rol`),
  CONSTRAINT `FK__participa` FOREIGN KEY (`id_participa`, `dni`, `id_representacion`, `id_obra_teatro`) REFERENCES `participa` (`id_participa`, `dni`, `id_representacion`, `id_obra_teatro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
