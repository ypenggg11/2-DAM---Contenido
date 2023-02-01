SELECT nombre_prod, nombre_prov, precio_prod FROM productos INNER JOIN proveedores ON proveedores.cod_prov = productos.cod_prov_prod WHERE precio_prod > 2000 ORDER BY precio_prod DESC
SELECT nombre_prov, telefono FROM proveedores INNER JOIN productos ON proveedores.cod_prov = productos.cod_prov_prod WHERE nombre_prod LIKE '%ordenador%'
SELECT nombre_prod FROM productos WHERE stock < 20
SELECT cod_prod, nombre_prod, precio_prod * 0.95 AS precio_rebajado FROM productos INNER JOIN proveedores ON proveedores.cod_prov = productos.cod_prov_prod WHERE bonifica = 0
SELECT nombre_prov, SUM (stock), AVG (precio_prod) FROM productos INNER JOIN proveedores ON proveedores.cod_prov = productos.cod_prov_prod GROUP BY (nombre_prov)
SELECT nombre_prov, direccion_prov, telefono, stock FROM proveedores INNER JOIN productos ON proveedores.cod_prov = productos.cod_prov_prod WHERE stock = (SELECT MAX(stock) FROM productos)
