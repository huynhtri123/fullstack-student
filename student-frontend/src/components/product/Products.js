import ListProducts from "./ListProducts";
import ProductInput from "./ProductInput";
import "../../scss/products.scss";

function Products() {
  return (
    <div className="product-component">
      <ProductInput></ProductInput>
      <ListProducts></ListProducts>
    </div>
  );
}

export default Products;
