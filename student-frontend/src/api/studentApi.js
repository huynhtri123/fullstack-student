import axiosClient from "./axiosClient";

const studentApi = {
  getAll() {
    const url = "/getAllStudents";
    return axiosClient.get(url);
  },
  get(id) {
    const url = `/getStudent/${id}`;
    return axiosClient.get(url);
  },
  add(data) {
    const url = "/createStudent";
    return axiosClient.post(url, data);
  },
  update(id, data) {
    const url = `/updateStudent/${id}`;
    return axiosClient.put(url, data);
  },
  delete(id) {
    const url = `/deleteStudent/${id}`;
    return axiosClient.delete(url);
  },
  searchByName(keyword) {
    const url = `/search?keyword=${keyword}`;
    return axiosClient.get(url);
  },
};

export default studentApi;
