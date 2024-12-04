-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-12-2024 a las 10:25:46
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
-- Estructura de tabla para la tabla `servicios`
--

CREATE TABLE `servicios` (
  `codServicio` int(11) NOT NULL,
  `codUsuario` int(11) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `fechaCreacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `descripcion` varchar(255) NOT NULL,
  `ubicacion` varchar(255) NOT NULL,
  `numUsuariosActuales` int(11) NOT NULL DEFAULT 0,
  `maxUsuarios` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `servicios`
--

INSERT INTO `servicios` (`codServicio`, `codUsuario`, `titulo`, `fechaCreacion`, `descripcion`, `ubicacion`, `numUsuariosActuales`, `maxUsuarios`) VALUES
(1, 1, 'servicio 1', '2024-11-19 12:04:34', 'Prueba de inserción del primer servicio', 'Seixo', 0, 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `codUsuario` int(11) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `fechaAlta` timestamp NOT NULL DEFAULT current_timestamp(),
  `admin` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`codUsuario`, `usuario`, `password`, `fechaAlta`, `admin`) VALUES
(1, 'israel', '$2y$13$yyQ0P66mubo2xYbQuLcWWOwJunqdrtFJizGVurXgvvqkaB42XR9k.', '2024-11-13 11:11:11', 0),
(3, 'admin', '$2y$13$I9cgHwzARGt0rJYNe5JvYO8BmJS4.brKczl.uGsfbP0tgSeiVDkki', '2024-11-13 11:21:28', 0);

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
-- Volcado de datos para la tabla `usuarios_servicios`
--

INSERT INTO `usuarios_servicios` (`codUsuario`, `codServicio`, `fechaInsercion`) VALUES
(1, 1, '2024-11-27 10:29:57');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `servicios`
--
ALTER TABLE `servicios`
  ADD PRIMARY KEY (`codServicio`),
  ADD KEY `FK_SERVICIOS_USUARIOS` (`codUsuario`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`codUsuario`),
  ADD UNIQUE KEY `UQ_usuario` (`usuario`);

--
-- Indices de la tabla `usuarios_servicios`
--
ALTER TABLE `usuarios_servicios`
  ADD PRIMARY KEY (`codUsuario`,`codServicio`),
  ADD KEY `FK_SERVICIOS_SERVICIOS` (`codServicio`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `servicios`
--
ALTER TABLE `servicios`
  MODIFY `codServicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `codUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `servicios`
--
ALTER TABLE `servicios`
  ADD CONSTRAINT `FK_SERVICIOS_USUARIOS` FOREIGN KEY (`codUsuario`) REFERENCES `usuarios` (`codUsuario`);

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
