Tested with self-signed certificate

generate sertificate:
keytool -genkeypair -keyalg RSA -keystore self_signed_localhost.jks -alias self_signed_localhost

enter the password, I used '123456', other fields could be left blank

run Main, to start routes

run SslTests to check communication.  
