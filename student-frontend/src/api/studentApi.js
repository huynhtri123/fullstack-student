import axiosClient from "./axiosClient";

const studentApi = {
  getAll() {
    const url = "/student/getAllStudents";
    return axiosClient.get(url);
  },
  get(id) {
    const url = `/student/getStudent/${id}`;
    return axiosClient.get(url);
  },
  add(data) {
    const url = "/student/createStudent";
    return axiosClient.post(url, data);
  },
  update(id, data) {
    const url = `/student/updateStudent/${id}`;
    return axiosClient.put(url, data);
  },
  delete(id) {
    const url = `/student/deleteStudent/${id}`;
    return axiosClient.delete(url);
  },
  searchByName(keyword) {
    const url = `/student/search?keyword=${keyword}`;
    return axiosClient.get(url);
  },
};

export default studentApi;
