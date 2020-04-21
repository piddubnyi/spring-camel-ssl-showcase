Tested with self-signed certificate

For browser-usage certificate constraints are more strict.
The easiest would be generating self-signed jks using KeyStore Explorer (https://keystore-explorer.org/)

* Install and start the tool
* create new keysotre
    * select JKS
* generate key-pair (icon with 2 keys)
    * use defaults
    * edit name (icon with adress book), fill CN, OK
    * Add Extensions
        * Use standard template (CA)
        * Remove! entry for 'Key Usage'
        * Add Extension (small button with plus sign)
            * Select 'Subject Alternative Name'
            * Add General Name (small button with plus sign)
            * Select 'IP Address'
            * Fill  127.0.0.1 or respective IP as value
    * OK everywhere
* fill some alias and password

There should be an entry now in the list
* click save, fill password

Reconfigure Camel route to use just-created jks-file.

Start the route (Main class)

Open route-endpoint in the browser (https://127.0.0.1:8080/test)
There will be ERR_CERT_AUTHORITY_INVALID error, it's expected.

Now you need to import cert to browser.
E.g. for chrome one can follow:

https://superuser.com/questions/1083766/how-do-i-deal-with-neterr-cert-authority-invalid-in-chrome

When done try to use src/test/resources/ssl-test.html to check websocket in browser.
Websocket message should be sent.