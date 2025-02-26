# Endpoint Documentation

## Cart Endpoints

### Create a cart
- **Method:** GET
- **Endpoint:** `/cart/add`
- **Description:** Creates a new shopping cart and returns the ID of the created cart.
- **Usage:** Make a GET request to this endpoint to create a new cart. The cart ID is returned in the response.

### Get all carts
- **Method:** GET
- **Endpoint:** `/cart/all`
- **Description:** Returns a list of all existing cart IDs.
- **Usage:** Make a GET request to this endpoint to get a list of all carts.

### Get cart details
- **Method:** GET
- **Endpoint:** `/cart/{id}`
- **Description:** Returns the details of the cart specified by the ID.
- **Usage:** Make a GET request to this endpoint with the cart ID to get its details.

### Add product to cart
- **Method:** POST
- **Endpoint:** `/cart/add/{id}`
- **Description:** Adds a product to the cart specified by the ID.
- **Usage:** Make a POST request to this endpoint with the cart ID and a JSON body containing `productId` and `quantity`.

### Remove product from cart
- **Method:** POST
- **Endpoint:** `/cart/delete-product/{id}`
- **Description:** Removes a product from the cart specified by the ID.
- **Usage:** Make a POST request to this endpoint with the cart ID and a JSON body containing `productId`.

### Clear cart
- **Method:** DELETE
- **Endpoint:** `/cart/clear/{id}`
- **Description:** Clears the cart specified by the ID.
- **Usage:** Make a DELETE request to this endpoint with the cart ID to clear it.

### Delete cart
- **Method:** DELETE
- **Endpoint:** `/cart/delete/{id}`
- **Description:** Deletes the cart specified by the ID.
- **Usage:** Make a DELETE request to this endpoint with the cart ID to delete it.

### Get total price of cart
- **Method:** GET
- **Endpoint:** `/cart/total/{id}`
- **Description:** Returns the total price of the products in the cart specified by the ID.
- **Usage:** Make a GET request to this endpoint with the cart ID to get the total price.

## Product Endpoints

### Get a product
- **Method:** GET
- **Endpoint:** `/product/{id}`
- **Description:** Returns the information of the product specified by the ID.
- **Usage:** Make a GET request to this endpoint with the product ID to get its information.

### Get all products
- **Method:** GET
- **Endpoint:** `/product/all`
- **Description:** Returns a list of all products.
- **Usage:** Make a GET request to this endpoint to get a list of all products.

### Add a product
- **Method:** POST
- **Endpoint:** `/product/add`
- **Description:** Adds a new product.
- **Usage:** Make a POST request to this endpoint with a JSON body containing `description` and `amount`.

### Delete a product
- **Method:** DELETE
- **Endpoint:** `/product/delete`
- **Description:** Deletes the product specified by the ID.
- **Usage:** Make a DELETE request to this endpoint with the product ID as a parameter.

### Update a product
- **Method:** PUT
- **Endpoint:** `/product/update/{id}`
- **Description:** Updates the information of the product specified by the ID.
- **Usage:** Make a PUT request to this endpoint with the product ID and a JSON body containing `description` and `amount`.