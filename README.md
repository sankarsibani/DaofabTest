# DaofabTest
 DaofabTest



There are 2 end-points

daofab/parents?page=1&sortOrder=asc : 
takes 2 params as page no nad sordOrder which sorts on id column of parent object

/daofab/child/1 : 
takes a path param of parent id, and returns all children associated with it

Here are some examples of it.



http://localhost:8080/daofab/parents?page=1&sortOrder=asc

Respones : 
[
    {
        "id": 1,
        "sender": "ABC",
        "receiver": "XYZ",
        "totalAmount": 200,
        "totalPaidAmount": 100
    },
    {
        "id": 2,
        "sender": "XYZ",
        "receiver": "MNP",
        "totalAmount": 100,
        "totalPaidAmount": 100
    }
]


http://localhost:8080/daofab/parents?page=1&sortOrder=desc
Resp : 
[
    {
        "id": 7,
        "sender": "ABC",
        "receiver": "PQRS",
        "totalAmount": 200,
        "totalPaidAmount": 0
    },
    {
        "id": 6,
        "sender": "MNP",
        "receiver": "PQRS",
        "totalAmount": 200,
        "totalPaidAmount": 125
    }
]


http://localhost:8080/daofab/parents?page=10&sortOrder=desc

{
    "msg": "No data found",
    "code": 2000
}



http://localhost:8080/daofab/parents?page=10&sortOrder=desc

{
    "code": 5000,
    "error": "Internal Error, Please contact Admin for more details"
}

http://localhost:8080/daofab/child/1
[
    {
        "id": 1,
        "parentId": 1,
        "paidAmount": 10
    },
    {
        "id": 2,
        "parentId": 1,
        "paidAmount": 50
    },
    {
        "id": 3,
        "parentId": 1,
        "paidAmount": 40
    }
]


http://localhost:8080/daofab/child/2

[
    {
        "id": 4,
        "parentId": 2,
        "paidAmount": 100
    }
]


http://localhost:8080/daofab/child/9

{
    "msg": "No data found",
    "code": 2000
}


