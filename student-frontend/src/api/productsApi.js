import axiosClient from "./axiosClient";

const productsApi = {
  getAll() {
    const url = "/product/getAll";
    return axiosClient.get(url);
  },
  create(productData) {
    const url = "/product/create";
    return axiosClient.post(url, productData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
  },
};

export default productsApi;
