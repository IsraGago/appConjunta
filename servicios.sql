-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-11-2024 a las 12:44:16
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `appconjunta`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios_servicios`
--

CREATE TABLE `usuarios_servicios` (
  `codUsuario` int(11) NOT NULL,
  `codServicio` int(11) NOT NULL,
  `fechaInsercion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `usuarios_servicios`
--
ALTER TABLE `usuarios_servicios`
  ADD PRIMARY KEY (`codUsuario`,`codServicio`),
  ADD KEY `FK_SERVICIOS_SERVICIOS` (`codServicio`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `usuarios_servicios`
--
ALTER TABLE `usuarios_servicios`
  ADD CONSTRAINT `FK_SERVICIOS_SERVICIOS` FOREIGN KEY (`codServicio`) REFERENCES `servicios` (`codServicio`),
  ADD CONSTRAINT `FK_USUARIOS_USUARIOS` FOREIGN KEY (`codUsuario`) REFERENCES `usuarios` (`codUsuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
