import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import studentApi from "../../api/studentApi";

function AddStudent() {
  const [student, setStudent] = useState({
    name: "",
    email: "",
    address: "",
  });

  const { name, email, address } = student;

  const onChangeInput = (e) => {
    setStudent({ ...student, [e.target.name]: e.target.value });
  };

  let navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    await studentApi.add(student);
    navigate("/");
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <form onSubmit={(e) => handleSubmit(e)}>
            <h2 className="text-center m-4">Register Student</h2>
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

export default AddStudent;
