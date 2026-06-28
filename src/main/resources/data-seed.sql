TRUNCATE TABLE evaluaciones CASCADE;
TRUNCATE TABLE historial_externo CASCADE;
TRUNCATE TABLE productos_credito CASCADE;
TRUNCATE TABLE usuarios CASCADE;

INSERT INTO usuarios (nombre_completo, email, contrasenia, rol) VALUES
  ('Carlos Mendoza Torres',  'carlos.mendoza@finrisk.com',  '2a10N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LPVyc57bAmC', 'ASESOR'),
  ('Ana Lucia Quispe',       'ana.quispe@finrisk.com',      '2a10N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LPVyc57bAmC', 'ASESOR'),
  ('Roberto Salcedo Pinto',  'roberto.salcedo@finrisk.com', '2a10N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LPVyc57bAmC', 'ADMIN');

INSERT INTO productos_credito (nombre_producto, monto_minimo, monto_maximo, tasa_interes, score_minimo) VALUES
  ('Credito Basico',            500.00,    5000.00,  24.00, 400),
  ('Credito Personal Estandar', 5000.00,  20000.00,  18.50, 550),
  ('Credito Personal Plus',    10000.00,  40000.00,  15.00, 680),
  ('Credito Preferencial',     20000.00,  80000.00,  12.00, 780),
  ('Credito Premium',          50000.00, 200000.00,   9.50, 870);

INSERT INTO historial_externo (dni, nombre, apellido, deuda_total, numero_empresas, dias_mora, sueldo) VALUES
  ('12345678', 'Miguel',   'Fernandez Rios',   1500.00,  1,  0,  3500.00),
  ('23456789', 'Sofia',    'Paredes Luna',     2000.00,  1,  5,  2800.00),
  ('34567890', 'Javier',   'Castillo Mora',    3000.00,  2,  0,  4000.00),
  ('45678901', 'Valeria',  'Torres Huanca',    5000.00,  1, 10,  5000.00),
  ('56789012', 'Diego',    'Alvarado Perez',   4000.00,  2,  5,  3200.00),
  ('67890123', 'Lucia',    'Mamani Ticona',    8000.00,  2, 20,  2500.00),
  ('78901234', 'Ricardo',  'Gutierrez Soto',  10000.00,  3, 15,  3000.00),
  ('89012345', 'Patricia', 'Herrera Campos',  12000.00,  2, 25,  2800.00),
  ('90123456', 'Andres',   'Vega Condori',     9000.00,  3, 30,  1800.00),
  ('01234567', 'Carmen',   'Mendoza Apaza',   15000.00,  2, 10,  4500.00),
  ('11223344', 'Fernando', 'Quiroz Bernal',   20000.00,  3, 30,  1500.00),
  ('22334455', 'Gloria',   'Vargas Espinoza', 18000.00,  4, 40,  1800.00),
  ('33445566', 'Hector',   'Llanos Pacheco',  22000.00,  3, 25,  2000.00),
  ('44556677', 'Mariana',  'Choque Flores',   25000.00,  4, 35,  1600.00),
  ('55667788', 'Ernesto',  'Ramos Ccallo',    17000.00,  5, 20,  2200.00),
  ('66778899', 'Norma',    'Cardenas Quispe', 35000.00,  5, 60,  1200.00),
  ('77889900', 'Luis',     'Huanca Mamani',   30000.00,  6, 50,  1400.00),
  ('88990011', 'Rosa',     'Pilco Condori',   40000.00,  5, 45,  1300.00),
  ('99001122', 'Pablo',    'Aguilar Diaz',    1130.00,   0,  0,  1130.00),
  ('10111213', 'Elena',    'Sanchez Ruiz',   50000.00,   1,  0, 10000.00),
  ('20212223', 'Tomas',    'Benitez Cruz',       0.00,   0,  0,  2000.00),
  ('30313233', 'Camila',   'Rojas Pinto',      500.00,   1,  5,  1200.00);
