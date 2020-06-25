# cucumber-groovy-backend

Adds support for defining cucumber steps using methods that contain the actual step definition as method name:

```groovy
@When
def "Customer adds product {string} to the cart(string productCode) {
  ...
}"
```
