-- MySQL dump 10.13  Distrib 8.0.45, for Linux (x86_64)
--
-- Host: localhost    Database: BD_Chepps
-- ------------------------------------------------------
-- Server version	8.0.45-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Cliente`
--

DROP TABLE IF EXISTS `Cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Cliente` (
  `idCliente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `tipoCliente` varchar(255) DEFAULT 'minorista',
  `eliminado` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Contacto`
--

DROP TABLE IF EXISTS `Contacto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Contacto` (
  `idContacto` int NOT NULL AUTO_INCREMENT,
  `telefono` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL DEFAULT 'sin email',
  PRIMARY KEY (`idContacto`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Domicilio`
--

DROP TABLE IF EXISTS `Domicilio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Domicilio` (
  `idDomicilio` int NOT NULL AUTO_INCREMENT,
  `direccion` text NOT NULL,
  `provincia` varchar(255) NOT NULL,
  PRIMARY KEY (`idDomicilio`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Historial`
--

DROP TABLE IF EXISTS `Historial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Historial` (
  `idHistorial` int NOT NULL AUTO_INCREMENT,
  `idProductoManufacturado` int DEFAULT NULL,
  `idInsumo` int DEFAULT NULL,
  `cantidad` double DEFAULT NULL,
  `denominacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idHistorial`),
  KEY `fk_historial_insumo` (`idInsumo`),
  KEY `fk_historial_producto_manufacturado` (`idProductoManufacturado`),
  CONSTRAINT `fk_historial_insumo` FOREIGN KEY (`idInsumo`) REFERENCES `Insumo` (`idInsumo`),
  CONSTRAINT `fk_historial_producto_manufacturado` FOREIGN KEY (`idProductoManufacturado`) REFERENCES `ProductoManufacturado` (`idProductoManufacturado`),
  CONSTRAINT `Historial_ibfk_1` FOREIGN KEY (`idProductoManufacturado`) REFERENCES `ProductoManufacturado` (`idProductoManufacturado`),
  CONSTRAINT `Historial_ibfk_2` FOREIGN KEY (`idInsumo`) REFERENCES `Insumo` (`idInsumo`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Insumo`
--

DROP TABLE IF EXISTS `Insumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Insumo` (
  `idInsumo` int NOT NULL AUTO_INCREMENT,
  `idUnidadMedida` int DEFAULT NULL,
  `idProductoDetalle` int DEFAULT NULL,
  `precio` double DEFAULT '0',
  `denominacion` varchar(255) DEFAULT NULL,
  `esParaElaborar` tinyint(1) NOT NULL,
  `eliminado` tinyint(1) DEFAULT '0',
  `idMarca` int DEFAULT NULL,
  `costo` decimal(10,0) DEFAULT '0',
  PRIMARY KEY (`idInsumo`),
  KEY `idUnidadMedida` (`idUnidadMedida`),
  KEY `idProductoDetalle` (`idProductoDetalle`),
  KEY `fk_insumo_marca` (`idMarca`),
  CONSTRAINT `fk_insumo_marca` FOREIGN KEY (`idMarca`) REFERENCES `Marca` (`idMarca`),
  CONSTRAINT `Insumo_ibfk_2` FOREIGN KEY (`idUnidadMedida`) REFERENCES `UnidadMedida` (`idUnidadMedida`),
  CONSTRAINT `Insumo_ibfk_3` FOREIGN KEY (`idProductoDetalle`) REFERENCES `ProductoDetalle` (`idProductoDetalle`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Marca`
--

DROP TABLE IF EXISTS `Marca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Marca` (
  `idMarca` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`idMarca`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Orden`
--

DROP TABLE IF EXISTS `Orden`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Orden` (
  `idOrden` int NOT NULL AUTO_INCREMENT,
  `idCliente` int DEFAULT NULL,
  `idContacto` int DEFAULT NULL,
  `idDomicilio` int DEFAULT NULL,
  `idUsuario` int DEFAULT NULL,
  `fecha_carga` timestamp NOT NULL,
  `fecha_entrega` timestamp NOT NULL,
  `subTotal` double NOT NULL DEFAULT '0',
  `total` double NOT NULL DEFAULT '0',
  `estado` enum('aceptada','pagada','parcial_pendiente','completado','cancelada') NOT NULL DEFAULT 'parcial_pendiente',
  `pagado` double NOT NULL DEFAULT '0',
  `tipoOrden` enum('PAGO','COMPRA','VENTA','AGREGACION_DE_STOCK','DEVOLUCION_O_ELIMINACION_DE_STOCK') DEFAULT NULL,
  `tipoPago` enum('EFECTIVO','TRANSFERENCIA','VILLETERAS_VIRTUALES','OTROS') DEFAULT NULL,
  PRIMARY KEY (`idOrden`),
  KEY `idCliente` (`idCliente`),
  KEY `idContacto` (`idContacto`),
  KEY `idDomicilio` (`idDomicilio`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `Orden_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `Cliente` (`idCliente`),
  CONSTRAINT `Orden_ibfk_2` FOREIGN KEY (`idContacto`) REFERENCES `Contacto` (`idContacto`),
  CONSTRAINT `Orden_ibfk_3` FOREIGN KEY (`idDomicilio`) REFERENCES `Domicilio` (`idDomicilio`),
  CONSTRAINT `Orden_ibfk_4` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `OrdenDetalle`
--

DROP TABLE IF EXISTS `OrdenDetalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `OrdenDetalle` (
  `idOrdenDetalle` int NOT NULL AUTO_INCREMENT,
  `idOrden` int DEFAULT NULL,
  `idProductoManufacturado` int DEFAULT NULL,
  `idProductoInsumo` int DEFAULT NULL,
  `descuentosPorProducto` double DEFAULT '0',
  `observaciones` text,
  `cantidadProducto` double DEFAULT NULL,
  `cantidadInsumo` double DEFAULT NULL,
  `precioInsumo` decimal(19,4) DEFAULT '0.0000',
  `precioProducto` decimal(19,4) DEFAULT '0.0000',
  PRIMARY KEY (`idOrdenDetalle`),
  KEY `idOrden` (`idOrden`),
  KEY `idProductoManufacturado` (`idProductoManufacturado`),
  KEY `idProductoInsumo` (`idProductoInsumo`),
  CONSTRAINT `OrdenDetalle_ibfk_1` FOREIGN KEY (`idOrden`) REFERENCES `Orden` (`idOrden`),
  CONSTRAINT `OrdenDetalle_ibfk_2` FOREIGN KEY (`idProductoManufacturado`) REFERENCES `ProductoManufacturado` (`idProductoManufacturado`),
  CONSTRAINT `OrdenDetalle_ibfk_3` FOREIGN KEY (`idProductoInsumo`) REFERENCES `Insumo` (`idInsumo`)
) ENGINE=InnoDB AUTO_INCREMENT=268 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ProductoDetalle`
--

DROP TABLE IF EXISTS `ProductoDetalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ProductoDetalle` (
  `idProductoDetalle` int NOT NULL AUTO_INCREMENT,
  `stockActual` double DEFAULT NULL,
  `stockMinimo` double DEFAULT NULL,
  PRIMARY KEY (`idProductoDetalle`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ProductoManufacturado`
--

DROP TABLE IF EXISTS `ProductoManufacturado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ProductoManufacturado` (
  `idProductoManufacturado` int NOT NULL AUTO_INCREMENT,
  `idUnidadMedida` int DEFAULT NULL,
  `idProductoDetalle` int DEFAULT NULL,
  `denominacion` text NOT NULL,
  `precio` double NOT NULL DEFAULT '1',
  `cantVendidas` int DEFAULT '0',
  `descripcion` text NOT NULL,
  `tiempo_estimado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `eliminado` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`idProductoManufacturado`),
  KEY `idUnidadMedida` (`idUnidadMedida`),
  KEY `idProductoDetalle` (`idProductoDetalle`),
  CONSTRAINT `ProductoManufacturado_ibfk_1` FOREIGN KEY (`idUnidadMedida`) REFERENCES `UnidadMedida` (`idUnidadMedida`),
  CONSTRAINT `ProductoManufacturado_ibfk_2` FOREIGN KEY (`idProductoDetalle`) REFERENCES `ProductoDetalle` (`idProductoDetalle`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ProductoManufacturado_Insumo`
--

DROP TABLE IF EXISTS `ProductoManufacturado_Insumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ProductoManufacturado_Insumo` (
  `idProductoManufacturado` int NOT NULL,
  `idInsumo` int NOT NULL,
  PRIMARY KEY (`idProductoManufacturado`,`idInsumo`),
  KEY `idInsumo` (`idInsumo`),
  CONSTRAINT `ProductoManufacturado_Insumo_ibfk_1` FOREIGN KEY (`idProductoManufacturado`) REFERENCES `ProductoManufacturado` (`idProductoManufacturado`),
  CONSTRAINT `ProductoManufacturado_Insumo_ibfk_2` FOREIGN KEY (`idInsumo`) REFERENCES `Insumo` (`idInsumo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UnidadMedida`
--

DROP TABLE IF EXISTS `UnidadMedida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `UnidadMedida` (
  `idUnidadMedida` int NOT NULL AUTO_INCREMENT,
  `denominacion` varchar(255) NOT NULL,
  PRIMARY KEY (`idUnidadMedida`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nombreUsuario` varchar(50) NOT NULL,
  `clave` blob NOT NULL,
  `role` varchar(25) NOT NULL DEFAULT (_utf8mb4'user'),
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-14 16:36:50
