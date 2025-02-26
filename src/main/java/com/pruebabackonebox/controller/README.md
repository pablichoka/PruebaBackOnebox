# Documentación de Endpoints

## Endpoints de Cart

### Crear un carrito
- **Método:** GET
- **Endpoint:** `/cart/add`
- **Descripción:** Crea un nuevo carrito de compras y devuelve el ID del carrito creado.
- **Uso:** Realiza una solicitud GET a este endpoint para crear un nuevo carrito. El ID del carrito se devuelve en la respuesta.

### Obtener todos los carritos
- **Método:** GET
- **Endpoint:** `/cart/all`
- **Descripción:** Devuelve una lista de todos los IDs de los carritos existentes.
- **Uso:** Realiza una solicitud GET a este endpoint para obtener una lista de todos los carritos.

### Obtener detalles de un carrito
- **Método:** GET
- **Endpoint:** `/cart/{id}`
- **Descripción:** Devuelve los detalles del carrito especificado por el ID.
- **Uso:** Realiza una solicitud GET a este endpoint con el ID del carrito para obtener sus detalles.

### Añadir producto al carrito
- **Método:** POST
- **Endpoint:** `/cart/add/{id}`
- **Descripción:** Añade un producto al carrito especificado por el ID.
- **Uso:** Realiza una solicitud POST a este endpoint con el ID del carrito y un cuerpo JSON que contenga `productId` y `quantity`.

### Eliminar producto del carrito
- **Método:** POST
- **Endpoint:** `/cart/delete-product/{id}`
- **Descripción:** Elimina un producto del carrito especificado por el ID.
- **Uso:** Realiza una solicitud POST a este endpoint con el ID del carrito y un cuerpo JSON que contenga `productId`.

### Vaciar carrito
- **Método:** DELETE
- **Endpoint:** `/cart/clear/{id}`
- **Descripción:** Vacía el carrito especificado por el ID.
- **Uso:** Realiza una solicitud DELETE a este endpoint con el ID del carrito para vaciarlo.

### Eliminar carrito
- **Método:** DELETE
- **Endpoint:** `/cart/delete/{id}`
- **Descripción:** Elimina el carrito especificado por el ID.
- **Uso:** Realiza una solicitud DELETE a este endpoint con el ID del carrito para eliminarlo.

### Obtener precio total del carrito
- **Método:** GET
- **Endpoint:** `/cart/total/{id}`
- **Descripción:** Devuelve el precio total de los productos en el carrito especificado por el ID.
- **Uso:** Realiza una solicitud GET a este endpoint con el ID del carrito para obtener el precio total.

## Endpoints de Product

### Obtener un producto
- **Método:** GET
- **Endpoint:** `/product/{id}`
- **Descripción:** Devuelve la información del producto especificado por el ID.
- **Uso:** Realiza una solicitud GET a este endpoint con el ID del producto para obtener su información.

### Obtener todos los productos
- **Método:** GET
- **Endpoint:** `/product/all`
- **Descripción:** Devuelve una lista de todos los productos.
- **Uso:** Realiza una solicitud GET a este endpoint para obtener una lista de todos los productos.

### Añadir un producto
- **Método:** POST
- **Endpoint:** `/product/add`
- **Descripción:** Añade un nuevo producto.
- **Uso:** Realiza una solicitud POST a este endpoint con un cuerpo JSON que contenga `description` y `amount`.

### Eliminar un producto
- **Método:** DELETE
- **Endpoint:** `/product/delete`
- **Descripción:** Elimina el producto especificado por el ID.
- **Uso:** Realiza una solicitud DELETE a este endpoint con el ID del producto como parámetro.

### Actualizar un producto
- **Método:** PUT
- **Endpoint:** `/product/update/{id}`
- **Descripción:** Actualiza la información del producto especificado por el ID.
- **Uso:** Realiza una solicitud PUT a este endpoint con el ID del producto y un cuerpo JSON que contenga `description` y `amount`.