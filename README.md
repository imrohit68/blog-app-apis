
## BLOGAPP-BACKEND-APIS

THIS CONSISTS OF BACKEND APIS OF A BLOGAPP , WITH FEATURES LIKE POST BY CATEGORY,COMMENTS AND OTHERS.THIS PROJECT HAS BEEN MADE IN JAVA USING SPRING BOOT.


## Documentation
## User Apis
* POST /api/users/createUser: Create a new user by providing a UserDTO object in the request body. The response will contain the created user data and a HTTP status code of 201 Created.
* PUT /api/users/{userId}: Update an existing user by providing a UserDTO object in the request body and the userId in the URL path. The response will contain the updated user data and a HTTP status code of 200 OK.
* DELETE /api/users/{userId}: Delete an existing user by providing the userId in the URL path. This endpoint requires an ADMIN role to access. The response will contain a message confirming the deletion and a HTTP status code of 200 OK.
* GET /api/users/: Get a list of all users. This endpoint requires an ADMIN role to access. The response will contain a list of UserDTO objects and a HTTP status code of 200 OK.
* GET /api/users/{userId}: Get a specific user by providing the userId in the URL path. This endpoint requires an ADMIN role to access. The response will contain the user data and a HTTP status code of 200 OK.

## Post Apis

* POST /userId/{userId}/categoryId/{categoryId}/post: creates a new post with the given userId and categoryId, using the data provided in the request body. Returns the created post as a PostDto object.
* GET /categoryId/{categoryId}/post: retrieves all posts belonging to the given categoryId. Returns a list of PostDto objects.
* GET /userId/{userId}/post: retrieves all posts created by the user with the given userId. Returns a list of PostDto objects.
* GET /post/{postId}: retrieves the post with the given postId. Returns a PostDto object.
* GET /post: retrieves all posts, with optional pagination and sorting. Returns a PageResponse object containing a list of PostDto objects and metadata about the page.
* DELETE /post/{userId}: deletes the post with the given postId.
* PUT /post/{postId}: updates the post with the given postId using the data provided in the request body. Returns the updated post as a PostDto object.
* GET /keyword/{keyword}/post: retrieves all posts containing the given keyword in their title or content. Returns a list of PostDto objects.
* POST /post/imageUpload/{postId}: uploads an image for the post with the given postId. Expects a multipart/form-data request with the image field containing the image file. Returns an ImageUploadResponse object containing the updated PostDto object and the filename of the uploaded image.
* GET /post/getImage/{imageName}: retrieves the image with the given imageName that was previously uploaded for a post. Returns the image data as a JPEG.

## Comment Apis

1. POST /userId/{userId}/postId/{postId}/createComment: This endpoint creates a new Comment object for a specific User and Post. It expects a JSON payload in the request body containing the CommentDto object. It also expects the User ID and Post ID to be provided as path variables. It returns a JSON payload containing the saved CommentDto object with HTTP status code 200 (OK).
2. PUT /commentId/{commentId}/updateComment: This endpoint updates an existing Comment object. It expects a JSON payload in the request body containing the CommentDto object with the updated values. It also expects the Comment ID to be provided as a path variable. It returns a JSON payload containing the updated CommentDto object with HTTP status code 200 (OK).
3. DELETE /commentId/{commentId}/delete: This endpoint deletes an existing Comment object. It expects the Comment ID to be provided as a path variable. It returns a JSON payload containing a message indicating that the deletion was successful with HTTP status code 200 (OK).
4. GET /postId/{postId}/getCommentByPost: This endpoint retrieves all Comment objects associated with a specific Post ID. It expects the Post ID to be provided as a path variable. It returns a JSON payload containing a List of CommentDto objects with HTTP status code 200 (OK).

## Category Apis

* POST /: creates a new category and returns a CategoryDto object representing the created category. Requires an authenticated user with an ADMIN role.
* PUT /{categoryId}: updates an existing category with the given ID and returns a CategoryDto object representing the updated category. Requires an authenticated user with an ADMIN role.
* DELETE /{categoryId}: deletes an existing category with the given ID and returns an ApiResponse object indicating the success of the operation. Requires an authenticated user with an ADMIN role.
* GET /{categoryId}: returns a CategoryDto object representing the category with the given ID. Requires an authenticated user with an ADMIN role.
* GET /: returns a list of all categories as a list of CategoryDto objects. Does not require authentication.

## Auth Apis
1. Login API:
    * Path: /login
    * Method: POST
    * Description: Authenticates user credentials and generates a JWT token.
    * Request Body: JwtAuthRequest object containing username and password.
    * Response Body: JwtAuthResponse object containing JWT token.
    * Access Control: Public
2. Give Admin Access API:
    * Path: /giveAdmin/{userId}
    * Method: POST
    * Description: Grants admin access to a user.
    * Request Body: None
    * Path Variable: userId (Integer) - The id of the user to grant admin access to.
    * Response Body: ApiResponse object containing success message.
    * Access Control: Admin only. The user must have the 'ADMIN' role.
