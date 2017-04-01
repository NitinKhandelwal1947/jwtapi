# jwtapi
Rest api based Json Web Token (jwt) to generate, Verify and refresh token.

JSON Web Token (JWT) is an open standard (RFC 7519) that defines a compact and self-contained way for securely transmitting information between parties as a JSON object. This information can be verified and trusted because it is digitally signed. JWTs can be signed using a secret  or a public/private key pair using RSA.

Here I have created a rest based api through which you can create a JWT token, Verify it and refresh it. A shared secret key is used to digitally signed the token. This secret key can be found in code base URIConstants file. All default payload information is kept in this file. If you want to override this default information you can pass through rest api call while generating token and can override it.

There are 3 rest api is used in this project....

  1. /jwtService/token/generateToken
  2. /jwtService/token/getTokenInfo
  3. /jwtService/token/refreshToken
  
  
Generate JWT Token...

  /jwtService/token/generateToken  call this api with empty request body {} . If you want to override the payload and want to add extra     payload you can do so by passing request body like.. {"expiry":"120000","extraParamMap":{"Name":"XXX","Id":"1111"}} which will override   default expiry parameter and will add Name and Id in your payload.


Verify Token or Get Token Information...

  /jwtService/token/getTokenInfo  by passing oldJwtToken in request body to get Token information or verify token.{"oldJwtToken":"XXXXXXXXXXXXXXXXXX.XXXXXXXXXXX.XXXXXXXXXXXXXXXX"}


Refresh JWT Token....

  /jwtService/token/refreshToken by passing oldJwtToken in request body to get refresh token. You need to pass last generated token only      to get it refreshed otherwise it will not work.
  {"oldJwtToken":"XXXXXXXXXXXXXXXXXX.XXXXXXXXXXX.XXXXXXXXXXXXXXXX"}
