-- ============================================================
-- Script de datos de prueba para finrisk_db
-- Base de datos: MySQL  |  BD: finrisk_db
-- Tablas: usuarios, productos_credito, historial_externo, evaluaciones
-- ============================================================

USE finrisk_db;

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE evaluaciones;
TRUNCATE TABLE historial_externo;
TRUNCATE TABLE productos_credito;
TRUNCATE TABLE usuarios;
SET FOREIGN_KEY_CHECKS = 1;

-- ============================================================
-- 1. USUARIOS (asesores)
-- Contrasena plana: Admin1234!
-- Hash BCrypt generado con strength 10
-- ============================================================
INSERT INTO usuarios (nombre_completo, email, contrasenia, rol) VALUES
  ('Carlos Mendoza Torres',  'carlos.mendoza@finrisk.com',  '2a10N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LPVyc57bAmC', 'ASESOR'),
  ('Ana Lucia Quispe',       'ana.quispe@finrisk.com',      '2a10N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LPVyc57bAmC', 'ASESOR'),
  ('Roberto Salcedo Pinto',  'roberto.salcedo@finrisk.com', '2a10N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LPVyc57bAmC', 'ADMIN');

-- ============================================================
-- 2. PRODUCTOS DE CREDITO
-- score_minimo calibrado con la formula:
-- Score = 1000 - (deuda_total/50) - (dias_mora*2) - (numero_empresas*20) + ((sueldo-1130)/100)
-- ============================================================
INSERT INTO productos_credito (nombre_producto, monto_minimo, monto_maximo, tasa_interes, score_minimo) VALUES
  ('Credito Basico',            500.00,    5000.00,  24.00, 400),
  ('Credito Personal Estandar', 5000.00,  20000.00,  18.50, 550),
  ('Credito Personal Plus',    10000.00,  40000.00,  15.00, 680),
  ('Credito Preferencial',     20000.00,  80000.00,  12.00, 780),
  ('Credito Premium',          50000.00, 200000.00,   9.50, 870);

-- ============================================================
-- 3. HISTORIAL EXTERNO - 22 clientes con perfiles variados
-- ============================================================
INSERT INTO historial_externo (dni, nombre, apellido, deuda_total, numero_empresas, dias_mora, sueldo) VALUES
-- Perfil EXCELENTE (score ~850-1000)
  ('12345678', 'Miguel',   'Fernandez Rios',   1500.00,  1,  0,  3500.00),
  ('23456789', 'Sofia',    'Paredes Luna',     2000.00,  1,  5,  2800.00),
  ('34567890', 'Javier',   'Castillo Mora',    3000.00,  2,  0,  4000.00),
  ('45678901', 'Valeria',  'Torres Huanca',    5000.00,  1, 10,  5000.00),
-- Perfil BUENO (score ~680-850)
  ('56789012', 'Diego',    'Alvarado Perez',   4000.00,  2,  5,  3200.00),
  ('67890123', 'Lucia',    'Mamani Ticona',    8000.00,  2, 20,  2500.00),
  ('78901234', 'Ricardo',  'Gutierrez Soto',  10000.00,  3, 15,  3000.00),
-- Perfil MEDIO (score ~500-680)
  ('89012345', 'Patricia', 'Herrera Campos',  12000.00,  2, 25,  2800.00),
  ('90123456', 'Andres',   'Vega Condori',     9000.00,  3, 30,  1800.00),
  ('01234567', 'Carmen',   'Mendoza Apaza',   15000.00,  2, 10,  4500.00),
  ('11223344', 'Fernando', 'Quiroz Bernal',   20000.00,  3, 30,  1500.00),
-- Perfil BAJO-MEDIO (score ~300-500)
  ('22334455', 'Gloria',   'Vargas Espinoza', 18000.00,  4, 40,  1800.00),
  ('33445566', 'Hector',   'Llanos Pacheco',  22000.00,  3, 25,  2000.00),
  ('44556677', 'Mariana',  'Choque Flores',   25000.00,  4, 35,  1600.00),
  ('55667788', 'Ernesto',  'Ramos Ccallo',    17000.00,  5, 20,  2200.00),
-- Perfil MALO (score < 300)
  ('66778899', 'Norma',    'Cardenas Quispe', 35000.00,  5, 60,  1200.00),
  ('77889900', 'Luis',     'Huanca Mamani',   30000.00,  6, 50,  1400.00),
  ('88990011', 'Rosa',     'Pilco Condori',   40000.00,  5, 45,  1300.00),
-- Casos BORDE
  ('99001122', 'Pablo',    'Aguilar Diaz',    1130.00,   0,  0,  1130.00),
  ('10111213', 'Elena',    'Sanchez Ruiz',   50000.00,   1,  0, 10000.00),
  ('20212223', 'Tomas',    'Benitez Cruz',       0.00,   0,  0,  2000.00),
  ('30313233', 'Camila',   'Rojas Pinto',      500.00,   1,  5,  1200.00);

-- ============================================================
-- 4. EVALUACIONES de muestra
-- Nota: los externo_id y id_usuario dependen del AUTO_INCREMENT.
-- Si la BD estaba vacia antes de este script: ids 1..22 para clientes, 1..3 para usuarios.
-- ============================================================
INSERT INTO evaluaciones (externo_id, producto_id, id_usuario, score_obtenido, estado, comentarios) VALUES
  (1,  5, 1, 947, 'APROBADO',  'Score 947/870. Sueldo: S/3500, Deuda: S/1500, Mora: 0 dias, Empresas: 1. APROBADO.'),
  (2,  4, 1, 910, 'APROBADO',  'Score 910/780. Sueldo: S/2800, Deuda: S/2000, Mora: 5 dias, Empresas: 1. APROBADO.'),
  (3,  4, 2, 889, 'APROBADO',  'Score 889/780. Sueldo: S/4000, Deuda: S/3000, Mora: 0 dias, Empresas: 2. APROBADO.'),
  (6,  2, 1, 675, 'APROBADO',  'Score 675/550. Sueldo: S/2500, Deuda: S/8000, Mora: 20 dias, Empresas: 2. APROBADO.'),
  (7,  2, 2, 618, 'APROBADO',  'Score 618/550. Sueldo: S/3000, Deuda: S/10000, Mora: 15 dias, Empresas: 3. APROBADO.'),
  (11, 1, 1, 429, 'APROBADO',  'Score 429/400. Sueldo: S/1500, Deuda: S/20000, Mora: 30 dias, Empresas: 3. APROBADO.'),
  (16, 1, 2,  99, 'RECHAZADO', 'Score 99/400. Sueldo: S/1200, Deuda: S/35000, Mora: 60 dias, Empresas: 5. RECHAZADO.');

-- ============================================================
-- VERIFICACION FINAL
-- ============================================================
SELECT 'usuarios'          AS tabla, COUNT(*) AS registros FROM usuarios
UNION ALL
SELECT 'productos_credito',           COUNT(*) FROM productos_credito
UNION ALL
SELECT 'historial_externo',           COUNT(*) FROM historial_externo
UNION ALL
SELECT 'evaluaciones',                COUNT(*) FROM evaluaciones;
