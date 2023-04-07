#!/bin/bash

curl -H "Content-Type: application/json" -X PUT -d @change_book_price_testdata.json http://localhost/authors/5f2e352e-a391-46fe-9a60-865cd615d6eb/books/4563865801239
