import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { toast } from "react-toastify";

import studentApi from "../../api/studentApi";

function UpdateStudent() {
  const [student, setStudent] = useState({
    name: "",
    email: "",
    address: "",
  });

  const { name, email, address } = student;

  //get current student
  //get params from url
  const { id } = useParams();

  useEffect(() => {
    getStudentById();
  }, []);

  const getStudentById = async () => {
    const student = await studentApi.get(id);
    setStudent(student.data);
  };

  const onChangeInput = (e) => {
    setStudent({ ...student, [e.target.name]: e.target.value });
  };

  let navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    await studentApi.update(id, student);
    navigate("/");
    toast.success("Updated student successfully");
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <form onSubmit={(e) => handleSubmit(e)}>
            <h2 className="text-center m-4">Update Student</h2>
            <div className="mb-3">
              <label htmlFor="Name" className="form-label">
                Name
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter your name..."
                name="name"
                value={name}
                onChange={(e) => onChangeInput(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Email" className="form-label">
                Email
              </label>
              <input
                type="email"
                className="form-control"
                placeholder="Enter your email..."
                name="email"
                value={email}
                onChange={(e) => onChangeInput(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Address" className="form-label">
                Address
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter your address..."
                name="address"
                value={address}
                onChange={(e) => onChangeInput(e)}
              />
            </div>
            <button type="submit" className="btn btn-outline-primary">
              Submit
            </button>
            <Link to={"/"} className="btn btn-outline-danger mx-2">
              Cancel
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}

export default UpdateStudent;
