import { useEffect, useState } from "react";
import productsApi from "../../api/productsApi";
import "../../scss/products.scss";

function ListProducts() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    getAllProducts();
  }, [products]);

  const getAllProducts = async () => {
    const list = await productsApi.getAll();
    setProducts(list);
  };

  return (
    <div className="productList">
      {products.map((product, index) => (
        <div className="products" key={index}>
          <h3>{product.name}</h3>
          <img src={`${product.imageUrl}`} alt="anh" className="product" />
        </div>
      ))}
    </div>
  );
}

export default ListProducts;
