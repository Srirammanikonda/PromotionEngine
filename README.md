# PromotionEngine
Apply Promotion offers to the items in the cart

POST:  http://localhost:9090/calculateFinalPrice

Sample Input format:

[
    {
        "ItemName": "A",
        "Quantity": "4"
    },
    {
        "itemName": "B",
        "quantity": "4"
    },
    {
        "itemName": "C",
        "quantity": "2"
    },
    {
        "itemName": "D",
        "quantity": "3"
    }
]

Sample Output:

The Total Price is: 345.0