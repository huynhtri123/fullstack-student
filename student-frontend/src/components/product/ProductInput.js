import { useState } from "react";
import { toast } from "react-toastify";

import "../../scss/products.scss";
import productsApi from "../../api/productsApi";

function ProductInput() {
  const [name, setName] = useState("");
  const [file, setFile] = useState(null);

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = new FormData();
    formData.append("productName", name);
    if (file) {
      formData.append("file", file);
    }
    try {
      const res = await productsApi.create(formData);

      // Kiểm tra mã trạng thái từ phản hồi
      if (res.status === 200) {
        toast.success("Product created successfully!");
      } else {
        // Sử dụng res.data để lấy message chi tiết từ API
        toast.warning(`Error: ${res.message}`);
      }
    } catch (error) {
      // Kiểm tra nếu có response và lỗi là từ server
      if (error.response) {
        // Trích xuất message từ error.response.data
        const errorMessage =
          error.response.data.message || "Internal server error";
        toast.error(`Error creating product: ${errorMessage}`);
      } else {
        // Nếu lỗi không phải từ response, là lỗi mạng hoặc lỗi khác
        toast.error(`Network error: ${error.message}`);
      }
    }
  };

  return (
    <div className="product-input">
      <label className="input-product-title">Create a new Product</label>
      <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
        <form onSubmit={(e) => handleSubmit(e)}>
          <div className="mb-3">
            <label htmlFor="Name" className="form-label">
              Product's Name
            </label>
            <input
              type="text"
              className="form-control"
              placeholder="Enter product name..."
              required
              name="name"
              onChange={(e) => setName(e.target.value)}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="Name" className="form-label">
              Product's Image url
            </label>
            <input
              type="file"
              className="form-control"
              placeholder="Enter product name..."
              required
              onChange={handleFileChange}
            />
          </div>
          <button type="submit" className="btn btn-outline-primary">
            Submit
          </button>
        </form>
      </div>
    </div>
  );
}

export default ProductInput;
