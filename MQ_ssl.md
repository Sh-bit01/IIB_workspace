# MQ Intercommunication using SSL

This guide demonstrates how to configure secure SSL-based intercommunication between two IBM MQ Queue Managers: `A` and `B`.

## 1. Create Queue Managers

```bash
crtmqm A
crtmqm B
```

---

## 2. SSL Setup

### Queue Manager A

#### Create Key Database
```bash
runmqakm -keydb -create -db /var/mqm/qmgrs/A/ssl/a.kdb -pw mypassw0rd -type cms -stash
```

#### Create Self-Signed Certificate
```bash
runmqakm -cert -create -db /var/mqm/qmgrs/A/ssl/a.kdb -pw mypassw0rd -label ibmwebspheremqa -dn "CN=QMA,OU=MQ,O=IBM,C=US" -size 2048 -sig_alg sha256WithRSA
```

#### Extract Public Certificate
```bash
runmqakm -cert -extract -db /var/mqm/qmgrs/A/ssl/a.kdb -pw mypassw0rd -label ibmwebspheremqa -target /tmp/a.arm -format ascii
```

### Queue Manager B

```bash
runmqakm -keydb -create -db /var/mqm/qmgrs/B/ssl/b.kdb -pw mypassw0rd -type cms -stash
runmqakm -cert -create -db /var/mqm/qmgrs/B/ssl/b.kdb -pw mypassw0rd -label ibmwebspheremqb -dn "CN=QMB,OU=MQ,O=IBM,C=US" -size 2048 -sig_alg sha256WithRSA
runmqakm -cert -extract -db /var/mqm/qmgrs/B/ssl/b.kdb -pw mypassw0rd -label ibmwebspheremqb -target /tmp/b.arm -format ascii
```

### Exchange and Add Certificates

```bash
runmqakm -cert -add -db /var/mqm/qmgrs/A/ssl/a.kdb -pw mypassw0rd -label ibmwebspheremqb -file /tmp/b.arm -format ascii
runmqakm -cert -add -db /var/mqm/qmgrs/B/ssl/b.kdb -pw mypassw0rd -label ibmwebspheremqa -file /tmp/a.arm -format ascii
```

---

## 3. Configure Queue Manager A

```bash
strmqm A
runmqsc A
```

```mqsc
ALTER QMGR SSLKEYR('/var/mqm/qmgrs/A/ssl/a')
ALTER QMGR SSLEV(ENABLED)

DEFINE QLOCAL(TO.B) USAGE(XMITQ)

DEFINE CHANNEL(A.TO.B) CHLTYPE(SDR) CONNAME('hostname_B(port)') XMITQ('TO.B') SSLCIPH('TLS_RSA_WITH_AES_128_CBC_SHA') SSLPEER('CN=QMB,OU=MQ,O=IBM,C=US')

DEFINE CHANNEL(B.TO.A) CHLTYPE(RCVR) SSLCIPH('TLS_RSA_WITH_AES_128_CBC_SHA')

REFRESH SECURITY TYPE(SSL)
```

---

## 4. Configure Queue Manager B

```bash
strmqm B
runmqsc B
```

```mqsc
ALTER QMGR SSLKEYR('/var/mqm/qmgrs/B/ssl/b')
ALTER QMGR SSLEV(ENABLED)

DEFINE QLOCAL(TO.A) USAGE(XMITQ)

DEFINE CHANNEL(B.TO.A) CHLTYPE(SDR) CONNAME('hostname_A(port)') XMITQ('TO.A') SSLCIPH('TLS_RSA_WITH_AES_128_CBC_SHA') SSLPEER('CN=QMA,OU=MQ,O=IBM,C=US')

DEFINE CHANNEL(A.TO.B) CHLTYPE(RCVR) SSLCIPH('TLS_RSA_WITH_AES_128_CBC_SHA')

REFRESH SECURITY TYPE(SSL)
```

---

## 5. Start Channels

### On Queue Manager A
```bash
START CHANNEL(A.TO.B)
```

### On Queue Manager B
```bash
START CHANNEL(B.TO.A)
```

---

### Notes:
- Ensure `hostname` and `port` match the respective MQ Listener setup.
- Verify firewall and network accessibility between hosts.
- Always use secure password practices in production environments.

---











---

