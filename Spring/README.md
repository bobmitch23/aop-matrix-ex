# SpringBoot Client
There are two endpoints, one to multiply matrices and one to find the determinant of matrices.

## Endpoint 1
http://localhost:8080/multiply

Supported HTTP Verbs: POST

Example json payload:
```javascript
[
	{"values": [[1,4.8],[7.8,5.6]]},
	{"values": [[2.2,8.7],[7.9,1.6]]},
	{"values": [[8.3,4.7],[6.7,7.6]]}
]
```

## Endpoint 2
http://localhost:8080/findDeterminant

Supported HTTP Verbs: POST

Example json payload:
```javascript
{
	"values": [[1,2,3],[4,5,99],[7,8,9]]
}
```
